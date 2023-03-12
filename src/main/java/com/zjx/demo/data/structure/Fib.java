package com.zjx.demo.data.structure;

import com.zjx.demo.data.structure.util.DateUtil;

/**
 * 斐波那契数
 * <p>
 * 0 1 1 2 3 5 8 13
 *
 * @author zjx
 */
public class Fib {

    public static int fib1(int n) {
        if (n <= 1) {
            return n;
        }
        return fib1(n - 1) + fib1(n - 2);
    }

    public static int fib2(int n) {
        if (n <= 1) {
            return n;
        }
        int first = 0;
        int second = 1;
        for (int i = 0; i < n - 1; i++) {
            int sum = first + second;
            first = second;
            second = sum;
        }
        return second;
    }


    public static void main(String[] args) {
        int n = 20;
        DateUtil.timeIt(() -> System.out.println(fib1(n)));
        DateUtil.timeIt(() -> System.out.println(fib2(n)));
    }
}
