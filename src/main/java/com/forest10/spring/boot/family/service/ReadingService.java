package com.forest10.spring.boot.family.service;

import com.forest10.spring.boot.family.domain.Book;

import java.util.List;

/**
 * @author Forest10
 * @date 2018/7/8 上午9:04
 */
public interface ReadingService {

	List<Book> getAll();

}
