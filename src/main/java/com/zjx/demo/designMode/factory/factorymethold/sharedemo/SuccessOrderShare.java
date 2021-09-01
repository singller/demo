package com.zjx.demo.designMode.factory.factorymethold.sharedemo;


import org.springframework.stereotype.Service;

/**
 * @author 65454
 */
@Service
public class SuccessOrderShare implements Share{
    @Override
    public String getShareFunctionType() {
        return ShareFactory.EnumShareType.SUCCESS_ORDER.getName();
    }

    @Override
    public String mainProcess(String shareName) {
        return shareName;
    }
}
