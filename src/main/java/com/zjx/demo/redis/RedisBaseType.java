package com.zjx.demo.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author 65454
 */
public class RedisBaseType {

    @Autowired
    private RedisTemplate redisTemplate;

}
