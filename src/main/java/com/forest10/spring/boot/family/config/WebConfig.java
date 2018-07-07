package com.forest10.spring.boot.family.config;

import com.forest10.spring.boot.family.interceptor.RequestRateLimiterInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;

/**
 * @author Forest10
 * @date 2018/7/7 下午9:12
 */
@EnableWebMvc
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	@Resource
	private RequestRateLimiterInterceptor requestRateLimiterInterceptor;

	// 注册自制拦截器
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(requestRateLimiterInterceptor).addPathPatterns("/limit/**");
	}
}
