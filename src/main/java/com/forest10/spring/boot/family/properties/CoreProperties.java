package com.forest10.spring.boot.family.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author Forest10
 * @date 2018/9/10 下午8:31
 */
@Data
@Component
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix="core")
public class CoreProperties {

	private String cpu;

}
