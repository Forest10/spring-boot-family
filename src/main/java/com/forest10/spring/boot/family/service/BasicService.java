package com.forest10.spring.boot.family.service;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Forest10
 * @date 2019-07-07 16:39
 */
@Slf4j
@Service
public class BasicService {

	public String test() {
		String s = UUID.randomUUID().toString();
		log.info("s==>{}", s);
		int i = new Random().nextInt(10);
		try {
			TimeUnit.SECONDS.sleep(i);
		} catch (Exception e) {
			log.error("BasicService", e);
		}
		return s;
	}

}
