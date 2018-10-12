package com.forest10.spring.boot.family;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author forest10
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.forest10.spring.boot.family"})
public class SpringBootFamilyApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootFamilyApplication.class);
    }

    public static void main(String[] args) {
        System.setProperty("spring.devtools.restart.enabled", "true");
        System.setProperty("spring.devtools.restart.additional-paths", "../security,../provider");
        SpringApplication.run(SpringBootFamilyApplication.class, args);
    }
}
