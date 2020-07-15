package com.zjx.demo.designMode.proxy.CglibProxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.designMode.proxy.CglibProxy
 * @date:2020/7/15
 **/
public class CglibProxy implements MethodInterceptor {

    private Object object;

    /**
     * 创建代理对象
     *
     * @param target
     * @return
     */
    public Object getInstance(Object target){
        this.object = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.object.getClass());
        //回调方法
        enhancer.setCallback(this);
        //创建代理对象
        return enhancer.create();
    }


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("时间开始");
        methodProxy.invokeSuper(o,objects);
        System.out.println("事件结束");
        return null;
    }


    public static void main(String[] args) {
        CglibProxy proxy = new CglibProxy();
        HelloImpl instance = (HelloImpl)proxy.getInstance(new HelloImpl());
        instance.say();
    }
}
