package com.zjx.demo.designMode.factory.simpile;

import com.zjx.demo.designMode.factory.simpile.product.Product;
import com.zjx.demo.designMode.factory.simpile.product.ProductA;
import com.zjx.demo.designMode.factory.simpile.product.ProductB;
import org.apache.commons.lang.StringUtils;

/**
 * @author: create by zhangjianxun
 * @version: v1.0   简单工厂模式
 * @description: com.zjx.demo.designMode.factory.simpile
 * @date:2020/9/14
 **/
public class SimpileFactory {

    /*
     * @Description 生产具体产品
     * @Param * @param type
     * @Return com.zjx.demo.designMode.factory.simpile.product.Product1
     * @Author zhangjianxun
     * @Date 2020/9/14 14:01
     **/
    public Product createProduct(String type) {

        Product product = null;
        switch (type) {
            case "A":
                product = new ProductA();
                break;
            case "B":
                product = new ProductB();
                break;
        }
        return product;

    }


    public static void main(String[] args) {
        String str = StringUtils.EMPTY;
        int i = 0;
        SimpileFactory factory = new SimpileFactory();
        while (i < 10) {
            if (i % 2 == 0) {
                str = "A";
            } else {
                str = "B";
            }
            Product product = factory.createProduct(str);
            product.use();
            ++i;
        }
    }

}
