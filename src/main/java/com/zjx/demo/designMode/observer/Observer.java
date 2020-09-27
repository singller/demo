package com.zjx.demo.designMode.observer;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.designMode.observer 观察者
 * @date:2020/9/27
 **/
public interface Observer {

    public void response(float temperature,float humidity,float kPa);
}
