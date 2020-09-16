package com.zjx.demo.designMode.factory.factorymethold;

import com.zjx.demo.designMode.factory.factorymethold.factory.ConcreteFactoryA;
import com.zjx.demo.designMode.factory.factorymethold.factory.ConcreteFactoryB;
import com.zjx.demo.designMode.factory.simpile.SimpileFactory;
import com.zjx.demo.designMode.factory.simpile.product.Product;
import org.apache.commons.lang.StringUtils;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.designMode.factory.factorymethold
 * @date:2020/9/14
 **/
public class client {
    public static void main(String[] args) {
        int i = 0;
        Product product =null;
        while (i < 10) {
            if (i % 2 == 0) {

                product = new ConcreteFactoryA().create();
            } else {
                product = new ConcreteFactoryB().create();
            }
            product.use();
            ++i;
        }
    }
}
