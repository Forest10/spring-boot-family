package com.forest10.spring.boot.family.controller;

import com.forest10.spring.boot.family.properties.CoreProperties;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Forest10
 * @date 2018/9/4 下午2:28 占位
 */
@Slf4j
@RestController
public class BasicController {


    @Resource
    private CoreProperties coreProperties;


    @GetMapping("/properties")
    public String properties() {
        return coreProperties.toString();
    }

    @RequestMapping("/")
    public String index() {
        return "new index";
    }


}
