package com.zjx.demo.designMode.observer;

/*
* @Description  抽象主题
* @Return
* @Date 2020/9/27 9:29
**/
public interface Subject {

    //添加观察者
    public void register(Observer observer);

    //移除观察者
    public void remove(Observer observer);

    //通知观察者
    public void notifyObserver();
}
