package com.zjx.demo.designMode.observer.jdkObserver;

import java.util.Observable;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.designMode.observer.jdkObserver
    具体目标  原油期货
 * @date:2020/9/27
 **/
public class OilFutures extends Observable {
    private float price;

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {

        super.setChanged();//设置内部标志， 注明数据发生变化
        super.notifyObservers(price);//设置价格变化

        this.price = price;
    }
}
