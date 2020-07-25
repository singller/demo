package com.zjx.demo.base;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.base  理解jvm内存 demo
 * @date:2020/7/23
 **/
public class Kafaka {

    public static RelicaManager relicaManager = new RelicaManager();

}

class RelicaManager{

    private RelicaFetcher relicaFetcher = new RelicaFetcher();

    public void load(){
        boolean flag = true;
        System.out.println(flag);
    };
}

class RelicaFetcher{

}
