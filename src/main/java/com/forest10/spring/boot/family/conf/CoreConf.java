package com.forest10.spring.boot.family.conf;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 占位
 *
 * @author Forest10
 * @date 2018/7/16 下午5:15
 */
@Configuration
public class CoreConf {

	@Bean
	public Queue queue() {
		return new Queue("hello");
	}
}
