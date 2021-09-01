package com.zjx.demo.designMode.factory.factorymethold.sharedemo;

/**
 * @author 65454
 */
public interface Share {

    /**
     * 获取分享类型
     * @return
     */
    String getShareFunctionType();

    /**
     *
     * @param shareName
     * @return
     */
    String mainProcess(String shareName);
}
