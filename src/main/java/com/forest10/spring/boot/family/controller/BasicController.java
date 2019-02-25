package com.forest10.spring.boot.family.controller;

import com.alibaba.fastjson.JSONObject;
import com.forest10.spring.boot.family.common.JsonResult;
import com.forest10.spring.boot.family.properties.CoreProperties;
import java.util.Base64;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${spring.profiles.active}")
    private String activeProfile;
    @Value("${jasypt.encryptor.password}")
    private String jasyptencryptorpassword;

    @Resource
    StringEncryptor stringEncryptor;

    @GetMapping("/activeProfile")
    public String activeProfile() {
        return activeProfile;
    }


    @GetMapping("/properties")
    public String properties() {
        return coreProperties.toString();
    }

    @RequestMapping("/")
    public String index() {
        return "new index";
    }

    @GetMapping("/jasyptencryptorpassword")
    public String jasyptencryptorpassword() {
        return jasyptencryptorpassword;
    }

    @GetMapping(value = "/encryptPwd")
    public JsonResult encryptPwd(String base64edPwd) {
        String pwd = new String(Base64.getDecoder().decode(base64edPwd));
        String encrypt = stringEncryptor.encrypt(pwd);
        log.info("传入参数:{},加密后:{}", pwd, encrypt);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pwd", pwd);
        jsonObject.put("encrypt", encrypt);
        return JsonResult.success(jsonObject);

    }


}
