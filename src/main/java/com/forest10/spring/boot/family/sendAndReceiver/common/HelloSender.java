package com.forest10.spring.boot.family.sendAndReceiver.common;

import com.forest10.spring.boot.family.conf.CoreConf;
import com.forest10.spring.boot.family.domain.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Forest10
 * @date 2018/7/16 下午5:19
 */
@Slf4j
@Component
public class HelloSender {

	@Autowired
	private AmqpTemplate amqpTemplate;

	@Scheduled(fixedRate = 3000)
	public void sendMessage1() {
		String content = "hello,I`m message 1; " + LocalDateTime.now();
		log.info("发送1:" + content);
		amqpTemplate.convertAndSend(CoreConf.exchange, CoreConf.message, content);
	}


	@Scheduled(fixedDelay = 3000)
	public void sendDomainObject() {
		Book book = new Book();
		book.setId(1L);
		book.setIsbn("send");
		book.setReader(LocalDateTime.now().toString());
		log.info("sendDomainObject:" + book);
		amqpTemplate.convertAndSend(CoreConf.topicHello, book);
	}


	@Scheduled(fixedRate = 3000)
	public void sendMessage3() {
		String content = "hello,I`m message 3; " + LocalDateTime.now();
		log.info("发送3:" + content);
		amqpTemplate.convertAndSend(CoreConf.exchange, CoreConf.messages, content);
	}

}
