package com.zjx.demo.redis.redisson;

import org.redisson.Redisson;
import org.redisson.RedissonMultiLock;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

/**
 * @author 65454
 */
public class RedissionTest {

    public static void main(String[] args) throws InterruptedException {
        Config config = new Config();
//        config.useClusterServers()
//                .addNodeAddress("redis://localhost:6379");
        config.useSingleServer().setAddress("redis://localhost:6379");

        RedissonClient redisson = Redisson.create(config);
//        RLock lock = redisson.getLock("anyLock");
//        lock.lock();
//        lock.unlock();
//
//        RMap<String, Object> map = redisson.getMap("anyMap");
//        map.put("foo", "bar");
//
//        map = redisson.getMap("anyMap");
//        System.out.println(map.get("foo"));
//
//        /* 加超时时间的锁*/
//        boolean b = lock.tryLock(10, 100, TimeUnit.SECONDS);
//
//        /* 多个锁*/
//        RLock lock1 = redisson.getLock("lock1");
//        RLock lock2 = redisson.getLock("lock2");
//        RLock lock3 = redisson.getLock("lock3");
//        RedissonMultiLock redissonMultiLock = new RedissonMultiLock(lock1, lock2, lock3);
//        redissonMultiLock.lock();
//        redissonMultiLock.unlock();
//
//
//        RedissonRedLock redissonRedLock = new RedissonRedLock(lock1, lock2, lock3);
//        redissonRedLock.lock();
//        redissonRedLock.unlock();


        /*公平锁*/
        RLock anyFairLock = redisson.getFairLock("anyFairLock");
        anyFairLock.lock();
        anyFairLock.unlock();
    }
}
