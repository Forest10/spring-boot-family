package com.forest10.spring.boot.family.service.impl;

import com.forest10.spring.boot.family.service.DubboService;
import org.springframework.stereotype.Service;

/**
 * @author Forest10
 * @date 2018/9/4 下午2:30
 */
@Service
public class DubboServiceImpl implements DubboService {
	@Override
	public String quickSay(Integer name) {
		return "Hello" + name;
	}
}
