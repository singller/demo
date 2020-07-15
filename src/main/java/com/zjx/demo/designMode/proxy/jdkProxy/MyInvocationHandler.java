package com.zjx.demo.designMode.proxy.jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.designMode.proxy.CglibProxy  自定义简单的Invocation，对接口提供的方法进行增强
 * @date:2020/7/15
 **/
public class MyInvocationHandler implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //在目标方法执行前简单打印一下
        System.out.println("----------before----------");

        //执行目标方法对象
        Object result = method.invoke(target, args);

        //在目标方法执行之后简单打印一下
        System.out.println("----------after----------");
        return result;
    }
    //目标对象
    private Object target;

    public MyInvocationHandler(Object target){
        super();
        this.target = target;
    }

    //获取代理类
    public Object getProxy(){
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                this.target.getClass().getInterfaces(),this);
    }
}
