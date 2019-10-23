package com.forest10.spring.boot.family.logic.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author Forest10
 * @date 2019/10/23 22:58
 */
public class UserSavedEvent extends ApplicationEvent {

	public UserSavedEvent(Object source) {
		super(source);
	}
}
