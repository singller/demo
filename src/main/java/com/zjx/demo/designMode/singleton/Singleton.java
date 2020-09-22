package com.zjx.demo.designMode.singleton;

/**
 * @author: create by zhangjianxun
 * @version: v1.0 饿汉式
 * @description: singleton
 * @date:2020/6/3
 **/
public class Singleton {
    //实例
    private static Singleton instance = new Singleton();

    //私有的构造函数 外部无法使用
    private Singleton(){

    }

    //对外提供静态的获取实例的方法
    public static Singleton getInstance() {
        return instance;
    }
}
