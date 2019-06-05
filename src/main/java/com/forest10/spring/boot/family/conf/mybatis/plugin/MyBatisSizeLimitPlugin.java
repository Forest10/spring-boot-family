package com.forest10.spring.boot.family.conf.mybatis.plugin;


import java.sql.Connection;
import java.util.Properties;
import java.util.function.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
 * @author Forest10
 * @date 2019-06-05 22:23
 */
@Slf4j
@Component
@Intercepts({
		@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})
})
public class MyBatisSizeLimitPlugin implements Interceptor {


	private Integer mysqlLimitSize = 2;

	private Integer sqlServerLimitSize = 2;

	@Override
	public Object intercept(Invocation invocation) throws Throwable {

		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
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

		/**
		 * 如果不是查询直接放开执行链
		 */
		if (!StringUtils.startsWithIgnoreCase(sql, "SELECT")) {
			return invocation.proceed();
		}

		Configuration configuration = (Configuration) metaObject.
				getValue("delegate.configuration");
		String dialect = configuration.getVariables().getProperty("dialect");

		StringBuilder newSqlBuilder = new StringBuilder(sql);

		if (StringUtils.equals("mysql", dialect)) {
			//防止误伤主动分页
			if (checkMySqlNeedReWrite().test(sql)) {
				newSqlBuilder.append(StringUtils.SPACE).append("LIMIT").append(StringUtils.SPACE).append(mysqlLimitSize);
			}
			//设置rowBounds逻辑内存分页为NO_ROW
			metaObject.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
			metaObject.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);
		}
		//设置修改完毕之后的SQL
		metaObject.setValue("delegate.boundSql.sql", newSqlBuilder.toString());

		return invocation.proceed();
	}

	/**
	 * 以后慢慢添加边缘条件
	 */
	private Predicate<String> checkMySqlNeedReWrite() {
		return sql -> !StringUtils.containsIgnoreCase(sql, "LIMIT");
	}

	@Override
	public Object plugin(Object target) {
		// 当目标类是StatementHandler类型时，才包装目标类，否者直接返回目标本身,减少目标被代理的
		// 次数
		return target instanceof StatementHandler ? Plugin.wrap(target, this) : target;

	}

	@Override
	public void setProperties(Properties properties) {
		mysqlLimitSize = Integer.valueOf(properties.getProperty("mysqlLimitSize", "500"));
		sqlServerLimitSize = Integer.valueOf(properties.getProperty("sqlServerLimitSize", "500"));
	}
}
