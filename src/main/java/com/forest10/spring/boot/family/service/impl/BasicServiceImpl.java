package com.forest10.spring.boot.family.service.impl;

import com.forest10.spring.boot.family.properties.CoreProperties;
import com.forest10.spring.boot.family.service.BasicService;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Forest10
 * @date 2018/9/4 下午5:22
 */
@Service
public class BasicServiceImpl implements BasicService {

	@Autowired
	private Auth qiniuAuth;

	@Autowired
	private CoreProperties coreProperties;

	@Override
	public String basicToken() {


		return qiniuAuth.uploadToken(coreProperties.getBucket());
	}
}
