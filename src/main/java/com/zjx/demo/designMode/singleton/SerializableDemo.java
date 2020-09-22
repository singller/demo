package com.zjx.demo.designMode.singleton;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: singleton
 * @date:2020/6/3
 **/
public class SerializableDemo {

    //为了便于理解，忽略关闭流操作及删除文件操作。真正编码时千万不要忘记
    //Exception直接抛出
    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        //Write Obj to file
//        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tempFile"));
//        oos.writeObject(VolatileSingleton.getInstance());
//        //Read Obj from file
//        File file = new File("tempFile");
//        ObjectInputStream ois =  new ObjectInputStream(new FileInputStream(file));
//        VolatileSingleton newInstance = (VolatileSingleton) ois.readObject();
//        //判断是否是同一个对象
//        System.out.println(newInstance == VolatileSingleton.getInstance());

        HashMap<String, String> map = new HashMap<>(1);
        map.put("1","1");
        map.put("2","2");
    }
}
