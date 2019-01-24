package com.forest10.spring.boot.family.controller;

import com.forest10.spring.boot.family.domain.MailSendConf;
import com.forest10.spring.boot.family.service.MailService;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

	@PostMapping(value = "/sendMail")
	public void send(@RequestBody MailSendConf mailSendConf) {
		mailService.sendSimpleMail(mailSendConf);
	}

	@Autowired
	StringEncryptor stringEncryptor;

	@GetMapping(value = "/encryptPwd")
	public void encryptPwd() {
		String result = stringEncryptor.encrypt("wgugtwsgopexhcij");
		System.out.println("==================");
		System.out.println(result);
		System.out.println("==================");
	}
}