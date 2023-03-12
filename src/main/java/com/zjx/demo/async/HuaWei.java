package com.zjx.demo.async;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author zjx
 */
public class HuaWei {

    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        // 注意 hasNext 和 hasNextLine 的区别
//        while (in.hasNextInt()) { // 注意 while 处理多个 case
//            String str = in.nextLine();
//            int a = in.nextInt();
//            int b = in.nextInt();
//            System.out.println(reverseWords(str, a, b));
//        }
//        List<String> str = Lists.newArrayList("1,0,0,0,1", "0,0,0,1,1", "0,1,0,1,0", "1,0,0,1,1", "1,0,1,0,1");
//        String s = "10002";
//        System.out.println(s.substring(3));
//        int max = getMax(5, str);
//
//        System.out.println(max);

        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int num = in.nextInt();
        System.out.println(num);
        List<String> strList = new ArrayList<>(num);
        while(in.hasNext()){
            for(int i =0;i<num;i++){
                String str = in.next();
                System.out.println(str);
                strList.add(str);
            }
            System.out.println(getMax(num,strList));
        }
        System.out.println(getMax(num,strList));
    }


    public static String reverseWords(String s, int start, int end) {

        if (start < 0 && end < 0) {
            return s;
        }
        if (start > end) {
            return s;
        }
        String[] s1 = s.split(" ");
        if (start <= 0) {
            start = 0;
        }
        if (end > s1.length) {
            end = s1.length;
        }
        while (start < end) {
            String temp = s1[start];
            s1[start] = s1[end];
            s1[end] = temp;
            start++;
            end--;
        }
        StringBuilder builder = new StringBuilder();
        for (String str : s1) {
            builder.append(str).append(" ");
        }
        return builder.toString();
    }


    public static int getMax(int n, List<String> strList) {
        Integer total = 0;
        for (String str : strList) {
            str = str.replace(",","");
            String max = str;
            for (int j = 1; j < n; j++) {
                String tempStr = str.substring(n-j)+str.substring(0,n-j);
                if (max.compareTo(tempStr)<0) {
                    max = tempStr;
                }
            }
            int maxInt = Integer.parseInt(max, 2);
            total += maxInt;
        }
        return total;
    }

}
