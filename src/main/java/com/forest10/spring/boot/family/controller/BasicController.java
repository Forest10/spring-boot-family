package com.forest10.spring.boot.family.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Forest10
 * @date 2018/9/4 下午2:28
 * 占位
 */
@RestController
public class BasicController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}
}
