package com.forest10.spring.boot.family.biz;

import com.forest10.spring.boot.family.service.BasicService;
import java.util.concurrent.CompletableFuture;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @author Forest10
 * @date 2019-07-08 13:28
 */
@Slf4j
@Component
public class AsyncBiz {

	@Resource
	private BasicService basicService;

	public DeferredResult<String> getDeferredResult() {
		DeferredResult<String> deferredResult = new DeferredResult<>();
		CompletableFuture.supplyAsync(basicService::test)
				.whenCompleteAsync((result, throwable) -> deferredResult.setResult(result)
				);
		deferredResult.onCompletion(() ->
				log.info("deferredResult onCompletion")
		);
		deferredResult.onTimeout(() ->
				log.info("deferredResult onTimeout"));
		deferredResult.onError(throwable -> {
			log.error("error:", throwable);
		});
		log.info("Servlet thread released");

		return deferredResult;
	}
}
