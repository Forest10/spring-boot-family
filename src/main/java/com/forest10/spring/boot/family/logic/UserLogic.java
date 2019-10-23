package com.forest10.spring.boot.family.logic;

import com.forest10.spring.boot.family.logic.event.UserSavedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

/**
 * @author Forest10
 * @date 2019/10/23 23:04
 */
@Slf4j
@Service
public class UserLogic implements ApplicationEventPublisherAware {

	private ApplicationEventPublisher applicationEventPublisher;

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}


	public void reg() {
		log.info("user reg");
		applicationEventPublisher.publishEvent(new UserSavedEvent(applicationEventPublisher));
	}
}

