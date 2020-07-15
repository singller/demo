package com.zjx.demo.designMode.proxy;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.designMode.proxy
 * @date:2020/7/15
 **/
public class RealSubject implements Subject {
    @Override
    public void Request() {
        System.out.println("访问真实主题方法...");
    }
}
