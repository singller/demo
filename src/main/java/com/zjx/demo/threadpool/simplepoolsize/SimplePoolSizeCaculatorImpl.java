package com.zjx.demo.threadpool.simplepoolsize;

import java.lang.management.ManagementFactory;
import java.math.BigDecimal;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.threadpool.simplepoolsize
 * @date:2020/9/27
 **/
public class SimplePoolSizeCaculatorImpl extends PoolSizeCalculator {


    @Override
    protected Runnable creatTask() {
        return new AsyncIOTask();
    }

    @Override
    protected BlockingQueue createWorkQueue() {
        return new LinkedBlockingQueue(1000);
    }

    @Override
    protected long getCurrentThreadCPUTime() {
        return ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
    }

    public static void main(String[] args) {
        PoolSizeCalculator poolSizeCalculator = new SimplePoolSizeCaculatorImpl();
        poolSizeCalculator.calculateBoundaries(new BigDecimal(1.0), new BigDecimal(100000));
    }
}
