package com.zjx.demo.jvmGCTest;

public class Demo {

    public static void main(String[] args) throws Exception {
        Thread.sleep(30000);
        while (true){
            loandData();
        }
    }

    public static void loandData() throws InterruptedException {
        byte [] data =null;
        for(int i=0;i<50;i++){
            data= new byte[100*1024];
        }
        data = null;
        Thread.sleep(1000);
    }
}
