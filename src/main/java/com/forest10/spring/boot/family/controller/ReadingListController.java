package com.forest10.spring.boot.family.controller;

import com.forest10.spring.boot.family.api.pojo.JsonResult;
import com.forest10.spring.boot.family.domain.Book;
import com.forest10.spring.boot.family.repository.ReadingListRepository;
import com.forest10.spring.boot.family.service.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
		return JsonResult.success("添加成功", readingService.getAll());
	}

}