package com.zjx.demo.designMode.observer.jdkObserver;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.designMode.observer.jdkObserver
 * @date:2020/9/27
 **/
public class JdkObserverClient {

    public static void main(String[] args) {
        OilFutures oilFutures = new OilFutures();

        Bear bear = new Bear();
        Bull bull = new Bull();
        oilFutures.addObserver(bear);
        oilFutures.addObserver(bull);

        oilFutures.setPrice(10);

    }
}
