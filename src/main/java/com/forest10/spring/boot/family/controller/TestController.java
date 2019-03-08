package com.forest10.spring.boot.family.controller;

import com.forest10.spring.boot.family.async.LocalTaskService;
import com.forest10.spring.boot.family.async.MailService;
import java.util.concurrent.Future;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Forest10
 * @date 2018/7/7 下午7:34
 */
@RestController
public class TestController {

    @Resource
    private LocalTaskService localTaskService;
    @Resource
    private MailService mailService;

    @GetMapping("/asyncLocalTask")
    public String asyncLocalTask() throws Exception {

        localTaskService.doTaskOne();
        localTaskService.doTaskTwo();
        localTaskService.doTaskThree();
        Future<String> stringFuture = localTaskService.asyncMethodWithReturnType();
        return stringFuture.get();
    }

    @GetMapping("/mail")
    public void mail() {
        mailService.send();
    }

}
