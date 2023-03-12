package com.zjx.demo.data.structure.util;

/**
 * @author zjx
 */
public class DateUtil {

    private long startTime;
    private long endTime;


    public void start() {
        startTime = System.currentTimeMillis();

    }

    public void end() {
        endTime = System.currentTimeMillis();
    }

    public long getElapsedTime() {
        return endTime - startTime;
    }

    public static long timeIt(Runnable method) {
        DateUtil timer = new DateUtil();
        timer.start();
        method.run();
        timer.end();
        System.out.println(method.getClass() + "execute time:" + timer.getElapsedTime());
        return timer.getElapsedTime();
    }


}
