package com.zjx.demo.Java8.BehaviorParameterization;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.Java8.BehaviorParameterization
 * @date:2020/8/18
 **/
public class Flower {

    private String color;

    private Integer price;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Flower(String collor, Integer price) {
        this.color = collor;
        this.price = price;
    }
}
