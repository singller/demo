package com.zjx.demo.designMode.singleton;

/**
 * @author: create by zhangjianxun
 * @version: v1.0  synchronized修饰具体实例的懒汉式
 * @description: singleton
 * @date:2020/6/3
 **/
public class SynchronizedSingleton1 {

    //私有静态实例
    private static SynchronizedSingleton1 instance;
    //私有构造
    private SynchronizedSingleton1(){}
    //对外提供的静态实例方法
    public static SynchronizedSingleton1 getInstance(){
        if(instance == null){
            synchronized (SynchronizedSingleton1.class){
                if(instance == null){
                    instance = new SynchronizedSingleton1();
                }
            }
        }
        return instance;
    }
}
