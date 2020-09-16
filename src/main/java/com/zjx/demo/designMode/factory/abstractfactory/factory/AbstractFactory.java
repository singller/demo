package com.zjx.demo.designMode.factory.abstractfactory.factory;


import com.zjx.demo.designMode.factory.abstractfactory.product.Product1;
import com.zjx.demo.designMode.factory.abstractfactory.product.Product2;

/*
* @Description 抽象工厂的接口
* @Author zhangjianxun
* @Date 2020/9/14 15:11
**/
public interface AbstractFactory {


    public Product1 createProduct1();

    public Product2 createProduct2();
}
