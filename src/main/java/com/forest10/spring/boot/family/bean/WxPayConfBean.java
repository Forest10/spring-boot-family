package com.forest10.spring.boot.family.bean;

import lombok.Data;

/**
 * @author Forest10
 * @date 2018/8/24 14:12
 * Description: 真正发起微信支付时候需要的参数Bean
 */
@Data
public class WxPayConfBean {

    /**服务号appId**/
    private String appId;
    /**时间戳**/
    private String timeStamp;
    /**一次性随机串**/
    private String nonceStr;
    /***统一下单接口返回的prepay_id参数值，提交格式如：prepay_id=XXX**/
    private String packageStr;
    /**签名类型，默认为MD5，支持HMAC-SHA256和MD5。注意此处需与统一下单的签名类型一致**/
    private String signType;
    /***使用支付时的签名:https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=4_3***/
    private String paySign;
}
