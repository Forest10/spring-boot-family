package com.forest10.spring.boot.family.service.multipleson;


/**
 * @author Forest10
 * @date 2019-01-31 11:18
 */
public abstract class AbstractServiceWithMultipleSonResource {

    /**
     * this interface has more than one son
     */
    protected ITest iTest;


    protected final String say() {
        return iTest.get();
    }

}
