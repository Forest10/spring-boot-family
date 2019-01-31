package com.forest10.spring.boot.family.service.multipleson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author Forest10
 * @date 2019-01-31 11:19
 */
@Service
public class CommonServiceWithMultipleSonResource extends AbstractServiceWithMultipleSonResource {


    @Autowired
    public void inject(@Qualifier("iTest2Impl") ITest iTest) {
        super.iTest = iTest;
    }

    public String commonSay() {
        return say();
    }

}
