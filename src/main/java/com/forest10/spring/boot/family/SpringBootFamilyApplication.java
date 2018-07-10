package com.forest10.spring.boot.family;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@EnableAdminServer
@SpringBootApplication
public class SpringBootFamilyApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(this.getClass());
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootFamilyApplication.class, args);
	}
}
