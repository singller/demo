package com.zjx.demo.redis.bloomfilter;

import com.zjx.demo.designMode.factory.abstractfactory.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.redis.bloomfilter
 * @date:2020/9/22
 **/
public class BloomTest {

    private String chars;
    {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            builder.append((char) ('a' + i));
        }
        chars = builder.toString();
    }

    private String randomString(int n) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int idx = ThreadLocalRandom.current().nextInt(chars.length());
            builder.append(chars.charAt(idx));
        }
        return builder.toString();
    }

    private List<String> randomUsers(int n) {
        List<String> users = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            users.add(randomString(64));
        }
        return users;
    }

    public static void main(String[] args) {
        BloomTest bloomer = new BloomTest();
        List<String> users = bloomer.randomUsers(100000);
        List<String> usersTrain = users.subList(0, users.size() / 2);
        List<String> usersTest = users.subList(users.size() / 2, users.size());

        Client client = new Client();
//        client.delete("codehole");
        for (String user : usersTrain) {
//            client.add("codehole", user);
        }
        int falses = 0;
        for (String user : usersTest) {
//            boolean ret = client.exists("codehole", user);
//            if (ret) {
//                falses++;
//            }
        }
//        System.out.printf("%d %d\n", falses, usersTest.size());
//        client.close();
    }
}
