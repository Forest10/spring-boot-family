package com.forest10.spring.boot.family.provider.res;

import lombok.*;

/**
 * @author Forest10
 * @date 2018/8/25 17:46
 * Description:统一下单的返回结果:
 * https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_1
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class WxUnifiedorderRes {

    /**
     * SUCCESS/FAIL 此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
     **/
    private String return_code;

    /**
     * 当return_code为FAIL时返回信息为错误原因 ，例如签名失败参数格式校验错误
     **/
    private String return_msg;

    /**
     * 公众账号ID
     */
    private String appid;
    /**
     * 商户号
     */
    private String mch_id;

    /***随机字符串***/
    private String nonce_str;

    /***默认为MD5   https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=4_3***/
    private String sign;
    /**
     * 业务结果	 SUCCESS/FAIL
     **/
    private String result_code;

    /**
     * 交易类型 JSAPI 公众号支付 NATIVE 扫码支付 APP APP支付
     **/
    private String trade_type;

    /**
     * 微信生成的预支付会话标识，用于后续接口调用中使用，该值有效期为2小时
     **/
    private String prepay_id;

    /**
     * trade_type为NATIVE时有返回，用于生成二维码，展示给用户进行扫码支付
     **/
    private String code_url;


    /**
     * 当result_code为FAIL时返回错误代码
     */
    private String err_code;
    /**
     * 当result_code为FAIL时返回错误描述
     **/
    private String err_code_des;


}
