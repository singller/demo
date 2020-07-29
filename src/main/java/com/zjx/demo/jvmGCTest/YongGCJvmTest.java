package com.zjx.demo.jvmGCTest;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.jvmGCTest
 * @date:2020/7/25
 **/
public class YongGCJvmTest {

    public static void main(String[] args) {
//        byte [] array1 = new byte[1024 * 1024];
//        array1 = new byte[1024 * 1024];
//        array1 = new byte[1024 * 1024];
//        array1 = null;
//
//        byte [] array2 = new byte[2 *1024 * 1024];

        //        byte[] array1 = new byte[2 * 1024 * 1024];
//        array1 = new byte[2 * 1024 * 1024];
//        array1 = new byte[2 * 1024 * 1024];
//        array1 = null;
//
//        byte[] array2 = new byte[128 * 1024];
//
//        byte[] array3 = new byte[2 * 1024 * 1024];
//        array3 = new byte[2 * 1024 * 1024];
//        array3 = new byte[2 * 1024 * 1024];
//        array3 = new byte[128 * 1024];
//        array3 = null;
//
//        byte[] array4 = new byte[2 * 1024 * 1024];
//        array4 = new byte[2 * 1024 * 1024];
//        array4 = new byte[2 * 1024 * 1024];
//        array4 = new byte[128 * 1024];
//        array4 = null;
//
//        byte[] array5 = new byte[2 * 1024 * 1024];
        /** minor GC 之后survivor区放不下  放入老年代*/
        byte[] array1 = new byte[2 * 1024 * 1024];
        array1 = new byte[2 * 1024 * 1024];
        array1 = new byte[2 * 1024 * 1024];

        byte[] array2 = new byte[128 * 1024];
        array2 = null;

        byte [] array3 = new byte[2 * 1024 *1024];

    }
}
