package com.forest10.spring.boot.family.conf;

import com.xxl.job.core.executor.XxlJobExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 基础配置
 *
 * @author Forest10
 * @date 2018/7/16 下午5:15
 */
@Configuration
public class CoreConf {

    @Bean(initMethod = "start", destroyMethod = "destroy")
    public XxlJobExecutor xxlJobExecutor() {
        XxlJobExecutor xxlJobExecutor = new XxlJobExecutor();
        xxlJobExecutor.setAdminAddresses("http://140.143.242.205:4567/xxl-job-admin");
        xxlJobExecutor.setAppName("spring-boot-xxljob");
        return xxlJobExecutor;
    }

}
