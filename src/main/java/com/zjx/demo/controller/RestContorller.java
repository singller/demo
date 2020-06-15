package com.zjx.demo.controller;

import com.zjx.demo.config.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.controller
 * @date:2020/6/5
 **/
@RestController
public class RestContorller {

    @Autowired
    private Producer producer;

    @GetMapping("/send")
    public void sendTest(){
        producer.send();
    }

    @GetMapping("/send/one")
    public void sendTestOne(){
        producer.sendByTopicOne();
    }
    @GetMapping("/send/two")
    public void sendTestTwo(){
        producer.sendByTopicTwo();
    }


}
