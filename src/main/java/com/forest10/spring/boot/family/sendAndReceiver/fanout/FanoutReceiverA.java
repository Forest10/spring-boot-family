package com.forest10.spring.boot.family.sendAndReceiver.fanout;

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
@RabbitListener(queues = "fanout.A")
public class FanoutReceiverA {

	@RabbitHandler
	public void process(String hello) {
		log.info("FanoutReceiverA接收:" + hello);
	}

}
