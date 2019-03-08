package com.forest10.spring.boot.family.conf;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

/**
 * @author Forest10
 * @date 2019-03-08 23:30
 */
@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class RedisConfiguration {


    /**
     * 线上环境
     */
    private static final String PRODUCTION_ENV = "prod";
    @Value("${spring.application.name}")
    private String applicationName;
    @Value("${spring.profiles.active}")
    private String activeProfile;

    /***
     * 自定义key生成策略
     * @return
     */
    @Bean
    public KeyGenerator simpleKeyGenerator() {
        return (o, method, objects) -> {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(o.getClass()
                .getSimpleName());
            stringBuilder.append(".");
            stringBuilder.append(method.getName());
            stringBuilder.append("[");
            for (Object obj : objects) {
                stringBuilder.append(obj.toString());
            }
            stringBuilder.append("]");

            return stringBuilder.toString();
        };
    }

    @Bean
    public CacheManager cacheManager(LettuceConnectionFactory lettuceConnectionFactory) {
        return new RedisCacheManager(
            RedisCacheWriter.nonLockingRedisCacheWriter(lettuceConnectionFactory),
            // 默认策略，未配置的 key 会使用这个
            this.getRedisCacheConfigurationWithTtl(Duration.ofHours(1L)),
            // 指定 key 策略
            this.getSpecialCacheNameSpaceConfiguration());
    }

    private Map<String, RedisCacheConfiguration> getSpecialCacheNameSpaceConfiguration() {
        Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();
        redisCacheConfigurationMap
            .put(applicationName, this.getRedisCacheConfigurationWithTtl(
                StringUtils
                    .equals(activeProfile, PRODUCTION_ENV) ?
                    Duration.ofDays(1L) : Duration.ofSeconds(10L)));
        return redisCacheConfigurationMap;
    }

    /**
     * 得到带有TTL时间的配置
     */
    private RedisCacheConfiguration getRedisCacheConfigurationWithTtl(Duration duration) {
        //使用jackson替换默认jdk
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer =
            new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration
            .defaultCacheConfig();
        redisCacheConfiguration = redisCacheConfiguration.serializeValuesWith(
            RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
            .entryTtl(duration)
            .disableCachingNullValues();
        return redisCacheConfiguration;
    }

}