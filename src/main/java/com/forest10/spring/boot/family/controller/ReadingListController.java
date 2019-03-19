package com.forest10.spring.boot.family.controller;

import com.forest10.spring.boot.family.domain.Book;
import com.forest10.spring.boot.family.service.impl.BookServiceImpl;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	private BookServiceImpl bookService;

	@RequestMapping(value = "/addBookWithTrans", method = RequestMethod.POST)
	public void addBookWithTrans(@RequestBody Book book) {
		bookService.addBookWithTrans(book);
	}

	@RequestMapping(value = "/addBookWithOutTrans", method = RequestMethod.POST)
	public void addBook(@RequestBody Book book) {
		bookService.addBookWithOutTrans(book);
	}

	@GetMapping(value = "/deleteAll")
	public void deleteAll() {
		bookService.deleteAll();
	}

}