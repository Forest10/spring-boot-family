package com.forest10.spring.boot.family.controller;

import com.forest10.spring.boot.family.biz.AsyncBiz;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @author Forest10
 * @date 2019-07-07 16:59
 */
@Slf4j
@RestController
public class AsyncController {

	@Resource
	private AsyncBiz asyncBiz;

	@GetMapping("/async")
	public Callable<String> async() {
		System.out.println("async in...");
		return new Callable<String>() {
			@Override
			public String call() throws Exception {
				return "hello task";
			}
		};

	}


	@GetMapping("/test")
	public String test() {
		log.info("Request received");

		try {

			TimeUnit.SECONDS.sleep(2L);
		} catch (Exception e) {

		}
		return "test";
	}


	@GetMapping("/deferred")
	public DeferredResult<String> deferredResult() {
		log.info("Request received");
		log.info("Servlet thread released");
		return asyncBiz.getDeferredResult();
	}

}
