package com.forest10.spring.boot.family.provider.controller;

import com.forest10.spring.boot.family.bean.WxPayConfBean;
import com.forest10.spring.boot.family.provider.common.JsonResult;
import com.forest10.spring.boot.family.service.WxPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Forest10
 * @date 2018/8/28 12:52
 * Description
 */
@RestController
@RequestMapping("/security/wxpay")
public class WxSecurityController {

    @Autowired
    private WxPayService wxPayService;

    @RequestMapping("/preConfig")
    public JsonResult preConfig() {

        return JsonResult.success(wxPayService.getPreWxPayCofig());
    }

    @RequestMapping("/pay")
    public JsonResult pay(String orderId) throws Exception {
        WxPayConfBean wxPayConfBean = wxPayService.getWxPayConfBean(orderId);
        return JsonResult.success(wxPayConfBean);
    }

}
