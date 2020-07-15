package com.zjx.demo.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import java.util.List;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.redis
 * @date:2020/6/29
 **/
public class TransactionDemo {

    public static int doubleAccount(Jedis jedis, String userId) {
        String key = keyFor(userId);
        while (true) {
            jedis.watch(key);
            int value = Integer.parseInt(jedis.get(key));
            value *= 2; // 加倍
            Transaction tx = jedis.multi();
            tx.set(key, String.valueOf(value));
            List<Object> res = tx.exec();
            if (res != null) {
                break; // 成功了
            }
        }
        return Integer.parseInt(jedis.get(key)); // 重新获取余额
    }

    public static String keyFor(String userId) {
        return String.format("account_%s", userId);
    }


    public static void main(String[] args) {
        JedisPool jedisPool = new JedisPool("10.0.1.34", 6379);
        Jedis jedis = jedisPool.getResource();
        jedis.auth("deThaxi6hesp8paKocheRorLf6AbRAsaxiqoxaquzeswoprar8juvlst7ru0AFro");
        jedis.select(5);
        String userId = "abc";
        String key = keyFor(userId);
        jedis.setnx(key, String.valueOf(5));  //setnx 做初始化
        System.out.println(doubleAccount(jedis, userId));
        jedis.close();
    }


}


