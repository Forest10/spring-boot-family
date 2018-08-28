package com.forest10.spring.boot.family.provider.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Forest10
 * @date 2018/8/28 12:52
 * Description
 */
@Controller
public class WxIndexController {

    @RequestMapping("/wxpay/{orderId}")
    public String index(@PathVariable(value = "orderId") String orderId,
                        Model model) {
        model.addAttribute("orderId", orderId);
        return "wxpay";
    }

    @RequestMapping("/wxpay/openId")
    public String openId(String code) {

        return code;
    }


    @RequestMapping("/seaconfig")
    public String seaconfig() {
        return "seaconfig";
    }
}
