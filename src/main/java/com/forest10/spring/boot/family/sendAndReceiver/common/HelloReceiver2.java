package com.forest10.spring.boot.family.sendAndReceiver.common;

import com.forest10.spring.boot.family.domain.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @author Forest10
 * @date 2018/7/16 下午5:22
 */
@Slf4j
@Component
@RabbitListener(queues = "topic.hello")
public class HelloReceiver2 {

	@RabbitHandler
	public void process(@Payload Book book) {
		log.info("HelloReceiver2接收:" + book);
	}

}
