package com.forest10.spring.boot.family.config;

import lombok.Data;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by forest10 on 2017/7/18.
 */
@Data
public class SimpleRateLimiter {
	//红绿灯
	private Semaphore semaphore;
	//最大限流
	private int maxPermits;
	//延时单位
	private TimeUnit timePeriod;
	//线程控制(真正执行者)
	private ScheduledExecutorService scheduler;
	private Long executeTime;

	public static SimpleRateLimiter create(int permits, TimeUnit timePeriod) {
		SimpleRateLimiter limiter = new SimpleRateLimiter(permits, timePeriod);
		limiter.schedulePermitReplenishment();
		limiter.setExecuteTime(System.currentTimeMillis());
		return limiter;
	}

	private SimpleRateLimiter(int permits, TimeUnit timePeriod) {
		this.semaphore = new Semaphore(permits, Boolean.TRUE);
		this.maxPermits = permits;
		this.timePeriod = timePeriod;
	}

	public boolean tryAcquire() {
		return semaphore.tryAcquire();
	}

	public void stop() {
		scheduler.shutdownNow();
	}


	private void schedulePermitReplenishment() {
		scheduler = Executors.newScheduledThreadPool(1);
		scheduler.scheduleWithFixedDelay(() -> {
			if (semaphore.availablePermits() >= 0 &&
					maxPermits > semaphore.availablePermits()) {
				semaphore.release(maxPermits - semaphore.availablePermits());
			}
		}, 1L, 1L, timePeriod);//控制许可证的释放时间

	}
}