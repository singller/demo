package com.zjx.demo.jvmGCTest.OOM;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.jvmGCTest.OOM
 * @date:2020/7/29
 **/
public class MetaspaceOOMTest {

    public static void main(String[] args) {
        int counter = 0;
        while (true){
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(Car.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    if(method.getName().equals("run")){
                        System.out.println("汽车启动前进行检查");
                        return methodProxy.invokeSuper(o,objects);
                    }
                    return methodProxy.invokeSuper(o,objects);
                }
            });

            Car car = (Car)enhancer.create();
            car.run();
            System.out.println("目前创建了"+(++counter)+"个Car子类");
        }
    }

}

class Car{

    public void run(){
        System.out.println("汽车启动");
    }
}