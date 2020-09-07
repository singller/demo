package com.zjx.demo.threadpool.threadPool;

import com.zjx.demo.threadpool.config.UserRejectHandler;
import com.zjx.demo.threadpool.config.UserThreadFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class UserThreadPool {
    public static void main(String[] args) {
        //缓存队列设置固定长度为2，为了快速出发rejectHandler
        BlockingQueue queue = new LinkedBlockingDeque(2);

        //设置任务线程来源由机房1 和机房2 混合调用
        UserThreadFactory userThreadFactory1 = new UserThreadFactory("第1机房");
        UserThreadFactory userThreadFactory2 = new UserThreadFactory("第2机房");

        UserRejectHandler userRejectHandler = new UserRejectHandler();

        //核心线程数为1 最大线程数为2 为了保证触发rejectHandler
        ThreadPoolExecutor threadPoolExecutor1 = new ThreadPoolExecutor(1, 2, 60, TimeUnit.SECONDS,
                queue, userThreadFactory1, userRejectHandler);

        ThreadPoolExecutor threadPoolExecutor2 = new ThreadPoolExecutor(1, 2, 60, TimeUnit.SECONDS,
                queue, userThreadFactory2, userRejectHandler);

        Task task = new Task();
        for (int i =0;i<200;i++){
            threadPoolExecutor1.execute(task);
            threadPoolExecutor2.execute(task );
        }
    }
}
