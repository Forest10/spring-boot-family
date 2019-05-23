package com.forest10.spring.boot.family.controller;

import java.util.Base64;
import javax.annotation.Resource;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Forest10
 * @date 2018/9/4 下午2:28 占位
 */
@RestController
public class BasicController {

    @Resource
    private StringEncryptor stringEncryptor;

    @GetMapping("/encryptPwd")
    public String passwd(String base64edPwd) {
        return stringEncryptor.encrypt(new String(Base64.getDecoder().decode(base64edPwd)));
    }

}
