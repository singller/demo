package com.zjx.demo.designMode.observer.impl;

import com.zjx.demo.designMode.observer.Observer;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.designMode.observer.impl
 * @date:2020/9/27
 **/
public class ConcreteObserverSina implements Observer {

    private float temperature;//温度

    private float humidity;//湿度

    private float kPa;//气压



    @Override
    public void response(float temperature,float humidity,float kPa) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.kPa = kPa;
        display();
    }

    public void display(){
        System.out.println("新浪天气 温度"+temperature);
        System.out.println("新浪天气 湿度"+humidity);
        System.out.println("新浪天气 压力"+kPa);
    }
}
