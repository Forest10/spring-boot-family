package com.forest10.spring.boot.family.properties;

import com.qiniu.util.Auth;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 七牛配置文件
 *
 * @author Forest10
 * @date 2018/9/10 下午8:31
 */
@Data
@Component
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "qiniu")
public class CoreProperties {

	private String accessKey;
	private String secretKey;
	private String bucket;


	@Bean(value = "qiniuAuth")
	public Auth auth() {
		return Auth.create(accessKey, secretKey);
	}

}
