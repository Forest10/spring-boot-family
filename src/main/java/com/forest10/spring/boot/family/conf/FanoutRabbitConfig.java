package com.forest10.spring.boot.family.conf;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Forest10
 * @date 2018/7/17 下午4:03
 */
@Configuration
public class FanoutRabbitConfig {

	public final static String fanoutExchange = "fanoutExchange";


	@Bean
	Queue AMessage() {
		return new Queue("fanout.A");
	}

	@Bean
	Queue BMessage() {
		return new Queue("fanout.B");
	}

	@Bean
	Queue CMessage() {
		return new Queue("fanout.C");
	}


	@Bean
	FanoutExchange fanoutExchange() {
		return new FanoutExchange("fanoutExchange");
	}


	@Bean
	Binding bindingExchangeA(Queue AMessage, FanoutExchange fanoutExchange) {
		return BindingBuilder.bind(AMessage).to(fanoutExchange);
	}

	@Bean
	Binding bindingExchangeB(Queue BMessage, FanoutExchange fanoutExchange) {
		return BindingBuilder.bind(BMessage).to(fanoutExchange);
	}

	@Bean
	Binding bindingExchangeC(Queue CMessage, FanoutExchange fanoutExchange) {
		return BindingBuilder.bind(CMessage).to(fanoutExchange);
	}

}
