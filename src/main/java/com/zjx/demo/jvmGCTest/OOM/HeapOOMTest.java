package com.zjx.demo.jvmGCTest.OOM;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.jvmGCTest.OOM
 * @date:2020/7/29
 **/
public class HeapOOMTest {

    public static void main(String[] args) {
        int counter = 0;
        List<Object> list =new ArrayList<Object>();
        while (true){
            list.add(new Object());
            System.out.println("当前创建了第"+(++counter)+"个对象");
        }
    }
}
