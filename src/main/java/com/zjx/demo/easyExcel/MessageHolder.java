package com.zjx.demo.easyExcel;

/**
 * @author catalina
 * @date 2020/8/19
 */
public class MessageHolder {

    private static MessageResource INSTANCE;

    public static void set(MessageResource messageResource) {
        INSTANCE = messageResource;
    }

    public static MessageResource get() {
        return INSTANCE;
    }

}
