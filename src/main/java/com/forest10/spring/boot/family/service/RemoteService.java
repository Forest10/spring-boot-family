package com.forest10.spring.boot.family.service;

import com.forest10.spring.boot.family.common.User;
import feign.Param;
import feign.RequestLine;

/**
 * @author Forest10
 * @date 2018/10/23 18:07
 */
public interface RemoteService {

    @RequestLine("GET /?name={name}")
    User getOwner(@Param(value = "name") String name);
}

