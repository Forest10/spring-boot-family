package com.forest10.spring.boot.family.controller;

import com.forest10.spring.boot.family.async.Task;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.Future;

/**
 * @author Forest10
 * @date 2018/7/7 下午7:34
 */
@RestController
public class TestController {

    @Resource
    private Task task;

    @GetMapping("/task")
    public String task() throws Exception {

        task.doTaskOne();
        task.doTaskTwo();
        task.doTaskThree();
        Future<String> stringFuture = task.asyncMethodWithReturnType();
        return stringFuture.get();
    }

}
