package com.zjx.demo.juc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.juc CountDownLatch测试
 * @date:2020/8/10
 **/
public class CountDownTest {

    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    volatile int i =10;
    static AtomicInteger integer = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {

        integer.incrementAndGet();
        CountDownLatch latch = new CountDownLatch(2);
        Worker worker1 = new Worker("张三",2000,latch);
        Worker worker2 = new Worker("李四",3000,latch);

        worker1.run();
        worker2.run();

        long startTime = System.currentTimeMillis();
        latch.await();
        System.out.println("bug全部解决，领导可以给客户交差了，任务总耗时："+ (System.currentTimeMillis() - startTime));

    }

    static class Worker implements Runnable {
        String name;
        int workTime;
        CountDownLatch latch;

        public Worker(String name, int workTime, CountDownLatch latch) {
            this.name = name;
            this.workTime = workTime;
            this.latch = latch;
        }

        @Override
        public void run() {
            System.out.println(name+"开始修复bug，当前时间："+sdf.format(new Date()));
            doWork();
            System.out.println(name+"结束修复bug，当前时间："+sdf.format(new Date()));
            latch.countDown();
        }

        private void doWork() {
            try {
                //模拟工作耗时
                Thread.sleep(workTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
