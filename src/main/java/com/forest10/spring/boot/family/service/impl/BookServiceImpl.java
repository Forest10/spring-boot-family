package com.forest10.spring.boot.family.service.impl;

import com.forest10.spring.boot.family.domain.Book;
import com.forest10.spring.boot.family.repository.ReadingListRepository;
import com.forest10.spring.boot.family.service.BookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Forest10
 * @date 2018/7/8 下午5:05
 */
@Service
public class BookServiceImpl implements BookService {

	@Resource
	private ReadingListRepository readingListRepository;

	@Override
	public List<Book> getAll() {
		return readingListRepository.findAll();
	}
}
