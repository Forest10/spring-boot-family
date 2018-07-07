package com.forest10.spring.boot.family.selfantion;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 描述:
 * Api限流注解
 *
 * @author Forest10
 * @date 2018/04/01 16:12
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestLimitRate {

	boolean limit() default true;
}
