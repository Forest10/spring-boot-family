package com.forest10.spring.boot.family.service.impl;

import com.forest10.spring.boot.family.domain.Book;
import com.forest10.spring.boot.family.repository.BookRepository;
import com.forest10.spring.boot.family.service.BookService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Forest10
 * @date 2019-03-19 16:57
 */
@Service
public class BookServiceImpl implements BookService {

	@Resource
	private BookRepository bookRepository;

	public void deleteAll() {
		bookRepository.deleteAll();
	}

	@Transactional
	public void addBookWithTrans(Book book) {
		save(book);
		throw new RuntimeException("addBookWithTrans");
	}


	public void addBookWithOutTrans(Book book) {
		save(book);
		throw new RuntimeException("addBookWithOutTrans");
	}


	@Transactional
	public void save(Book book) {
		bookRepository.save(book);
		throw new RuntimeException("哈哈哈");
	}
}
