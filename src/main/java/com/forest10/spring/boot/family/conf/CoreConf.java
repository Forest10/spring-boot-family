package com.forest10.spring.boot.family.conf;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
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

	public final static String message = "topic.message";
	public final static String messages = "topic.messages";
	public final static String topicHello = "topic.hello";
	public final static String exchange = "exchange";


	@Bean
	Queue queueMessage() {
		return new Queue(message);
	}

	@Bean
	Queue queueMessages() {
		return new Queue(messages);
	}

	@Bean
	Queue queueTopicHello() {
		return new Queue(topicHello);
	}


	@Bean
	TopicExchange exchange() {
		return new TopicExchange(exchange);
	}


	@Bean
	Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
		return BindingBuilder.bind(queueMessage).to(exchange).with(message);
	}

	@Bean
	Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange) {
		return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
	}

}
