package com.forest10.spring.boot.family.service.impl;

import com.forest10.spring.boot.family.service.BasicService;
import org.springframework.stereotype.Service;

/**
 * @author Forest10
 * @date 2018/9/4 下午2:30
 */
@Service
public class BasicServiceImpl implements BasicService {
	@Override
	public String sayHello(String name) {
		return "Hello " + name;
	}
}
