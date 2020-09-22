package com.zjx.demo.designMode.singleton;

/**
 * @author: create by zhangjianxun
 * @version: v1.0 静态类单例
 * @description: singleton
 * @date:2020/6/3
 **/
public class StaticInnerClassSingleton {

    //静态类中实例化
    private static class SingletonHolder{
        private static  final StaticInnerClassSingleton INSTANCE = new StaticInnerClassSingleton();
    }
    //私有构造
    private StaticInnerClassSingleton(){}
    //对外公共的获取实例的静态方法
    public static StaticInnerClassSingleton getInstance(){
        return SingletonHolder.INSTANCE;
    }
}
