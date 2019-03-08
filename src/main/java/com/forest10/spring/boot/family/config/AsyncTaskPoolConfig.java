package com.forest10.spring.boot.family.config;

import com.forest10.spring.boot.family.async.MailService;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author Forest10
 * @date 2018/7/7 下午7:16
 */
@Slf4j
@EnableAsync
@Configuration
public class AsyncTaskPoolConfig extends AsyncConfigurerSupport {

    @Lazy
    @Resource
    private MailService mailService;


    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(100);
        executor.setKeepAliveSeconds(60);
        executor.setThreadNamePrefix("Hi-AsyncExecutor-");
        executor.setRejectedExecutionHandler(getRejectedExecutionHandler());
        //防止任务未执行完毕就关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(60);
        return executor;
    }


    private RejectedExecutionHandler getRejectedExecutionHandler() {
        return new SpringRejectedExecutionHandler();
    }

    private void sendMail(String setSubject, String content) {
        mailService.send();
    }

    class SpringRejectedExecutionHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            if (!executor.isShutdown()) {
                r.run();
            }
            log.error("rejectedTask in async method");
            sendMail("发生线程池堵塞", "发生线程池堵塞.由主线程自己执行");
        }
    }

}
