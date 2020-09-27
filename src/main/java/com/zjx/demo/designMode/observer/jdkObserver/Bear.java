package com.zjx.demo.designMode.observer.jdkObserver;

import java.util.Observable;
import java.util.Observer;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.designMode.observer.jdkObserver
 * 空方
 * @date:2020/9/27
 **/
public class Bear implements Observer {
    public void update(Observable o,Object arg)
    {
        Float price=((Float)arg).floatValue();
        if(price>0)
        {
            System.out.println("油价上涨"+price+"元，空方伤心了！");
        }
        else
        {
            System.out.println("油价下跌"+(-price)+"元，空方高兴了！");
        }
    }
}
