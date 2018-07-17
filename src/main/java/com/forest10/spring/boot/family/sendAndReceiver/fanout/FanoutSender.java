package com.forest10.spring.boot.family.sendAndReceiver.fanout;

import com.forest10.spring.boot.family.conf.CoreConf;
import com.forest10.spring.boot.family.conf.FanoutRabbitConfig;
import com.forest10.spring.boot.family.domain.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author Forest10
 * @date 2018/7/16 下午5:19
 */
@Slf4j
@Component
public class FanoutSender {

	@Autowired
	private AmqpTemplate amqpTemplate;

	@Scheduled(fixedRate = 3000)
	public void sendMessage1() {
		String content = "hello,I`m fanout msg; " + LocalDateTime.now();
		log.info("发送fanout:" + content);
		amqpTemplate.convertAndSend(FanoutRabbitConfig.fanoutExchange, "", content);
	}


}
