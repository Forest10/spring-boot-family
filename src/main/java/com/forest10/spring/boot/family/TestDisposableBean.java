package com.forest10.spring.boot.family;

/**
 * @author Forest10
 * @date 2018/9/27 10:52 AM
 */

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

@Component
public class TestDisposableBean implements DisposableBean {

    @Override

    public void destroy() throws Exception {

        System.out.println("测试 Bean 已销毁 ...");

    }

}