package com.forest10.spring.boot.family.constants;

/**
 * @author Forest10
 * @date 2018/8/22 16:52
 * Description:微信支付的一些常量
 */
public interface WxPayConstant {
    /***加密方式***/
    public enum SignType {
        MD5, HMACSHA256
    }
     String HMACSHA256 = "HMAC-SHA256";
     String MD5 = "MD5";


    /***支付方式***/
    String TRADE_TYPE_NATIVE = "NATIVE";
    String TRADE_TYPE_JSAPI = "JSAPI";
    String TRADE_TYPE_H5 = "MWEB'";




    /****微信统一下单接口***/
    String UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    /***公众号支付里面的微信授权域名路径***/
    String AUTHORIZED_URL = "";
    /**通知URL**/
    String NOTIFY_URL = "http://wxpay.forest10.com/";

}
