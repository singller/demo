package com.zjx.demo.designMode.singleton;

/**
 * @author: create by zhangjianxun
 * @version: v1.0   Synchronized的懒汉式    synchronized 修饰了整个getInstance方法，方法所有操作都是同步及进行，很是耗费资源，当不是第一次初始化的时候不需要被synchronized修饰
 * @description: singleton
 * @date:2020/6/3
 **/
public class SynchronizedSingleton {

    //私有实例
    private static SynchronizedSingleton instance;
    //私有构造
    private SynchronizedSingleton(){}
    //synchronized修饰的获取实例静态方法
    public synchronized static SynchronizedSingleton getInstance(){
        if (instance == null){
            instance = new SynchronizedSingleton();
        }
        return instance;
    }
}
