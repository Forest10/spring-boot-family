package com.forest10.spring.boot.family.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * @author Forest10
 * @date 2019/10/16 16:37
 */
@Data
public class User implements Serializable {


	private String name;
	private String pwd;
}
