package com.zjx.demo.mqconfig.TopicConsumer;

import com.zjx.demo.mqconfig.MqProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.mqconfig.TopicConsumer
 * @date:2020/6/5
 **/
@Component
public class ConsumerOne {

    @RabbitListener(queues = MqProperties.TOPIC_ONE_QUEUE )
    public void process(String hello) {


        System.out.println("Receiver Topic One : " + hello);
    }
}
