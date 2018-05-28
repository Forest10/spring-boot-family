package com.forest10.spring.boot.family.jwt.controller;

import com.forest10.spring.boot.family.common.domain.UserAuth;
import com.forest10.spring.boot.family.jwt.repository.UserAuthRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Forest10
 * @date 2018/5/28 下午6:14
 */
@RequestMapping("/users")
@RestController
public class UserController {


	@Resource
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Resource
	private UserAuthRepository userAuthRepository;


	@PostMapping("/signup")
	public void signUp(@RequestBody UserAuth user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userAuthRepository.save(user);
	}

	@RequestMapping("/hello")
	@ResponseBody
	public String hello(){
		return "hello";
	}

}
