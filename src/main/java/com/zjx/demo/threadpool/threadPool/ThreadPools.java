package com.zjx.demo.threadpool.threadPool;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPools {

    public ThreadPoolExecutor createThreadPool() {
        return new ThreadPoolExecutor(3, Integer.MAX_VALUE, 60l, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
    }
}
