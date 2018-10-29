package com.forest10.spring.boot.family.service.impl;

import com.forest10.spring.boot.family.domain.Book;
import com.forest10.spring.boot.family.repository.ReadingListRepository;
import com.forest10.spring.boot.family.service.ReadingService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Forest10
 * @date 2018/7/8 上午9:05
 */
@Service
//命名空间(整体,也可单独加在各个方法)
@CacheConfig(cacheNames = "Book")
public class ReadingServiceImpl implements ReadingService {

    @Resource
    private ReadingListRepository readingListRepository;

    @Cacheable
    @Override
    public List<Book> getAll() {
        return readingListRepository.findAll();
    }
}
