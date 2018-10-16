package com.forest10.spring.boot.family.controller;

import com.alipay.api.AlipayApiException;
import com.forest10.spring.boot.family.service.AliPayService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Forest10
 * @date 2018/8/14 下午5:29
 */
@RestController
@RequestMapping("/alipay")
public class AliPayController {

    @Resource
    private AliPayService aliPayService;

    @GetMapping("/return_url")
    public String returnUrl() {
        return "return_url";
    }

    @PostMapping(value = "/notify_url")
    public String notifyUrl() {
        return "success";
    }

    /**
     * 手机网站跳转支付(直接调取客户端,如无使用账号填写)
     *
     * @param res
     * @throws IOException
     */
    @GetMapping("/wap")
    public void wapPay(HttpServletResponse res) throws IOException, AlipayApiException {
        aliPayService.wapPay(res);
    }

    /**
     * 电脑网站跳转支付(直接调取客户端,如无使用账号填写)
     *
     * @param res
     * @throws IOException
     */
    @GetMapping("/pc")
    public void pcPay(HttpServletResponse res) throws IOException, AlipayApiException {
        aliPayService.pcPay(res);
    }

}
