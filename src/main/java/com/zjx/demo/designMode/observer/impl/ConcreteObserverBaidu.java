package com.zjx.demo.designMode.observer.impl;

import com.zjx.demo.designMode.observer.Observer;
import lombok.Data;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.designMode.observer.impl 具体观察者实现
 * @date:2020/9/27
 **/
@Data
public class ConcreteObserverBaidu implements Observer {

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
        System.out.println("百度天气 温度"+temperature);
        System.out.println("百度天气 湿度"+humidity);
        System.out.println("百度天气 压力"+kPa);
    }
}
