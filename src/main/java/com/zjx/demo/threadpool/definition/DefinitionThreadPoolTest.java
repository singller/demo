package com.zjx.demo.threadpool.definition;

import com.zjx.demo.threadpool.config.UserRejectHandler;
import com.zjx.demo.threadpool.config.UserThreadFactory;
import com.zjx.demo.threadpool.threadPool.Task;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DefinitionThreadPoolTest {
    public static void main(String[] args) {
        //缓存队列设置固定长度为2，为了快速出发rejectHandler
        BlockingQueue queue = new LinkedBlockingDeque(2);


        //设置任务线程来源由机房1 和机房2 混合调用
        UserThreadFactory userThreadFactory1 = new UserThreadFactory("第1机房");

        UserRejectHandler userRejectHandler = new UserRejectHandler();

        TaskQueue<Runnable> taskQueue = new TaskQueue<Runnable>(2);
        //核心线程数为1 最大线程数为2 为了保证触发rejectHandler
        EagerThreadPoolExecutor eagerThreadPoolExecutor = new EagerThreadPoolExecutor(1, 3, 30, TimeUnit.SECONDS, taskQueue, userThreadFactory1, userRejectHandler);
        taskQueue.setExecutor(eagerThreadPoolExecutor);
        Task task = new Task();
        for (int i =0;i<10;i++){
            eagerThreadPoolExecutor.execute(task);
            int size = taskQueue.size();
            System.out.println("线程队列大小为:"+size);
        }
        eagerThreadPoolExecutor.shutdown();
    }
}
