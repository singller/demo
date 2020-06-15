package com.zjx.demo.config;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.config
 * @date:2020/6/5
 **/
@Component
public class Customer1 {

//    @RabbitListener(queues = MqProperties.TEST_QUEUE)
    public void process(String hello) {
        System.out.println("Receiver 2 : " + hello);
    }

}
