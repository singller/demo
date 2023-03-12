package com.zjx.demo.utils;



import cn.hutool.core.thread.ExecutorBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Desc: 线程安全 CASLock
 * @Author: zjx
 * @Date: 2022/4/20 0020 17:37
 */
public class MyCASStack<T> {

    private AtomicInteger size = new AtomicInteger(0);


    /**
     * 采用尾插法
     */
    private AtomicReference<Node> lastNode = new AtomicReference<>();

    public int size() {
        return size.get();
    }

    public boolean isEmpty() {
        return size() <= 0;
    }

    /**
     * @param element
     */
    public void push(T element) {
        Node node = new Node(element);
        Node old;
        do {
            old = lastNode.get();
            node.next = old;
        } while (!lastNode.compareAndSet(old, node));
        size.incrementAndGet();
    }

    /**
     * 弹出最后一个节点
     *
     * @return
     */
    public T pop() {
        if (size.get() <= 0) {
            return null;
        }
        Node top;
        Node topNext;
        do {
            top = lastNode.get();
            topNext = top.next;
        } while (!lastNode.compareAndSet(top, topNext));
        size.decrementAndGet();
        return top.item;

    }


    public static void main(String[] args) {
        MyCASStack<Integer> stack = new MyCASStack<>();
        ExecutorService executorService = ExecutorBuilder.create().build();
        for (int i = 0; i < 20; i++) {
            final int index = i;
            executorService.execute(() -> {
                stack.push(index);
            });
        }

        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (!stack.isEmpty()) {
            System.out.println("出栈:" + stack.pop());
        }
        executorService.shutdown();
    }

    /**
     * 链表中的节点
     *
     * @param
     */

    private class Node {

        //节点保存的数据
        public final T item;

        //指向下一个链表中下一个节点
        public MyCASStack.Node next;

        public Node(T item) {
            this.item = item;
        }

    }

}
