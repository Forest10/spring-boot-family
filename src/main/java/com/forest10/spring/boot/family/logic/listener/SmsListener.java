package com.forest10.spring.boot.family.logic.listener;

import com.forest10.spring.boot.family.logic.event.UserSavedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author Forest10
 * @date 2019/10/23 23:00
 */
@Slf4j
@Component
public class SmsListener implements ApplicationListener<UserSavedEvent> {

	@Override
	public void onApplicationEvent(UserSavedEvent event) {
		log.info("send sms");
	}
}
