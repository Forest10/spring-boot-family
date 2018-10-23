package com.forest10.spring.boot.family.controller;

import com.forest10.spring.boot.family.common.User;
import com.forest10.spring.boot.family.properties.CoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;

/**
 * @author Forest10
 * @date 2018/9/4 下午2:28
 * 占位
 */
@RestController
public class BasicController {

    @Autowired
    private CoreProperties coreProperties;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/properties")
    public String properties() {
        return coreProperties.toString();
    }

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/get")
    public User get() {
        return restTemplate.getForObject(URI.create("http://127.0.0.1:8085"), User.class);
    }

}
