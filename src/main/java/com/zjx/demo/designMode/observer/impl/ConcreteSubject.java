package com.zjx.demo.designMode.observer.impl;

import com.zjx.demo.designMode.observer.Observer;
import com.zjx.demo.designMode.observer.Subject;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.designMode.observer.impl  具体主题的实现
 * @date:2020/9/27
 **/
@Data
public class ConcreteSubject implements Subject {

    private float temperature;//温度

    private float humidity;//湿度

    private float kPa;//气压

    private List<Observer> observerList;

    public ConcreteSubject(){
        observerList = new ArrayList<Observer>();
    }

    public void setData(float temperature,float hunidity,float kPa){
        this.temperature = temperature;
        this.humidity = hunidity;
        this.kPa = kPa;
        notifyObserver();
    }


    @Override
    public void register(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void remove(Observer observer) {
        if(observerList.contains(observer)){
            observerList.remove(observer);
        }
    }

    @Override
    public void notifyObserver() {
        for (Observer observer: observerList){
            observer.response(temperature,humidity,kPa);
        }
    }
}
