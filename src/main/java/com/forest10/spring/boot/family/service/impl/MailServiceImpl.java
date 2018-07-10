package com.forest10.spring.boot.family.service.impl;

import com.forest10.spring.boot.family.domain.MailSendConf;
import com.forest10.spring.boot.family.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * @author Forest10
 * @date 2018/7/10 下午7:46
 */
@Component
public class MailServiceImpl implements MailService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private JavaMailSender mailSender;

	@Value("${spring.mail.fromMail.addr}")
	private String from;

	@Override
	public void sendSimpleMail(MailSendConf mailSendConf) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(mailSendConf.getTo());
		message.setSubject(mailSendConf.getSubject());
		message.setText(mailSendConf.getContent());
		try {
			mailSender.send(message);
			logger.info("简单邮件已经发送。");
		} catch (Exception e) {
			logger.error("发送简单邮件时发生异常！", e);
		}

	}
}