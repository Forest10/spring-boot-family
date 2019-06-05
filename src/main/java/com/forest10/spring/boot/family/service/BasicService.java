package com.forest10.spring.boot.family.service;

import com.forest10.spring.boot.family.entity.Book;
import java.util.List;

/**
 * @author Forest10
 * @date 2018/9/4 下午2:28 占位
 */
public interface BasicService {

	List<Book> selectAll();

	Long insertBook(Book book);


}
