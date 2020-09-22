package com.zjx.demo.java8.BehaviorParameterization;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.java8.BehaviorParameterization
 * @date:2020/8/18
 **/
public class LowPriceFilterPredicate implements FilterPredicate{
    @Override
    public boolean test(Flower flower) {
        return flower.getPrice() < 8;
    }
}
