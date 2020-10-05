package com.zjx.demo.sort;

import java.util.Arrays;

/*
    冒泡排序

 */
public class BubbleSort {

    public static void main(String[] args) {
        Integer [] a={10,1,35,61,89,36,55};

        bubbleSort(a,a.length);
//        Arrays.asList(a).forEach(a -> System.out.println(a));
//        Arrays.asList(a).forEach(System.out::println);



        // Lambda 表达式遍历（JDK 1.8）
        System.out.println("\n第三种方式：Lambda 表达式遍历 Array 数组");
        Arrays.asList(a).forEach(item -> System.out.println(item));

        // Lambda 表达式遍历（JDK 1.8）
        System.out.println("\n第四种方式：Lambda 表达式遍历 Array 数组");
        Arrays.asList(a).forEach(System.out::println);

    }
    public static void bubbleSort(Integer []array,int n){
        int i,j;
        for(i=0;i<n;i++){
            for(j=1;j<n-i;j++){
                if(array[j-1] >array[j]){//前边的数据大于后边的数据就交换
                    int temp;
                    //将j-1 与j 数据进行交换
                    temp = array[j-1];
                    array[j-1] = array[j];
                    array[j] = temp;
                }
            }
        }

    }
}
