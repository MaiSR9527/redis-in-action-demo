package com.msr.action.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

/**
 * 对redis的key和value做序列化处理
 * author: MaiShuRen
 * site: http://www.maishuren.top
 * sine: 2021-02-24 23:45
 **/
@Configuration
public class RedisConfig {

    @Bean
    @ConditionalOnBean(name = "redisTemplate")
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        Jackson2JsonRedisSerializer<Object> objectJackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);

        redisTemplate.setKeySerializer(objectJackson2JsonRedisSerializer);
        redisTemplate.setValueSerializer(objectJackson2JsonRedisSerializer);

        redisTemplate.setHashKeySerializer(objectJackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(objectJackson2JsonRedisSerializer);

        redisTemplate.setConnectionFactory(connectionFactory);

        return redisTemplate;
    }
}
