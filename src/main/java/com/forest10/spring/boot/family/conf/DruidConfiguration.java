package com.forest10.spring.boot.family.conf;

import com.alibaba.druid.support.http.ResourceServlet;
import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * druid连接池配置
 *
 * @author Forest10
 * @date 2018/10/29 17:42
 */
@Configuration
public class DruidConfiguration {

    @Bean
    public ServletRegistrationBean druidStatViewServlet() {
        StatViewServlet viewServlet = new StatViewServlet();
        ServletRegistrationBean<StatViewServlet> servletRegistrationBean =
            new ServletRegistrationBean(viewServlet, "/druid/*");
        servletRegistrationBean.addInitParameter(ResourceServlet.PARAM_NAME_USERNAME, "Forest10");
        servletRegistrationBean
            .addInitParameter(ResourceServlet.PARAM_NAME_PASSWORD, "Forest10");
        servletRegistrationBean
            .addInitParameter(StatViewServlet.PARAM_NAME_RESET_ENABLE, Boolean.toString(false));
        return servletRegistrationBean;
    }

}
