package com.forest10.spring.boot.family.conf;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.forest10.spring.boot.family.domain.AliPayConfBean;
import com.forest10.spring.boot.family.repository.AliPayConfRepository;
import com.google.common.base.Preconditions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * 配置中心
 *
 * @author Forest10
 * @date 2018/7/16 下午5:15
 */
@Configuration
public class CoreConf {


	public static final String ALI_PAY_TYPE = "aliPay";
	@Resource
	private AliPayConfRepository aliPayConfRepository;


	@Bean
	public AlipayClient alipayClient() {
		AliPayConfBean aliPayConf = aliPayConfRepository.findByType(ALI_PAY_TYPE);
		Preconditions.checkNotNull(aliPayConf, "aliPayConf can`t be null");
		return new DefaultAlipayClient(aliPayConf.getApiGateWay(), aliPayConf.getAppId(), aliPayConf.getPrivateKey(), "json", "utf-8", aliPayConf.getPublicKey(), "RSA2");
	}
}
