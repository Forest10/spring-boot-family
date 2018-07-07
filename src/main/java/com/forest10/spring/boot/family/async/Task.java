package com.forest10.spring.boot.family.async;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author Forest10
 * @date 2018/7/7 下午7:20
 */
@Slf4j
@Component
public class Task {


	@Async("taskExecutor")
	public void doTaskOne() throws InterruptedException {
		log.info("开始做任务一");
		Stopwatch stopwatch = Stopwatch.createStarted();
		TimeUnit.SECONDS.sleep(5);
		log.info("任务一OK.耗时{}毫秒", stopwatch.elapsed(TimeUnit.MILLISECONDS));
	}

	@Async("taskExecutor")
	public void doTaskTwo() throws InterruptedException {
		log.info("开始做任务二");
		Stopwatch stopwatch = Stopwatch.createStarted();
		TimeUnit.SECONDS.sleep(5);
		log.info("任务二OK.耗时{}毫秒", stopwatch.elapsed(TimeUnit.MILLISECONDS));
	}

	@Async("taskExecutor")
	public void doTaskThree() throws InterruptedException {
		log.info("开始做任务三");
		Stopwatch stopwatch = Stopwatch.createStarted();
		TimeUnit.SECONDS.sleep(5);
		log.info("任务三OK.耗时{}毫秒", stopwatch.elapsed(TimeUnit.MILLISECONDS));
	}

	@Async("taskExecutor")
	public Future<String> asyncMethodWithReturnType() {
		log.info("开始做有返回值的任务");
		Stopwatch stopwatch = Stopwatch.createStarted();
		System.out.println("Execute method asynchronously - " + Thread.currentThread().getName());
		try {
			Thread.sleep(5000);
			return new AsyncResult<>("hello world !!!!==>" + stopwatch.elapsed(TimeUnit.MILLISECONDS));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}

}
