package com.forest10.spring.boot.family.controller;

import com.forest10.spring.boot.family.common.JsonResult;
import com.forest10.spring.boot.family.service.IZooHandle;
import com.forest10.spring.boot.family.service.ZooFactory;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Forest10
 * @date 2018/9/4 下午2:28 占位
 */
@RestController
@RequestMapping("/zoo")
public class BasicController {


    @Resource
    private ZooFactory zooFactory;

    @PostMapping("/quark")
    public JsonResult quark(@RequestBody ZooReq req) {
        IZooHandle zooAnimal = zooFactory.getInstance(req.getAnnimalTypeEnum());
        return JsonResult.success(zooAnimal.quark());
    }


}
