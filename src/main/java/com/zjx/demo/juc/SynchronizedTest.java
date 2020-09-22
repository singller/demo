package com.zjx.demo.juc;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.juc  synchronized的测试
 * @date:2020/8/28
 **/
public class SynchronizedTest {

    public static void test() {
        synchronized (SynchronizedTest.class) {
            System.out.println("线程被锁定");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(3000);
        while (true){
            loadData();
        }
    }


    public static void loadData() throws InterruptedException {
        byte[] data = null;

        for (int i = 0; i < 50; i++) {
            data = new byte[100 * 1024];
        }
        data =null;

        Thread.sleep(1000);

    }
}
