package com.zjx.demo.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.redis.service
 * @date:2020/8/13
 **/
@Service
public class Publisher {

    @Autowired
    private RedisTemplate redisTemplate;

    public void publish(Object msg){

        redisTemplate.convertAndSend("demo-channel",msg);
    }
}
