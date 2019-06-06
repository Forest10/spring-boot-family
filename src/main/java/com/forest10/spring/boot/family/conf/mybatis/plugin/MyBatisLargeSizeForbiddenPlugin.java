package com.forest10.spring.boot.family.conf.mybatis.plugin;


import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Properties;
import java.util.UUID;
import java.util.function.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

/**
 * 拦截大批量查询SQL并主动修改(不透明)并发全员通报邮件
 *
 * @author Forest10
 * @date 2019-06-05 22:23µ
 */
@Slf4j
@Component
@Intercepts({
		@Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class}),
		@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})
})
public class MyBatisLargeSizeForbiddenPlugin implements Interceptor {


	private static Integer MAX_LIMIT_SIZE = 2_000;
	private String PROCESS_ID;

	/**
	 * 生成ProcessId
	 */
	private static String generateProcessId() {
		return LocalDate.now()
				.toString() + "@" + UUID.randomUUID()
				.toString();
	}

	public static void main(String[] args) {
		System.out.println(SqlDialectEnum.getByName("mysql"));
	}

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Object invocationTarget = invocation.getTarget();
		PROCESS_ID = generateProcessId();
		log.info("进入MyBatisLargeSizeForbiddenPlugin: PROCESS_ID:{}", PROCESS_ID);
		//处理prepare
		handlePrepareMethod(invocationTarget);
		//处理resultSet
		Object resultSet = handleResultSet(invocation, invocationTarget);
		log.info("离开MyBatisLargeSizeForbiddenPlugin: PROCESS_ID:{}", PROCESS_ID);
		return Objects.isNull(resultSet) ? invocation.proceed() : resultSet;
	}

	private void handlePrepareMethod(Object invocationTarget) {
		MetaObject metaObject = null;
		String logSql = StringUtils.EMPTY;
		String originalSql = StringUtils.EMPTY;
		try {
			//处理prepare
			if (invocationTarget instanceof StatementHandler) {
				StatementHandler statementHandler = (StatementHandler) invocationTarget;
				metaObject = SystemMetaObject.forObject(statementHandler);
				// 分离代理对象链(由于目标类可能被多个拦截器拦截，从而形成多次代理，通过下面的两次循环可以分离出最原始的的目标类)
				while (metaObject.hasGetter("h")) {
					metaObject = SystemMetaObject.forObject(metaObject.getValue("h"));
				}
				// 分离最后一个代理对象的目标类
				while (metaObject.hasGetter("target")) {
					metaObject = SystemMetaObject.forObject(metaObject.getValue("target"));
				}
				//获取sql
				String sql = String.valueOf(metaObject.getValue("delegate.boundSql.sql"));
				logSql = sql;
				/**
				 * 如果不是查询直接放开执行链
				 */
				if (!StringUtils.startsWithIgnoreCase(sql, "SELECT")) {
					return;
				}

				Configuration configuration = (Configuration) metaObject.
						getValue("delegate.configuration");
				//获取方言
				SqlDialectEnum sqlDialectEnum = SqlDialectEnum.getByName(configuration.getVariables().getProperty("dialect"));

				StringBuilder newSqlBuilder = new StringBuilder(sql);

				if (Objects.equals(sqlDialectEnum, SqlDialectEnum.MYSQL)) {
					//防止误伤主动分页
					if (checkMySqlNeedNotReWrite().negate().test(sql)) {
						newSqlBuilder.append(StringUtils.SPACE).append("LIMIT").append(StringUtils.SPACE).append(MAX_LIMIT_SIZE);
					}
					//设置rowBounds逻辑内存分页为NO_ROW
					metaObject.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
					metaObject.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);
				}
				originalSql = sql;
				//设置修改完毕之后的SQL
				metaObject.setValue("delegate.boundSql.sql", newSqlBuilder.toString());
			}
		} catch (Exception e) {
			log.error("重写SQL LIMIT发生错误,原始SQL:{},PROCESS_ID:{}", logSql, PROCESS_ID);
		} finally {
			if (Objects.nonNull(metaObject) && StringUtils.isNotBlank(originalSql)) {
				metaObject.setValue("delegate.boundSql.sql", originalSql);
			}
		}
	}

	private void handleReWriteMysql() {

	}

	private Object handleResultSet(Invocation invocation, Object invocationTarget) throws Throwable {
		Object result = null;
		if (invocationTarget instanceof ResultSetHandler) {
			result = invocation.proceed();
			log.info("large SIZE result:{}", result);
		}
		return result;
	}

	/**
	 * 边缘条件
	 */
	private Predicate<String> checkMySqlNeedNotReWrite() {
		//1.SQL里面本身含有LIMIT关键字2.本身是count的3.本身是为了锁定数据的 .select ...for update
		return sql -> StringUtils.containsAny(sql.toUpperCase(), "LIMIT", "COUNT", "FOR");
	}

	@Override
	public Object plugin(Object target) {
		// 当目标类是StatementHandler或者ResultSetHandler类型时，才包装目标类，否者直接返回目标本身,减少目标被代理的
		// 次数
		return target instanceof StatementHandler || target instanceof ResultSetHandler ? Plugin.wrap(target, this) : target;
	}

	@Override
	public void setProperties(Properties properties) {
		MAX_LIMIT_SIZE = Integer.valueOf(properties.getProperty("maxLimitSize", "500"));
	}
}
