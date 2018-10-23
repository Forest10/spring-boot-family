package com.forest10.spring.boot.family.controller;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.forest10.spring.boot.family.common.User;
import com.forest10.spring.boot.family.properties.CoreProperties;
import com.forest10.spring.boot.family.service.RemoteService;
import com.google.common.collect.Lists;
import feign.Feign;
import feign.Request;
import feign.Retryer;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
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
    public User index() {


        RemoteService service = Feign.builder()
            .encoder(new JacksonEncoder())
            .decoder(new JacksonDecoder(Lists.newArrayList(new JavaTimeModule())))
            .options(new Request.Options(1000, 3500))
            .retryer(new Retryer.Default(5000, 5000, 3))
            .target(RemoteService.class, "http://127.0.0.1:8085");
        return service.getOwner("fff");
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
