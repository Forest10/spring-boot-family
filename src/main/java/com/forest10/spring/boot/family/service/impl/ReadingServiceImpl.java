package com.forest10.spring.boot.family.service.impl;

import com.forest10.spring.boot.family.domain.Book;
import com.forest10.spring.boot.family.repository.ReadingListRepository;
import com.forest10.spring.boot.family.service.ReadingService;
import org.apache.commons.collections4.CollectionUtils;
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
