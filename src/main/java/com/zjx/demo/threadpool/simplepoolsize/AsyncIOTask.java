package com.zjx.demo.threadpool.simplepoolsize;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.threadpool.simplepoolsize
 * @date:2020/9/27
 **/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 自定义的异步IO任务
 * @author Will
 *
 */
class AsyncIOTask implements Runnable {

    @Override
    public void run() {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            String getURL = "http://baidu.com";
            URL getUrl = new URL(getURL);

            connection = (HttpURLConnection) getUrl.openConnection();
            connection.connect();
            reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                // empty loop
            }
        }

        catch (IOException e) {

        } finally {
            if(reader != null) {
                try {
                    reader.close();
                }
                catch(Exception e) {

                }
            }
            connection.disconnect();
        }

    }

}
