package com.zjx.demo.mqconfig;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author: create by zhangjianxun
 * @version: v1.0 消息发送者
 * @description: com.zjx.demo.mqconfig
 * @date:2020/6/5
 **/
@Component
public class Producer {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        for (int i = 0; i < 10; i++) {
            String content = "direct-test".concat(new Date().toString());
            System.out.println("direct-send:" + i + content);
            rabbitTemplate.convertAndSend("test-queue", content);
        }

    }

    public void sendByTopicOne() {
        for (int i = 0; i < 10; i++) {
            String content = "topic-one-test".concat(new Date().toString());
            System.out.println("topic-one-send:" + i + content);
            rabbitTemplate.convertAndSend(MqProperties.TOPIC_TEST_EXCHANGE, MqProperties.TEST_ROUTING_KEY_ONE, content);
        }

    }

    public void sendByTopicTwo() {
        for (int i = 0; i < 10; i++) {
            String content = "topic-two-test".concat(new Date().toString());
            System.out.println("topic-two-send:" + i + content);
            rabbitTemplate.convertAndSend(MqProperties.TOPIC_TEST_EXCHANGE, MqProperties.TEST_ROUTING_KEY_TWO, content);
        }

    }
}
