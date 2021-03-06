package com.zjx.demo.juc;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.juc
 * @date:2020/8/10
 **/
public class SemaphoreTest {

    private static int count = 20;

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(count);

        Semaphore semaphore = new Semaphore(5,false);

        Random random = new Random();
        for (int i = 0; i < count; i++) {
            final int no = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        //获得许可
                        semaphore.acquire();
                        System.out.println(no +":号车可通行");
                        //模拟车辆通行耗时
                        Thread.sleep(2000);
                        //释放许可
                        System.out.println();
                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        executorService.shutdown();

    }

}
