package com.forest10.spring.boot.family.service.common.impl;

import com.forest10.spring.boot.family.service.common.ICommonInterface;
import org.springframework.stereotype.Service;

/**
 * @author Forest10
 * @date 2019-01-31 11:17
 */
@Service
public class ICommonImpl implements ICommonInterface {

    @Override
    public String get() {
        return "ICommon";
    }
}
