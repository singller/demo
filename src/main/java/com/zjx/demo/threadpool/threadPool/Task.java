package com.zjx.demo.threadpool.threadPool;

import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicLong;

public class Task implements Runnable {
    private final AtomicLong count = new AtomicLong(1L);

    @Override
    public void run() {
        System.out.println("running_"+count.getAndIncrement());
        TreeSet set = new TreeSet();
        Iterator iterator = set.iterator();

    }
}
