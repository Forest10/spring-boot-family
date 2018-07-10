package com.forest10.spring.boot.family.controller;

import com.forest10.spring.boot.family.domain.MailSendConf;
import com.forest10.spring.boot.family.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 描述:
 * 书籍阅读 Controller
 *
 * @author Forest10
 * @date 2018/04/01 16:12
 */
@RestController
public class TestController {

	@Autowired
	private MailService mailService;

	@GetMapping(value = "/sendMail")
	public void send(@RequestBody MailSendConf mailSendConf) {
		mailService.sendSimpleMail(mailSendConf);
	}
}