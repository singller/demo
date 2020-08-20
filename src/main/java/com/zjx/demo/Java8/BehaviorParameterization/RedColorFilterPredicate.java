package com.zjx.demo.Java8.BehaviorParameterization;

import org.apache.commons.lang.StringUtils;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.Java8.BehaviorParameterization
 * @date:2020/8/18
 **/
public class RedColorFilterPredicate implements FilterPredicate{
    @Override
    public boolean test(Flower flower) {
        return StringUtils.equals("red",flower.getColor());
    }
}
