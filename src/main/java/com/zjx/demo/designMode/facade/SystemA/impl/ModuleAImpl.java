package com.zjx.demo.designMode.facade.SystemA.impl;

import com.zjx.demo.designMode.facade.SystemA.ModuleA;

public class ModuleAImpl implements ModuleA {
    @Override
    public void operate() {
        System.out.println("实现B模块的功能ModuleAImpl");
    }
}
