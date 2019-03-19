package com.forest10.spring.boot.family.repository;

import com.forest10.spring.boot.family.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 描述:
 * 书籍阅读数据库接口
 *
 * @author Forest10
 * @date 2018/04/01 16:11
 */
public interface BookRepository extends JpaRepository<Book, Long> {


}