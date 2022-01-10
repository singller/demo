package com.zjx.demo.designMode.template;

/**
 * @author 65454
 */
public class ConcreteClass extends AbstractClass {
    @Override
    public void method1() {
        System.out.println("9折具体逻辑实现");
    }

    @Override
    public void method2() {
        System.out.println("8折具体逻辑实现");
    }

    @Override
    public void method3() {
        System.out.println("7折具体逻辑实现");
    }
}
