package com.zjx.demo.sort;


import java.util.Arrays;

/*
    插入排序
 */
public class InsertSort {

    public static void main(String[] args) {

        Integer [] a={10,1,35,61,89,36,55};

        sort(a);

        Arrays.asList(a).forEach(System.out::println);
    }

    public static void sort(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            //被插入的数
            int insertVal = arr[i];
            //被插入的位置
            int index = i - 1;
            while (index >= 0 && insertVal < arr[index]) {
                //将arr[index]向后移动
                arr[index + 1] = arr[index];
                //让index向前移动
                --index;
            }
            //把插入的数放入合适的位置
            arr[index + 1] = insertVal;
        }
    }
}
