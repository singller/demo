package com.zjx.demo.designMode.factory.abstractfactory.product;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.designMode.factory.simpile.product
 * @date:2020/9/14
 **/
public class ProductB implements Product1 {
    @Override
    public void use() {
        System.out.println("产品1B");
    }
}
