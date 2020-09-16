package com.zjx.demo.designMode.factory.factorymethold.factory;

import com.zjx.demo.designMode.factory.simpile.product.Product;
import com.zjx.demo.designMode.factory.simpile.product.ProductA;
import com.zjx.demo.designMode.factory.simpile.product.ProductB;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.designMode.factory.factorymethold.factory
 * @date:2020/9/14
 **/
public class ConcreteFactoryB implements FacotryMethod{

    @Override
    public Product create() {
        System.out.println("调用B工厂");
        return new ProductB();
    }
}
