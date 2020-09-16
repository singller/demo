package com.zjx.demo.designMode.factory.abstractfactory.factory;

import com.zjx.demo.designMode.factory.abstractfactory.product.Product1;
import com.zjx.demo.designMode.factory.abstractfactory.product.Product2;
import com.zjx.demo.designMode.factory.abstractfactory.product.Product2A;
import com.zjx.demo.designMode.factory.abstractfactory.product.ProductA;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.designMode.factory.abstractfactory.factory
 * @date:2020/9/14
 **/
public class ConcreteFactory1 implements AbstractFactory {
    @Override
    public Product1 createProduct1() {
        System.out.println("使用具体工厂1创建产品1A");
        return new ProductA();
    }

    @Override
    public Product2 createProduct2() {
        System.out.println("使用具体工厂1创建产品2A");
        return new Product2A();
    }
}
