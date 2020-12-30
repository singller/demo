package com.zjx.demo.designMode.facade.SystemB;

import com.zjx.demo.designMode.facade.SystemA.SystemAFacade;

public class SystemB {

    public static void main(String[] args) {
        SystemAFacade.operate();
    }
}
