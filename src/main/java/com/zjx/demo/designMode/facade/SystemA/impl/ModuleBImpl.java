package com.zjx.demo.designMode.facade.SystemA.impl;

import com.zjx.demo.designMode.facade.SystemA.ModuleB;

public class ModuleBImpl implements ModuleB {
    @Override
    public void operate() {
        System.out.println("实现B模块的功能ModuleAImpl");
    }
}
