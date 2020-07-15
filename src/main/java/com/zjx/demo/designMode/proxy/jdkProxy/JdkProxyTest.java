package com.zjx.demo.designMode.proxy.jdkProxy;

import com.zjx.demo.designMode.proxy.RealSubject;
import com.zjx.demo.designMode.proxy.Subject;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.designMode.proxy.CglibProxy
 * @date:2020/7/15
 **/
public class JdkProxyTest {
    public static void main(String[] args) {
        //实例化目标对象
        Subject subject=new RealSubject();

        //实例化Invocation
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(subject);
        //根据目标对象生成代理
        Subject subjectProxy = (Subject)myInvocationHandler.getProxy();

        //调用代理对象方法
        subjectProxy.Request();
    }
}
