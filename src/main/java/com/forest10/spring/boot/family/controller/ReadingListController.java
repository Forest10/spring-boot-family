package com.forest10.spring.boot.family.controller;

import com.forest10.spring.boot.family.api.pojo.JsonResult;
import com.forest10.spring.boot.family.service.ReadingService;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 * 书籍阅读 Controller
 *
 * @author Forest10
 * @date 2018/04/01 16:12
 */
@RestController
public class ReadingListController {

    @Resource
    private ReadingService readingService;

    @GetMapping(value = "/getAll")
    public JsonResult getAllBooks() {
        return JsonResult.success("SUCCESS", readingService.getAll());
    }

}