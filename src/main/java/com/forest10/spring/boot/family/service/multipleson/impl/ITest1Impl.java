package com.forest10.spring.boot.family.service.multipleson.impl;

import com.forest10.spring.boot.family.service.multipleson.ITest;
import org.springframework.stereotype.Service;

/**
 * @author Forest10
 * @date 2019-01-31 11:17
 */
@Service("iTest1Impl")
public class ITest1Impl implements ITest {

    @Override
    public String get() {
        return "test1";
    }
}
