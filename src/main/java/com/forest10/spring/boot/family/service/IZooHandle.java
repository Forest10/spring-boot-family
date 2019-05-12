package com.forest10.spring.boot.family.service;

import com.forest10.spring.boot.family.common.AnnimalTypeEnum;

/**
 * @author Forest10
 * @date 2018/9/4 下午2:28
 * 请注意子类没有public.必须使用ZooFactory获取
 */
public interface IZooHandle {


    /**
     * 叫声-- 🐩:汪汪叫 🐱:喵喵叫
     */
    String quark();


    /**
     * 实现类支持的动物类型
     */
    AnnimalTypeEnum supportedType();
}
