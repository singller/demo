package com.zjx.demo.utils;

import org.apache.poi.ss.formula.functions.T;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicReference;


/**
 * @author zjx
 */
public class Stack {

    private volatile int count;

    private static final int totalCount = 100;

    /**
     *     链表结构头部节点
     */
    private Node head;

    private int getCount(){
        return count;
    }


    /**
     * 入栈
     *
     * @param item
     */
    public void push(T item) {

        if(count++>totalCount){
            throw new ArrayIndexOutOfBoundsException();
        }
        //为新插入item创建一个新node
        Node newHead = new Node(item);
        Node old;
        if (head != null) {
            do{
                old = head;
                newHead.next = old;
            }while (!testAndSet(count,count++,getCount()));

        }

    }

    /**
     * 出栈
     *
     * @return
     */
    public T pop() {

        if (head == null) {
            //当前链表为空
            return null;
        }

        //暂存当前节点。
        Node oldHead = head;
        //将当前节点指向当前节点的下一个节点
        head = head.next;
        //从暂存的当前节点记录返回数据

        return oldHead.item;
    }

    /**
     * 链表中的节点
     *
     * @param
     */

    private static class Node {

        //节点保存的数据
        public final T item;

        //指向下一个链表中下一个节点
        public Node next;

        public Node(T item) {
            this.item = item;
        }

    }

    private static synchronized boolean testAndSet(int value,int target,int before){
        if(value == before){
            value = target;
            return true;
        }
        return false;
    }

}
