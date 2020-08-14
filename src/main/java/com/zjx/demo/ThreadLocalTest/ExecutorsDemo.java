package com.zjx.demo.ThreadLocalTest;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.ThreadLocalTest  使用guava的线程工厂创建
 * @date:2020/6/15
 **/
public class ExecutorsDemo {


    private static ThreadFactory nameThread =new ThreadFactoryBuilder()
            .setNameFormat("demo-pool-%d").build();

    private static ExecutorService threadPool =new ThreadPoolExecutor(5,200,20,TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(200),nameThread,new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) throws InterruptedException {
        for(int i=0;i<Integer.MAX_VALUE;i++){
            Thread.sleep(1000);
            threadPool.execute(new SubThread());
        }
    }


}

class SubThread implements Runnable {
    private static AtomicInteger i = new AtomicInteger(1);

    @Override
    public void run() {
        try {
            System.out.println(i.incrementAndGet());
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            //do nothing
        }
    }
}