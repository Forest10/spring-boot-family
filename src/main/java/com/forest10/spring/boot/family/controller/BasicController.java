package com.forest10.spring.boot.family.controller;

import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Forest10
 * @date 2018/9/4 下午2:28 占位
 */
@RestController
public class BasicController {

	@GetMapping("/")
	public String index() {
		return "呵呵" + UUID.randomUUID().toString();
	}

}
