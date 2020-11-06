package com.zjx.demo.mqconfig;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.mqconfig
 * @date:2020/6/5
 **/
//@Configuration
public class TopicRabbitMqConfig {


    //创建队列
    @Bean
    public Queue topicOneQueue(RabbitAdmin rabbitAdmin){
        Queue topicOneQueue = new Queue(MqProperties.TOPIC_ONE_QUEUE, true);
        rabbitAdmin.declareQueue(topicOneQueue);
        return topicOneQueue;
    }

    //创建队列
    @Bean
    public Queue topicTwoQueue(RabbitAdmin rabbitAdmin){
        Queue topicTwoQueue = new Queue(MqProperties.TOPIC_TWO_QUEUE, true);
        rabbitAdmin.declareQueue(topicTwoQueue);
        return topicTwoQueue;
    }

    //创建topic交换机
    @Bean
    public TopicExchange topicExchange(RabbitAdmin rabbitAdmin) {
        TopicExchange directExchange = new TopicExchange(MqProperties.TOPIC_TEST_EXCHANGE, true, false);
        rabbitAdmin.declareExchange(directExchange);
        return directExchange;
    }

    //绑定交换机跟队列 将队列和交换机绑定, 并设置用于匹配键：TestDirectRouting
    @Bean
    public Binding bindExchageQueueOne(Queue topicOneQueue, TopicExchange topicExchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(topicOneQueue).to(topicExchange).with(MqProperties.TEST_ROUTING_KEY_ONE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public Binding bindExchageQueueOneTwo(Queue topicTwoQueue, TopicExchange topicExchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(topicTwoQueue).to(topicExchange).with(MqProperties.TEST_ROUTING_KEY_ALL);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }


}
