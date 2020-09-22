package com.zjx.demo.designMode.singleton;

/**
 * @author: create by zhangjianxun
 * @version: v1.0 懒汉式   缺乏线程安全，当多线程同时进入if 对创建多个实例
 * @description: singleton
 * @date:2020/6/3
 **/
public class Singleton2 {

    //私有实例
    private static Singleton2 instance;
    //私有构造
    private Singleton2(){}
    //对外获取实例静态方法
    public static Singleton2 getInstance(){
        if(instance == null){
            instance = new Singleton2();
        }
        return instance;
    }
}
