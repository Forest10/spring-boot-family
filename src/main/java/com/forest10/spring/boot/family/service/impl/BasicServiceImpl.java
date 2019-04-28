package com.forest10.spring.boot.family.service.impl;

import com.forest10.spring.boot.family.entity.Book;
import com.forest10.spring.boot.family.repository.BookDao;
import com.forest10.spring.boot.family.service.BasicService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author Forest10
 * @date 2018/9/4 下午5:22
 */
@Service
public class BasicServiceImpl implements BasicService {

    @Resource
    private BookDao bookDao;

    @Override
    public List<Book> selectAll() {
        return bookDao.selectAll();
    }

    @Override
    public Long insertBook(Book book) {
        return bookDao.insertBook(book);
    }
}
