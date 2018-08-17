package com.forest10.spring.boot.family.repository;

import com.forest10.spring.boot.family.domain.AliPayConfBean;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Forest10
 * @date 2018/04/01 16:11
 */
public interface AliPayConfRepository extends JpaRepository<AliPayConfBean, Long> {

	/**
	 * 根据支付类型查找
	 *
	 * @param type
	 * @return
	 */
	AliPayConfBean findByType(String type);

}