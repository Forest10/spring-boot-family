package com.forest10.spring.boot.family;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SpringBootFamilyApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {

        SpringApplication.run(SpringBootFamilyApplication.class, args);
        Runtime.getRuntime()
            .addShutdownHook(new Thread(() -> System.out.println("执行shutdown hook")));
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(this.getClass());
    }

}
