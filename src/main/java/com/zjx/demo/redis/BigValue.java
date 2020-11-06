package com.zjx.demo.redis;

import redis.clients.jedis.*;

import java.util.List;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.redis  redis 一个key对应的大Value
 * @date:2020/9/22
 **/
public class BigValue {

    public static void main(String[] args) {
        JedisPool jedisPool = new JedisPool("127.0.0.1", 6379);
        Jedis jedis = jedisPool.getResource();
        jedis.select(5);

        jedis.set("key1","bigValue1");
        jedis.set("key2","bigValue2");
        jedis.set("key3","bigValue3");
        jedis.set("key4","bigValue4");

        Transaction multi = jedis.multi();

        Response<String> key1 = multi.get("key1");
        Response<String> key2 = multi.get("key2");
        Response<String> key3 = multi.get("key3");
        Response<String> key4 = multi.get("key4");
        List<Object> exec = multi.exec();
        if(exec==null){
            System.out.println("事务执行失败");
        }

        String value = key1.get() + key2.get() + key3.get() + key4.get();
        System.out.println("获取的bigvalue" + value);
    }
}
