package com.forest10.spring.boot.family;

import com.forest10.spring.boot.family.common.JsonResult;
import com.forest10.spring.boot.family.service.multipleson.CommonServiceWithMultipleSonResource;
import javax.annotation.Resource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SpringBootFamilyApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootFamilyApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(this.getClass());
    }

    @Resource
    private CommonServiceWithMultipleSonResource commonServiceWithMultipleSonResource;

    @GetMapping("/say")
    public JsonResult<String> say() {
        return JsonResult.success(commonServiceWithMultipleSonResource.commonSay());
    }


}
