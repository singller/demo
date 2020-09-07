package com.zjx.demo.threadpool.config;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

public class UserRejectHandler implements RejectedExecutionHandler {
    private final AtomicInteger nextId = new AtomicInteger(1);
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("task rejected."+nextId.getAndIncrement()+"===" + executor.toString());
    }
}
