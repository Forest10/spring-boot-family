package com.forest10.spring.boot.family.sendAndReceiver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Forest10
 * @date 2018/7/16 下午5:22
 */
@Slf4j
@Component
@RabbitListener(queues = "hello")
public class HelloReceiver2 {

	@RabbitHandler
	public void process(String hello) {
		log.info("HelloReceiver2接收:" + hello);
	}

}
