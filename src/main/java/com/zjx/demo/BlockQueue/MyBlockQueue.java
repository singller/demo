package com.zjx.demo.BlockQueue;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.BlockQueue 实现阻塞队列
 * @date:2020/8/10
 **/
public class MyBlockQueue<E> {

    private LinkedList<E> list = new LinkedList<>();

    private ReentrantLock lock = new ReentrantLock();
    private Condition notFullCondition = lock.newCondition();
    private Condition notEmptyCondition = lock.newCondition();

    private int maxSize = 0;

    public  MyBlockQueue(int maxSize) {
        this.maxSize = maxSize;
    }

    public void push(E e) throws InterruptedException {
        lock.lock();
        try {
            if(list.size() >= maxSize) {
                notFullCondition.await();
            }
            list.add(e);
            notEmptyCondition.signal();
        } finally {
            lock.unlock();
        }

    }

    public E pop() throws InterruptedException {
        lock.lock();
        try {
            if(list.size() == 0) {
                notEmptyCondition.await();
            }
            E e = list.pop();
            notFullCondition.signalAll();
            return e;
        } finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) {
        MyBlockQueue queue = new MyBlockQueue(5);

        //生产队列
        new Thread(new Runnable() {
            public void run() {
                int i =0;
                try {
                    while (true){
                        Thread.sleep(1000);
                        queue.push(++i);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } ).start();

        //消费队列
        new Thread(new Runnable() {
            public void run() {
                try {
                    while (true){
                        Object pop = queue.pop();
                        System.out.println(pop+"=====1");
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } ).start();

        //消费队列
        new Thread(new Runnable() {
            public void run() {
                try {
                    while (true){
                        Object pop = queue.pop();
                        System.out.println(pop+"=====2");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } ).start();
    }

}
