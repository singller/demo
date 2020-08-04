package com.zjx.demo.leecode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.leecode
 * @date:2020/7/29
 **/
public class Solution {

    public static int[] twoSum(int[] nums, int target) {

        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if ((nums[i] + nums[j]) == target) {
                    int[] tar = {i, j};
                    return tar;
                }
            }
        }
        return null;
    }

    /*
     * @Description  回文数demo
     * @Param * @param x
     * @Return boolean
     * @Author zhangjianxun
     * @Date 2020/7/30 16:44
     **/
    public static boolean isPalindrome(int x) {
        if (x < 0)
            return false;
        long y = 0;
        int temp = x;
        while (x != 0) {
            y = y * 10 + x % 10;
            x = x / 10;
        }
        y = (int) y;
        if (temp == y) {
            return true;
        }
        return false;
    }

    /*
     * @Description  回文数demo最优
     * @Param * @param x
     * @Return boolean
     * @Author zhangjianxun
     * @Date 2020/7/30 16:44
     **/
    public static boolean isPalindromeFirst(int x) {
        //负数跟末尾是0的数都不是
        if (x < 0 || (x % 10 == 0 && x != 0))
            return false;

        int inverse = 0;
        while (x > inverse) {
            inverse = inverse * 10 + x % 10;
            x /= 10;
        }
        return x == inverse || (x == inverse / 10);
    }


    /* 罗马数字转整数*/
    public int romanToInt(String s) {
        int sum = 0;
        int preNum = getValue(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            int num = getValue(s.charAt(i));
            if (preNum < num) {
                sum -= preNum;
            } else {
                sum += preNum;
            }
            preNum = num;
        }
        sum += preNum;
        return sum;
    }

    public int getValue(char s) {
        switch (s) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }

    /*最大公共前缀*/
    public static String longestCommonPrefix(String[] strs) {

        if (strs == null || strs.length == 0) {
            return "";
        }
        String str = strs[0];
        for (int i = 0; i < str.length(); i++) {
            for (int j = 1; j < strs.length; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != str.charAt(i)) {
                    return str.substring(0, i);
                }
            }
        }
        return str;
    }


    public static void main(String[] args) {
//        int [] a = {2,7,11,15};
//        int [] c = {3,2,4};
//        int b =9;
//        int d= 6;
//        String s = "";
//        int[] ints = twoSum(c, d);
//        System.out.println(ints);
        int i = 12321;
        boolean palindrome = isPalindrome(i);
        System.out.println(palindrome);

        int y = 3;

        int sum = 0;
        sum -= y;
        System.out.println(sum);

        String[] o = {"c", "c"};
        longestCommonPrefix(o);
    }


}
