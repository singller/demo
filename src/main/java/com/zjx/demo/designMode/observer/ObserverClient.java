package com.zjx.demo.designMode.observer;


import com.zjx.demo.designMode.observer.impl.ConcreteObserverBaidu;
import com.zjx.demo.designMode.observer.impl.ConcreteObserverSina;
import com.zjx.demo.designMode.observer.impl.ConcreteSubject;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.designMode.observer 客户端
 * @date:2020/9/27
 **/
public class ObserverClient {

    public static void main(String[] args) {

        //创建具体主题
        ConcreteSubject concreteSubject = new ConcreteSubject();
        //创建具体观察者
        Observer observerBaidu = new ConcreteObserverBaidu();
        //进行注册
        concreteSubject.register(observerBaidu);

        ConcreteObserverSina observerSina = new ConcreteObserverSina();
        concreteSubject.register(observerSina);
        //检测天气
        concreteSubject.setData(28.2f,30.1f,108f);
        //移除某个监视器
        concreteSubject.remove(observerBaidu);
        //检测天气
        concreteSubject.setData(26.2f,32f,99f);

    }
}
