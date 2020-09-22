package com.zjx.demo.designMode.singleton;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: singleton
 * @date:2020/6/3
 **/
public class Singleton1 {
    private static Singleton1 instance;
    static {
        instance = new Singleton1();
    }
    //私有构造
    private Singleton1(){

    }
    //对外的获取实例静态方法
    public static Singleton1 getInstance(){
        return instance;
    }
}
