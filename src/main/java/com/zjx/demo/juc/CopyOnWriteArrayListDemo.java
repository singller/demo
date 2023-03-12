package com.zjx.demo.juc;

import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author zjx
 */
public class CopyOnWriteArrayListDemo {

    static CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

    static LinkedList<String> linkedList = new LinkedList<>();

    public static void main(String[] args) {
        list.add("阿西吧");
        list.set(0, "我的天");
        list.remove(0);
        System.out.println(list);
    }
}
