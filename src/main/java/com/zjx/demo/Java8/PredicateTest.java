package com.zjx.demo.Java8;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.Java8
 * @date:2020/8/18
 **/
public class PredicateTest {

    static List<Flower> flowerList = Arrays.asList(new Flower("red", 6), new Flower("yellow", 7), new Flower("pink", 8));

    public static boolean redFilter(Flower flower) {
        return StringUtils.equals(flower.getColor(), "red");
    }

    public static boolean pricelowFilter(Flower flower) {
        return flower.getPrice() < 8;
    }

    public static List<Flower> filterFlower(List<Flower> flowers, Predicate<Flower> p) {
        List<Flower> resList = new ArrayList<>();
        for (Flower flower : flowers) {
            if (p.test(flower)) {
                resList.add(flower);
            }
        }
        return resList;
    }

    public static void main(String[] args) {


        List<String> colorList =  flowerList.stream()
                    .filter(t->t.getPrice()<10)
                    .sorted(Comparator.comparing(Flower::getPrice))
                    .map(Flower::getColor)
                    .collect(Collectors.toList());

        List<Flower> redFlowers = filterFlower(flowerList, PredicateTest::redFilter);
        List<Flower> priceFlowers = filterFlower(flowerList, PredicateTest::pricelowFilter);


        filterFlower(flowerList, (Flower f) -> StringUtils.equals("red", f.getColor()));
        filterFlower(flowerList, (Flower f) -> f.getPrice() < 8);

    }

}

class Flower{

    private String color;

    private int price;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Flower(String collor, int price) {
        this.color = collor;
        this.price = price;
    }
}