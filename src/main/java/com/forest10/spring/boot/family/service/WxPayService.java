package com.forest10.spring.boot.family.service;

import com.forest10.spring.boot.family.bean.WxPayConfBean;
import com.forest10.spring.boot.family.bean.WxPrePayConfBean;
import com.forest10.spring.boot.family.constants.WxPayConstant;
import com.forest10.spring.boot.family.constants.WxPaySecurity;
import com.forest10.spring.boot.family.util.HttpSendAndRecieve;
import com.forest10.spring.boot.family.util.WXPayXmlUtil;
import com.forest10.spring.boot.family.util.WxPayUtil;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author Forest10
 * @date 2018/8/28 13:30
 * Description:为了测试,一切从简
 */
@Service
public class WxPayService {

    /**
     * 进入到微信支付页面需要的config
     *
     * @return
     */
    public WxPrePayConfBean getPreWxPayCofig() {
        String jsapi_ticket = "需要Redis进行缓存";
        String nonce_str = WxPayUtil.createNonceStr();
        String timestamp = WxPayUtil.createTimestamp();
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        Map<String, String> paramsMap = Maps.newLinkedHashMap();
        paramsMap.put("jsapi_ticket", jsapi_ticket);
        paramsMap.put("noncestr", nonce_str);
        paramsMap.put("timestamp", timestamp);
        paramsMap.put("url", WxPayConstant.AUTHORIZED_URL);

        LinkedList<String> signPairs = WxPayUtil.spliceSortedParamsList(paramsMap);
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(String.join("&", signPairs).getBytes(StandardCharsets.UTF_8));
            signature = WxPayUtil.byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        WxPrePayConfBean WxPrePayConfBean = new WxPrePayConfBean();
        WxPrePayConfBean.setAppId(WxPaySecurity.APP_ID);
        WxPrePayConfBean.setNonceStr(nonce_str);
        WxPrePayConfBean.setTimestamp(timestamp);
        WxPrePayConfBean.setSignature(signature);

        return WxPrePayConfBean;
    }


    /**
     * @param totalFee:总价格        分
     * @param body:识别
     * @param orderId:订单Id
     * @param openId:公众号关注者openId
     * @return
     */
    public Map<String, String> getPrepareId(int totalFee, String body, String orderId,
                                            String openId) throws Exception {
        // 构造请求参数
        Map<String, String> params = Maps.newHashMap();
        params.put("appid", WxPaySecurity.APP_ID);
        params.put("mch_id", WxPaySecurity.MERCHANT_ID);
        params.put("nonce_str", WxPayUtil.createNonceStr());
        params.put("body", body);
        params.put("openid", openId);
        params.put("out_trade_no", orderId);
        params.put("total_fee", totalFee + "");

        //应该使用IpUtil获取,便于后期做黑名单拦截
        params.put("spbill_create_ip", "127.0.0.1");
        params.put("notify_url", WxPayConstant.NOTIFY_URL);
        params.put("trade_type", WxPayConstant.TRADE_TYPE_JSAPI);

        // 签名
        params.put("sign", WxPayUtil.signParams(params, WxPaySecurity.MERCHANT_KEY));

//        // 请求微信生成预支付订单，获取支付链接
        String returnXml = HttpSendAndRecieve.sendPost(WxPayConstant.UNIFIED_ORDER_URL, WXPayXmlUtil.mapToXml(params));


        return WXPayXmlUtil.xmlToMap(returnXml);

    }
    /***
     * appId, timeStamp, nonceStr, package, signType。
     timestamp: 0, // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。
     但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
     nonceStr: '', // 支付签名随机串，不长于 32 位
     package: '', // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=***）
     signType: '', // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
     paySign: '', // 支付签名
     */
    /***
     * 真正发起微信支付
     *
     * @return
     */
    public WxPayConfBean getWxPayConfBean(String orderId) throws Exception {
        Map<String, String> packageStr = getPrepareId(
                1, "Forest10测试", orderId, "暂时使用的openId");

        String timestamp = WxPayUtil.createTimestamp();
        String nonce_str = WxPayUtil.createNonceStr();
        Map<String, String> paramsMap = Maps.newHashMap();

        paramsMap.put("appId", WxPaySecurity.APP_ID);
        paramsMap.put("timeStamp", timestamp);
        paramsMap.put("nonceStr", nonce_str);
        paramsMap.put("package", "prepay_id=" + packageStr.get("prepay_id"));
        paramsMap.put("signType", WxPayConstant.MD5);
        String paySign = WxPayUtil.signParams(paramsMap, WxPaySecurity.MERCHANT_KEY);

        WxPayConfBean WxPayConfBean = new WxPayConfBean();
        WxPayConfBean.setAppId(WxPaySecurity.APP_ID);
        WxPayConfBean.setTimeStamp(timestamp);
        WxPayConfBean.setNonceStr(nonce_str);
        WxPayConfBean.setPackageStr("prepay_id=" + packageStr);
        WxPayConfBean.setSignType("MD5");
        WxPayConfBean.setPaySign(paySign);

        return WxPayConfBean;
    }


}
