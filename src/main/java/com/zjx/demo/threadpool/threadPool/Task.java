package com.zjx.demo.threadpool.threadPool;

import java.util.concurrent.atomic.AtomicLong;

public class Task implements Runnable {
    private final AtomicLong count = new AtomicLong(1L);

    @Override
    public void run() {
        System.out.println("running_"+count.getAndIncrement());
    }
}
