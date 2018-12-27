package com.forest10.spring.boot.family.xxljob.jobhanlder;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Forest10
 * @date 2018-12-26 23:33
 */
@Slf4j
@Service
@JobHandler(value = "shisenHandler")
public class DemoHandler extends IJobHandler {

    public DemoHandler(){
        log.info("demoHandler");
    }

    @Override
    public ReturnT<String> execute(String param) throws Exception {
        log.info("xxl-job good");
        return ReturnT.SUCCESS;
    }
}
