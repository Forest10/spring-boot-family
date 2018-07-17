package com.forest10.spring.boot.family.sendAndReceiver.fanout;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author Forest10
 * @date 2018/7/16 下午5:22
 */
@Slf4j
@Component
@RabbitListener(queues = "fanout.C")
public class FanoutReceiverC {

	@RabbitHandler
	public void process(String hello) throws InterruptedException {
		log.info("FanoutReceiverC接收:" + hello);
		TimeUnit.SECONDS.sleep(5);
		log.info("FanoutReceiverC done!");
	}

}
