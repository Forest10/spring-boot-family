package com.forest10.spring.boot.family.controller;

import com.forest10.spring.boot.family.domain.Book;
import com.forest10.spring.boot.family.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

    private static final String TEMPLATE_LIST = "book/list";
    private static final String ADD = "book/add";
    @Resource
    private BookService bookService;

    @GetMapping(value = "/")
    public String index() {
        return "redirect:/list";
    }

    @RequestMapping(value = "/list")
    public ModelAndView getList(ModelAndView modelAndView) {
        modelAndView.addObject("books", bookService.getAll());
        modelAndView.setViewName(TEMPLATE_LIST);
        return modelAndView;
    }

    @RequestMapping(value = "/add")
    public ModelAndView add(ModelAndView modelAndView) {
        modelAndView.setViewName(ADD);
        return modelAndView;
    }

    @PostMapping(value = "/addBook")
    public ModelAndView addBook(Book book) {
        bookService.save(book);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/list");
        return modelAndView;
    }
}