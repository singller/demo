package com.zjx.demo.controller;

import com.zjx.demo.mqconfig.Producer;
import com.zjx.demo.redis.HyperLogLog.HyperLogLogTest;
import com.zjx.demo.redis.service.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.controller
 * @date:2020/6/5
 **/
@RestController
public class RestContorller {

    Map<String,String> map = new HashMap<String,String>(){{
        put("1","2");
        put("3","4");
        put("5","6");
    }};

    @Autowired
    private Producer producer;

    @Autowired
    private Publisher publisher;

    /**
     * 策略模式测试
     * **/
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

    /************************************************************************************/
    /*
    redis队列测试
    * */
    @GetMapping("/redis/{msg}")
    public void redisTest(@PathVariable String msg){
        publisher.publish(msg);
    }


    @Autowired
    HyperLogLogTest hyperLogLogTest;
    /**
     * hyperloglog测试
     * */
    @GetMapping("/redis/hyperloglog")
    public void redisHyperloglogTest(){
        hyperLogLogTest.count();
    }

}
