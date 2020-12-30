package com.zjx.demo.designMode.proxy;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.designMode.proxy
 * @date:2020/7/15
 **/
public class Proxy implements Subject {

    private RealSubject realSubject;

    @Override
    public void Request() {
        if (null == realSubject) {
            realSubject = new RealSubject();
        }
        preRequest();
        realSubject.Request();
        postRequest();
    }

    public void preRequest() {
        System.out.println("访问真实主题之前的预处理。");
    }

    public void postRequest() {
        System.out.println("访问真实主题之后的后续处理。");
    }


    public static void main(String[] args) {
        Proxy proxy = new Proxy();
        proxy.Request();
    }
}
