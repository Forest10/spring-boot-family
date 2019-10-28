package com.forest10.spring.boot.family.service;

import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author Forest10
 * @date 2019/10/28 12:45
 */
@Slf4j
@Service
public class BasicService {


	@Scheduled(cron = "")
	public void sc() {
		log.info("呵呵:{}", UUID.randomUUID().toString());
	}

}
