package com.forest10.spring.boot.family.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * @author Forest10
 * @date 2019-01-19 21:37
 */
@Slf4j
@Service
@Validated
public class MailService {

    @Async
    public void send() {
        log.info("发送邮件的线程名:{}", Thread.currentThread().getName());
    }

}
