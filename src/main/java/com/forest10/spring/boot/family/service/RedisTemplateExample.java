package com.forest10.spring.boot.family.service;

import com.forest10.spring.boot.family.domain.Book;
import com.forest10.spring.boot.family.repository.ReadingListRepository;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Forest10
 * @date 2018/9/10 下午5:30
 */
@Slf4j
@Service
public class RedisTemplateExample {

    @Resource
    private RedisTemplate<String, String> redisCacheTemplate;
    @Resource
    private ReadingListRepository readingListRepository;

    @Resource
    private ListOperations<Object, Object> listOps;

    /**
     * 实验 list 操作
     */
    public void addLink(String userId, String url) {
        listOps.leftPush(userId, url);
    }

    /**
     * 测试 Redis事务
     */
    public void transaction(boolean throwEx) {

        //execute a transaction
        List<Object> txResults = redisCacheTemplate.execute(new SessionCallback<List<Object>>() {
            @Override
            public List<Object> execute(RedisOperations operations) throws DataAccessException {
                operations.multi();
                operations.opsForSet()
                    .add("txkey", "value1");
                operations.opsForSet()
                    .add("txkey", "value2");
                // This will contain the results of all ops in the transaction
                if (throwEx) {
                    int i = 1 / 0;
                }
                operations.opsForSet()
                    .add("txkey", "value3");
                return operations.exec();
            }
        });

        log.info("Number of items added to set: {}", txResults.get(0));
    }

    /**
     * 测试事务注解
     */
    @Transactional(rollbackFor = Exception.class)
    public void addBook(boolean throwEx) {
        listOps.leftPush("TransactionalKey", new Date() + "");
        log.info("redis complete");
        Book book = Book.builder()
            .isbn("33ee")
            .reader("ewe")
            .build();
        readingListRepository.save(book);
        log.info("mysql complete");

        if (throwEx) {
            int i = 1 / 0;
        }
    }

}


