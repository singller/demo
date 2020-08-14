package com.zjx.demo.redis.lock;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.redis.lock 基于redis分布式锁
 * @date:2020/8/14
 **/
public class RedisLock {
    private static volatile Integer inventory = 1000;
    private static final int NUM = 1000;
    private static LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
    volatile static CountDownLatch countDownLatch = new CountDownLatch(inventory);
    static CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

    public static void main(String[] args) throws InterruptedException {

        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(50, 50, 10L, SECONDS, linkedBlockingQueue);
        long start = System.currentTimeMillis();
        Config config = new Config();
        config.useSingleServer().setAddress("redis://10.0.1.34:6379").setPassword("deThaxi6hesp8paKocheRorLf6AbRAsaxiqoxaquzeswoprar8juvlst7ru0AFro")
                .setDatabase(5);

        final RedissonClient client = Redisson.create(config);
        final RLock lock = client.getLock("lock1");

        for (int i = 0; i < NUM; i++) {
            threadPoolExecutor.execute(new Runnable() {
                public void run() {
                    lock.lock();
                    inventory--;
                    System.out.println(Thread.currentThread().getId()+"======="+inventory);
                    lock.unlock();
                    countDownLatch.countDown();
                }
            });
        }
        long end = System.currentTimeMillis();
        countDownLatch.await();
        System.out.println("执行线程数:" + NUM + "   总耗时:" + (end - start) + "  库存数为:" + inventory);
    }
}
