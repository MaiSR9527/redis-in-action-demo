package com.msr.action.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * author: MaiShuRen
 * site: http://www.maishuren.top
 * sine: 2021-02-28 11:35
 **/
@Component
@Slf4j
public class RedisUtil {

    private final RedisTemplate<Object, Object> redisTemplate;

    public RedisUtil(RedisTemplate<Object, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void set(String key, String value, Long timeout, TimeUnit timeUnit) {
        ValueOperations<Object, Object> ops = redisTemplate.opsForValue();
        ops.set(key, value, timeout, timeUnit);
    }


}
