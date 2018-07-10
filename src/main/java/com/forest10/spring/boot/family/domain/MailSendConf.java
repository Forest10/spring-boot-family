package com.forest10.spring.boot.family.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Forest10
 * @date 2018/7/10 下午7:52
 */
@Data
public class MailSendConf implements Serializable {

	private String to;
	private String subject;
	private String content;
}
