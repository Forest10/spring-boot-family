package com.forest10.spring.boot.family.conf;

import com.dianping.cat.servlet.CatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Forest10
 * @date 2018/8/8 下午1:14
 */

@Configuration
public class CatFilterConfigure {

	@Bean
	public FilterRegistrationBean catFilter() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		CatFilter filter = new CatFilter();
		registration.setFilter(filter);
		registration.addUrlPatterns("/*");
		//   registration.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		registration.setName("cat-filter");
		registration.setOrder(2);
		return registration;
	}
}
