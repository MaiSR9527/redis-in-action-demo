package com.msr.action.basis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * redis的基本数据类型操作
 * author: MaiShuRen
 * site: http://www.maishuren.top
 * sine: 2021-02-24 23:55
 **/
@RestController("basic")
public class RedisBasicOperation {

    private final RedisTemplate<Object, Object> redisTemplate;

    public RedisBasicOperation(RedisTemplate<Object, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostMapping("setString/{key}/{value}")
    public Object setString(@PathVariable("key") String key, @PathVariable("value") Object value) {
        ValueOperations<Object, Object> ops = redisTemplate.opsForValue();
        ops.set(key, value);
        return null;
    }

    @GetMapping("listKey")
    public Object listKeys() {
        Set<Object> keys = redisTemplate.keys("*");
        HashMap<String, Object> map = new HashMap<>();
        if (keys == null) {
            return "not keys in redis";
        }
        keys.forEach(e -> {
            map.put(String.valueOf(e), redisTemplate.opsForValue().get(e));
        });
        return map;
    }

}
