package com.forest10.spring.boot.family.util;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * @author Forest10
 * @date 2018/8/24 14:22
 * Description
 */
public class WxPayUtil {


    /**
     * 过滤掉空值和需要排除的key
     *
     * @param params
     * @return
     */
    private static Map<String, String> grepEmptyValueAndExcludeKey(Map<String, String> params) {
        List<String> excludeKeys = Lists.newArrayList("sign", "sign_type");
        Map<String, String> map = Maps.newHashMap();
        params.forEach((K, V) -> {
            if (Objects.nonNull(V) && !Objects.equals(V, "") && !excludeKeys.contains(K)) {
                map.put(K, V);
            }
        });
        return map;
    }

    /**
     * 按字典序拼接参数, [参数名=参数值, ...]; 字典序 key 区分大小写;
     *
     * @param params        参数及参数值
     * @param //excludeKeys 不参与拼接的key，有默认值
     */
    public static LinkedList<String> spliceSortedParamsList(Map<String, String> params) {
        Map<String, String> signPairs = grepEmptyValueAndExcludeKey(params);
        LinkedList<String> linkedList = new LinkedList<>();
        signPairs.entrySet().stream()
                .sorted(Map.Entry.<String, String>comparingByKey())
                .forEachOrdered(x -> linkedList.add(x.getKey() + "=" + x.getValue()));
        return linkedList;

    }

    /**
     * 签名
     * <p>
     * 参数名ASCII码从小到大排序（字典序）；
     * 如果参数的值为空不参与签名； 参数名区分大小写； secret
     * 参与排序，参与签名；
     *
     * @param params
     * @param key    分配给服务的对称密钥
     * @return
     */
    public static String signParams(Map<String, String> params, String key) {
        if (Objects.isNull(params) || StringUtils.isBlank(key)) {
            return null;
        }
        LinkedList<String> signPairs = spliceSortedParamsList(params);
        signPairs.add("key=" + key);
        return MD5Util.MD5Encode(String.join("&", signPairs)).toUpperCase();

    }

    /***
     * byte与16进制字符串的互相转换
     * @param hash
     * @return 转换后的16进制
     */
    public static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    /**
     * 创建一次性字符串
     *
     * @return 字符串
     */
    public static String createNonceStr() {
        return "5K8264ILTKCH16CQ2502SI8ZNMTM67VS";
    }

    /***
     * 拿到时间戳
     * @return 时间戳
     */
    public static String createTimestamp() {
        return "1535436863";
    }


}
