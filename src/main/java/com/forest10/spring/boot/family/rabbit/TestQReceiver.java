package com.forest10.spring.boot.family.rabbit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @author Forest10
 * @date 2019-05-23 17:37
 */
@Slf4j
@Component
public class TestQReceiver {


    @RabbitListener(queues = "TestQ")
    public void processMessage(@Payload byte[] message) {
        log.info("TestQ content:{}", new String(message));
    }
}
