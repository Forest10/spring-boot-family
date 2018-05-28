package com.forest10.spring.boot.family.common.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Forest10
 * @date 2018/5/17 下午2:45
 */
@Data
@Entity
public class UserAuth {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String username;
	private String password;

}
