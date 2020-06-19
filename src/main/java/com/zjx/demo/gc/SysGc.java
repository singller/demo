package com.zjx.demo.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.gc
 * @date:2020/6/18
 **/
public class SysGc {

    public static void main(String[] args) {
        //-XX:+UseParallelGC -XX:+UseParallelOldGC -XX:+PrintGC -XX:+PrintGCTimeStamps -XX:+PrintGCDetails -Xms2g -Xmx2g -Xmn1g
//        for (int i = 0; i < 5; i++) {
//            createBigObject(21);
//            System.gc();
//        }

        //-Xms200m -Xmx200m -Xmn32m -XX:+UseParallelGC -XX:+UseParallelOldGC -XX:+PrintGC
//        // 设置大对象，新生代内存有32m，不够
        byte[] bigObj1 = new byte[1024 * 1024 * 160];
        byte[] bigObj2 = new byte[1024 * 1024 * 70];

    }

    private static void createBigObject(int n){
        List<byte[]> bytesList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            bytesList.add(new byte[1024 * 1024 * 10]);
        }
        if (bytesList.size() < 20) {
            throw new RuntimeException("this is test");
        }
    }
}
