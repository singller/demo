package com.zjx.demo.jvmGCTest;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.jvmGCTest
 * @date:2020/7/25
 **/
public class JvmTest {

    public static void main(String[] args) {
        byte [] array1 = new byte[1024 * 1024];
        array1 = new byte[1024 * 1024];
        array1 = new byte[1024 * 1024];
        array1 = null;

        byte [] array2 = new byte[2 *1024 * 1024];
    }
}
