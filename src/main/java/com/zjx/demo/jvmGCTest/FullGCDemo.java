package com.zjx.demo.jvmGCTest;

public class FullGCDemo {

    public static void main(String[] args) throws InterruptedException {
//        Thread.sleep(30000);
//        while (true){
//            loadData();
//        }
        int i=-2147483648;
//        int reverse = reverse(i);
        int i1 = strReverse(i);
        System.out.println(i1);
//        System.out.println(reverse);
    }

    public static void loadData() throws InterruptedException {
        byte [] data =null;
        for(int i=0;i<4;i++){
            data = new byte[10 *1024*1024];
        }
        data =null;

        byte[] data1 = new byte[10 *1024*1024];
        byte[] data2 = new byte[10 *1024*1024];
        byte[] data3 = new byte[10 *1024*1024];
        data3 = new byte[10 *1024*1024];
        Thread.sleep(1000);
    }

    public static int  reverse(int x) {
        long n = 0;
        while(x != 0){
            n = n*10 + x%10;
            x = x/10;
        }
        return (int) n == n?(int)n:0;
    }

    public static int  strReverse(int x) {
        String s = String.valueOf(x);
        int flag =1;
        if(x<0){
            flag = -1;
            s = s.substring(1);
        }
        try{
            return Integer.valueOf(new StringBuilder(s).reverse().toString())*flag;
        }catch (Exception e){
            return 0;
        }

    }
}
