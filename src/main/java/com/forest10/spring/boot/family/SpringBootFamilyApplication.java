package com.forest10.spring.boot.family;

import javax.annotation.Resource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2//http://localhost:8080/swagger-ui.html#/
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SpringBootFamilyApplication extends SpringBootServletInitializer {

	@Resource
	private ApplicationContext applicationContext;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootFamilyApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

		return application.sources(this.getClass());
	}


}
