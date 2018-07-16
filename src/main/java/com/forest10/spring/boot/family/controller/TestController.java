package com.forest10.spring.boot.family.controller;

import com.forest10.spring.boot.family.sendAndReceiver.HelloSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Forest10
 * @date 2018/7/16 下午5:24
 */
@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	private HelloSender helloSender;


	@GetMapping("/sendmq")
	public void test() {
		helloSender.send();
	}
}
