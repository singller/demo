package com.zjx.demo.java8.BehaviorParameterization;

import org.apache.commons.lang.StringUtils;

import java.util.*;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.java8.BehaviorParameterization
 * @date:2020/8/18
 **/
public class TestOne {

    static List<Flower> flowerList = Arrays.asList(new Flower("red", 6), new Flower("yellow", 7), new Flower("pink", 8));


    public static List<Flower> filterFlower(List<Flower> flowers, FilterPredicate filter) {
        List<Flower> resList = new ArrayList<>();
        for (Flower flower : flowers) {
            if (filter.test(flower)) {
                resList.add(flower);
            }
        }
        return resList;
    }

    public static void main(String[] args) {
        List<Flower> flowers = filterFlower(flowerList, new RedColorFilterPredicate());

        List<Flower> flowers1 = filterFlower(flowerList, new LowPriceFilterPredicate());



        filterFlower(flowerList, (Flower flower) -> flower.getPrice() > 8 && StringUtils.equals("red", flower.getColor()));

        Collections.sort(flowerList,(o1, o2) -> o1.getPrice().compareTo(o2.getPrice()));

        Collections.sort(flowerList, Comparator.comparing(Flower::getPrice));

        flowerList.sort(Comparator.comparing(Flower::getPrice));


    }

}
