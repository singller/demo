package com.zjx.demo.leecode;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.leecode  实现字符串反转
 * @date:2020/9/22
 **/
public class StringReversed {

    public static void main(String[] args) {
        String str = "Zhang Da X";
        System.out.println("原始串" + str);
        String reversed = reversed(str);
        System.out.println("反转串" + reversed);
    }

    public static String reversed(String str) {
        return str.isEmpty() ? str : reversed(str.substring(1)) + str.charAt(0);
    }

}
