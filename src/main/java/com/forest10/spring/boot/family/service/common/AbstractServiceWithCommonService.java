package com.forest10.spring.boot.family.service.common;


import javax.annotation.Resource;

/**
 * @author Forest10
 * @date 2019-01-31 11:18
 */
public abstract class AbstractServiceWithCommonService {

    /**
     * this interface has only one Son and you can treat it as itself`s behave
     */
    @Resource
    protected ICommonInterface iCommonInterface;


    protected final String say() {
        return iCommonInterface.get();
    }

}
