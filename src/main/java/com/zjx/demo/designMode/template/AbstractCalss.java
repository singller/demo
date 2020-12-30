package com.zjx.demo.designMode.template;


/**
 * 场景：
 * （1）我们有多种优惠策略
 * （2）不同的优惠策略在计算的价格的时候，有一些通用的基础逻辑
 * （3）每种优惠策略还有一些是自己特殊的价格计算的逻辑
 */
public abstract class AbstractCalss {

    public void templateMethod() {
        System.out.println("处理优惠打折相关的基础通用逻辑");
        // 但是对于优惠打折具体的处理逻辑，交给不同的折扣类型子类自己去实现
        method1();
        method2();
        method3();
    }

    public abstract void method1();

    public abstract void method2();

    public abstract void method3();
}
