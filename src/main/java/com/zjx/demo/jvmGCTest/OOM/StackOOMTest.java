package com.zjx.demo.jvmGCTest.OOM;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.jvmGCTest.OOM
 * @date:2020/7/29
 **/
public class StackOOMTest {

    public static int couner = 0;

    public static void main(String[] args) {
        work();
    }



    public static void work(){
        System.out.println("work方法运行了"+(++couner)+"次");
        work();
    }
}
