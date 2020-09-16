package com.zjx.demo.designMode.factory.abstractfactory;

import com.zjx.demo.designMode.factory.abstractfactory.factory.ConcreteFactory1;
import com.zjx.demo.designMode.factory.abstractfactory.factory.ConcreteFactory2;
import com.zjx.demo.designMode.factory.abstractfactory.product.Product1;
import com.zjx.demo.designMode.factory.abstractfactory.product.Product2;
import com.zjx.demo.designMode.factory.factorymethold.factory.ConcreteFactoryA;
import com.zjx.demo.designMode.factory.factorymethold.factory.ConcreteFactoryB;
import com.zjx.demo.designMode.factory.simpile.product.Product;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.designMode.factory.abstractfactory
 * @date:2020/9/14
 **/
public class Client {

    public static void main(String[] args) {
        int i = 0;
        Product1 product1 =null;
        Product2 product2 =null;
        while (i < 10) {
            if (i % 2 == 0) {
                ConcreteFactory1 concreteFactory1 = new ConcreteFactory1();
                product1 = concreteFactory1.createProduct1();
                product2 = concreteFactory1.createProduct2();

            } else {
                ConcreteFactory2 concreteFactory2 = new ConcreteFactory2();
                product1 = concreteFactory2.createProduct1();
                product2 = concreteFactory2.createProduct2();
            }
            product1.use();
            product2.use();
            ++i;
        }
    }
}
