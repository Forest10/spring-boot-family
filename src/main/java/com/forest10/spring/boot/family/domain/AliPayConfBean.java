package com.forest10.spring.boot.family.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Forest10
 * @date 2018/8/15 下午3:28
 */
@Data
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
public class AliPayConfBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String appId;
	@Column(nullable = false)
	private String publicKey;
	@Column(nullable = false)
	private String privateKey;
	@Column(nullable = false)
	private String apiGateWay;
	/**支付类型: aliPay**/
	@Column(nullable = false)
	private String type;
	@Column(nullable = false)
	private String returnUrl;
	@Column(nullable = false)
	private String notifyUrl;

}
