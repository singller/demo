package com.zjx.demo.designMode.facade.SystemA.impl;

import com.zjx.demo.designMode.facade.SystemA.ModuleC;

public class ModuleCImpl implements ModuleC {
    @Override
    public void operate() {
        System.out.println("实现C模块的功能ModuleAImpl");
    }
}
