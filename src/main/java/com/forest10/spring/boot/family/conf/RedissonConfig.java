package com.forest10.spring.boot.family.conf;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Forest10
 * @date 2019-03-02 15:40
 */
@Configuration
public class RedissonConfig {

    @Value("${spring.redis.database}")
    private int database;

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.port}")
    private String port;

    @Bean
    RedissonClient createConfig() {
        Config config = new Config();
        //设置编码方式为 Jackson JSON 编码（不设置默认也是这个）
        config.setCodec(new JsonJacksonCodec());
        config.useSingleServer()
            //节点地址设置
            .setAddress("redis://" + host + ":" + port)
            //密码
            .setPassword(password)
            //数据库编号（默认0）
            .setDatabase(database);

        return Redisson.create(config);
    }
}