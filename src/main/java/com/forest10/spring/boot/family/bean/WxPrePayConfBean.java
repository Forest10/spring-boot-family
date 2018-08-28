package com.forest10.spring.boot.family.bean;

import lombok.Data;

/**
 * @author Forest10
 * @date 2018/8/24 13:59
 * Description:微信JS-SDK进行wx.config时候所需参数实体bean
 */
@Data
public class WxPrePayConfBean {

    /**服务号appId**/
    private String appId;
    /**一次性随机串**/
    private String nonceStr;
    /**时间戳**/
    private String timestamp;
    /**JS-SDK权限验证的签名,需要先获取获得jsapi_ticket**/
    private String signature;
}