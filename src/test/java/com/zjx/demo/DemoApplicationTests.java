package com.zjx.demo;

import com.zjx.demo.mqconfig.Customer;
import com.zjx.demo.mqconfig.Producer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private Producer producer;

    @Autowired
    private Customer customer;

    @Test
    void send() {
        ConcurrentHashMap<Integer,Integer> map = new ConcurrentHashMap<>();
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>(5);
        objectObjectHashMap.put(1,1);
    }

}
