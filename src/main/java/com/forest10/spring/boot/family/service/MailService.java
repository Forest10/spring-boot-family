package com.forest10.spring.boot.family.service;

import com.forest10.spring.boot.family.domain.MailSendConf;

/**
 * @author Forest10
 * @date 2018/7/10 下午7:46
 */
public interface MailService {

	/**
	 * 发送简易邮件
	 * @param mailSendConf
	 */
	void sendSimpleMail(MailSendConf mailSendConf);
}
