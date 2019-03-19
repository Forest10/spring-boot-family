package com.forest10.spring.boot.family.service;

import com.forest10.spring.boot.family.domain.Book;

/**
 * @author Forest10
 * @date 2019-03-19 16:56
 */
public interface BookService {

	void addBookWithTrans(Book book);

	void addBookWithOutTrans(Book book);

	void save(Book book);

}
