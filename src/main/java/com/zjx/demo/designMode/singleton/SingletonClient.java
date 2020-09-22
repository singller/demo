package com.zjx.demo.designMode.singleton;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: singleton
 * @date:2020/6/3
 **/
public class SingletonClient {

    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
        Singleton singleton1 = Singleton.getInstance();
        System.out.println(singleton == singleton1);

        Singleton1 instance = Singleton1.getInstance();
        Singleton1 instance1 = Singleton1.getInstance();
        System.out.println(instance == instance1);
    }
}
