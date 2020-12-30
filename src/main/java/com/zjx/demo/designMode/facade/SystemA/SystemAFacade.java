package com.zjx.demo.designMode.facade.SystemA;

import com.zjx.demo.designMode.facade.SystemA.impl.ModuleAImpl;
import com.zjx.demo.designMode.facade.SystemA.impl.ModuleBImpl;
import com.zjx.demo.designMode.facade.SystemA.impl.ModuleCImpl;

public class SystemAFacade {

    public static void operate() {
        ModuleA moduleA = new ModuleAImpl();
        moduleA.operate();

        ModuleB moduleB = new ModuleBImpl();
        moduleB.operate();

        ModuleC moduleC = new ModuleCImpl();
        moduleC.operate();
    }
}
