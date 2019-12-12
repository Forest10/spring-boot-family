package com.forest10.spring.boot.family.controller;

import com.forest10.spring.boot.family.common.JsonResult;
import com.forest10.spring.boot.family.web.UserReq;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Forest10
 * @date 2018/9/4 下午2:28 占位
 */
@RestController
public class BasicController {


    @PostMapping("/addUser")
    public JsonResult addUser(@Valid @RequestBody UserReq req) {
        return JsonResult.success("提交成功", req);
    }

}
