package com.forest10.spring.boot.family.service.impl;

import com.forest10.spring.boot.family.service.BasicService;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

/**
 * @author Forest10
 * @date 2018/9/4 下午5:22
 */
@Slf4j
@Service
public class BasicServiceImpl implements BasicService {

    private static final String LOCK_KEY = "LOCK_KEY";
    @Resource
    private RedissonClient redissonClient;

    @Override
    public void doWithLock() {
        RLock lock = redissonClient.getLock(LOCK_KEY);
        if (lock.tryLock()) {
            log.info("Thread:{}-->doWithLock抢到锁", Thread.currentThread().getId());
            try {
                TimeUnit.SECONDS.sleep(31L);
            } catch (InterruptedException e) {
                log.error("获取锁执行操作时候出现异常", e);
            } finally {
                log.info("Thread:{}-->doWithLock finally", Thread.currentThread().getId());
                lock.unlock();
            }
        } else {
            log.info("Thread:{}-->doWithLock没抢到锁", Thread.currentThread().getId());
        }
        log.info("Thread:{}-->doWithLock执行完毕", Thread.currentThread().getId());
    }

    @Override
    public String doWithLockReturn() {
        RLock lock = redissonClient.getLock(LOCK_KEY);
        if (lock.tryLock()) {
            try {
                TimeUnit.SECONDS.sleep(60L);
            } catch (InterruptedException e) {
                log.error("获取锁执行操作时候出现异常", e);
            } finally {
                lock.unlock();
            }
        } else {
            log.info("doWithLockReturn没抢到锁");
        }
        return "hi";
    }
}
