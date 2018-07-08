package com.forest10.spring.boot.family.controller;

import com.forest10.spring.boot.family.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 描述:
 * 书籍阅读 Controller
 *
 * @author Forest10
 * @date 2018/04/01 16:12
 */
@Controller
public class ReadingListController {

	@Resource
	private BookService bookService;

	private static final String TEMPLATE_INDEX = "index";

	@RequestMapping(value = "/")
	public String index() {
		return TEMPLATE_INDEX;
	}

	@RequestMapping(value = "/list")
	public String getList(Model model) {
		model.addAttribute("books", bookService.getAll());
		return "book/list";
	}
}