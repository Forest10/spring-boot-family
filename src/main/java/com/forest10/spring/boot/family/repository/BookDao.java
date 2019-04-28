package com.forest10.spring.boot.family.repository;

import com.forest10.spring.boot.family.entity.Book;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Forest10
 * @date 2019-03-08 23:49
 */
@Mapper
public interface BookDao {

    List<Book> selectAll();

    Long insertBook(Book book);

}
