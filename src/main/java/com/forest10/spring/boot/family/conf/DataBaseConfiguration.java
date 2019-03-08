package com.forest10.spring.boot.family.conf;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.forest10.mybatis.interceptor.MybatisAopInterceptor;
import javax.sql.DataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * 库配置
 *
 * @author Forest10
 * @date 2018/12/13 11:39
 */
@Configuration
@MapperScan(basePackages = "com.forest10.spring.boot.family.repository", sqlSessionTemplateRef = "sqlSessionTemplate")
public class DataBaseConfiguration {

    @Bean
    MybatisAopInterceptor mybatisAopInterceptor() {
        return new MybatisAopInterceptor();
    }

    @Bean(name = "dataSource", destroyMethod = "close", initMethod = "init")
    @ConfigurationProperties("spring.datasource.druid")
    public DataSource securityDataSource() {
        return DruidDataSourceBuilder.create()
            .build();
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(
        @Qualifier("dataSource") DataSource dataSource)
        throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(
            new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/basic/*.xml"));
        sqlSessionFactoryBean.setConfigLocation(
            new PathMatchingResourcePatternResolver().getResource("classpath:mybatis-config.xml"));
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{mybatisAopInterceptor()});
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "TransactionManager")
    public DataSourceTransactionManager transactionManager(
        @Qualifier("dataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(
        @Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
