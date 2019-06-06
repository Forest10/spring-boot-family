package com.forest10.spring.boot.family.conf.mybatis.plugin;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Forest10
 * @date 2019-06-06 16:10
 */
public enum SqlDialectEnum {


	MYSQL(1, "mysql", "mysql数据库"),
	MSSQL(2, "msSql", "SQL SERVER数据库");

	private int code;
	private String name;
	private String desc;

	SqlDialectEnum(int code, String name, String desc) {
		this.code = code;
		this.name = name;
		this.desc = desc;
	}

	public static SqlDialectEnum getByName(String name) {
		for (SqlDialectEnum b : SqlDialectEnum.values()) {
			if (StringUtils.equals(name, b.getName())) {
				return b;
			}
		}
		return null;
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getDesc() {
		return desc;
	}


}
