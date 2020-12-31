package com.zjx.demo.classloader;

/**
 * @version: v1.0
 * @description: 验证类加载器
 * @author: Create by zjx
 * @create: 2020-12-30 16:17
 **/
public class ClassLoaderParentMain {

    public static void main(String[] args) {
        ClassLoader classLoader = ClassLoaderParentMain.class.getClassLoader();
        ClassLoader parent = classLoader.getParent();
        ClassLoader parent1 = parent.getParent();
        System.out.println(classLoader);
        System.out.println(parent);
        System.out.println(parent1);
    }
}
