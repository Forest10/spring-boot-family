package com.forest10.spring.boot.family.service.multipleson.impl;

import com.forest10.spring.boot.family.service.multipleson.ITest;
import org.springframework.stereotype.Service;

/**
 * @author Forest10
 * @date 2019-01-31 11:17
 */
@Service("iTest2Impl")
public class ITest2Impl implements ITest {

    @Override
    public String get() {
        return "test2";
    }
}
