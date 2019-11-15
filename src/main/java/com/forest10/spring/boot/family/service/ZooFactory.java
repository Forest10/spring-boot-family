package com.forest10.spring.boot.family.service;

import com.forest10.spring.boot.family.common.AnnimalTypeEnum;
import com.google.common.collect.Maps;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author Forest10
 * @date 2019-05-12 18:06
 */
@Component
public class ZooFactory {

    private Map<AnnimalTypeEnum, IZooHandle> map;

    @Resource
    private ApplicationContext applicationContext;

    @PostConstruct
    private void init() {
        Map<String, IZooHandle> beans = applicationContext
            .getBeansOfType(IZooHandle.class);
        map = Maps.newHashMapWithExpectedSize(beans.size());
        for (IZooHandle handle : beans.values()) {
            map.put(handle.supportedType(), handle);
        }
    }

    public IZooHandle getInstance(AnnimalTypeEnum typeEnum) {
        return map.getOrDefault(typeEnum, new DefaultAnnimal());
    }

    private final class DefaultAnnimal implements IZooHandle {

        @Override
        public String quark() {
            return "动物园未能找到对应的小动物,请选择捐赠!";
        }

        @Override
        public AnnimalTypeEnum supportedType() {
            return null;
        }
    }

}
