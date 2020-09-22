package com.zjx.demo.designMode.singleton;

import java.io.Serializable;

/**
 * @author: create by zhangjianxun
 * @version: v1.0  双重校验锁的懒汉式单例
 * @description: singleton
 * @date:2020/6/3
 **/
public class VolatileSingleton implements Serializable {

    //私有实例
    private static volatile VolatileSingleton instance;
    //私有构造
    private VolatileSingleton(){}

    //对外提供获取实例的静态方法
    public static VolatileSingleton getInstance(){
        if(instance == null){
            synchronized (VolatileSingleton.class){
                if(instance == null){
                    instance = new VolatileSingleton();
                }
            }
        }
        return instance;
    }

    //防止反序列化破坏单例
    public Object readResolve(){
        return instance;
    }
}
