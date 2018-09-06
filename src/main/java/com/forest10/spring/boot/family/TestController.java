package com.forest10.spring.boot.family;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Forest10
 * @date 2018/9/6 下午2:29
 */
@RestController
public class TestController {


	@RequestMapping("/rsync")
	public String rsync() {
		return "test rsync";
	}
}
