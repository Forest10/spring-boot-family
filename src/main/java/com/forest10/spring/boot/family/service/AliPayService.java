package com.forest10.spring.boot.family.service;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.forest10.spring.boot.family.conf.CoreConf;
import com.forest10.spring.boot.family.domain.AliPayConfBean;
import com.forest10.spring.boot.family.repository.AliPayConfRepository;
import com.google.common.base.Preconditions;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Forest10
 * @date 2018/8/15 下午2:20
 */
@Service
public class AliPayService {


	@Resource
	private AlipayClient alipayClient;

	@Resource
	private AliPayConfRepository aliPayConfRepository;

	/**
	 * 手机网站跳转支付(直接调取客户端,如无使用账号填写)
	 *
	 * @param httpResponse
	 * @throws IOException
	 */
	public void wapPay(HttpServletResponse httpResponse) throws IOException, AlipayApiException {
		AliPayConfBean aliPayConf = aliPayConfRepository.findByType(CoreConf.ALI_PAY_TYPE);
		Preconditions.checkNotNull(aliPayConf, "aliPayConf can`t be null");
		//创建API对应的request
		AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();
		//在公共参数中设置回跳和通知地址
		alipayRequest.setReturnUrl(aliPayConf.getReturnUrl());
		alipayRequest.setNotifyUrl(aliPayConf.getNotifyUrl());
		JSONObject bizContent = new JSONObject();
		bizContent.put("out_trade_no", System.currentTimeMillis());
		bizContent.put("total_amount", 0.01);
		bizContent.put("subject", "iphone");
		bizContent.put("product_code", "QUICK_WAP_PAY");
		//填充业务参数
		alipayRequest.setBizContent(bizContent.toJSONString());
		//调用SDK生成表单
		generateAliPayRes(httpResponse, alipayClient.pageExecute(alipayRequest).getBody());
	}

	/**
	 * 电脑网站跳转支付(电脑展示付款码,如无使用账号填写)
	 *
	 * @param httpResponse
	 * @throws IOException
	 */
	public void pcPay(HttpServletResponse httpResponse) throws IOException, AlipayApiException {
		AliPayConfBean aliPayConf = aliPayConfRepository.findByType(CoreConf.ALI_PAY_TYPE);
		Preconditions.checkNotNull(aliPayConf, "aliPayConf can`t be null");
		//创建API对应的request
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		//在公共参数中设置回跳和通知地址
		alipayRequest.setReturnUrl(aliPayConf.getReturnUrl());
		alipayRequest.setNotifyUrl(aliPayConf.getNotifyUrl());
		JSONObject bizContent = new JSONObject();
		bizContent.put("out_trade_no", System.currentTimeMillis());
		bizContent.put("total_amount", 0.01);
		bizContent.put("subject", "mac");
		bizContent.put("passback_params", "merchantBizType%3d3C%26merchantBizNo%3d2016010101111");
		bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");
		JSONObject extendParams = new JSONObject();
		extendParams.put("sys_service_provider_id", "2088511833207846");
		bizContent.put("extend_params", extendParams);
		//填充业务参数
		alipayRequest.setBizContent(bizContent.toJSONString());
		//调用SDK生成表单
		generateAliPayRes(httpResponse, alipayClient.pageExecute(alipayRequest).getBody());

	}


	private void generateAliPayRes(HttpServletResponse httpResponse, String form) throws IOException {
		httpResponse.setContentType("text/html;charset=UTF-8");
		//直接将完整的表单html输出到页面
		httpResponse.getWriter().write(form);
		httpResponse.getWriter().flush();
		httpResponse.getWriter().close();
	}

}
