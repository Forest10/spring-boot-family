package com.forest10.spring.boot.family.controller;

import com.forest10.spring.boot.family.common.JsonResult;
import com.forest10.spring.boot.family.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Forest10
 * @date 2019/10/16 16:36
 */

@Api(description = "关于账号的相关请求")
@RestController
@RequestMapping(value = "/user")
public class UserController {


	@ApiOperation(value = "注册", notes = "用于用户注册")
	@PostMapping(value = "/register")
	public JsonResult register(@RequestBody User user) throws Exception {
		return JsonResult.success("注册成功");
	}

	@ApiOperation(value = "登录", notes = "用于用户登录")
	@PostMapping(value = "/login")
	public JsonResult login(@ApiParam(value = "用户名") String userName, @ApiParam(value = "密码") String password) throws Exception {
		return JsonResult.success("登录成功");
	}
}