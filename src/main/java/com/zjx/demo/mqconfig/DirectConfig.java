package com.zjx.demo.mqconfig;


import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;


/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.mqconfig
 * @date:2020/6/5
 **/
@Configuration
public class DirectConfig {

    @Bean
    RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    //队列 起名：directQueue
    @Bean
    public Queue directQueue(RabbitAdmin rabbitAdmin) {
        // durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
        // exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
        // autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
        //   return new Queue("TestDirectQueue",true,true,false);

        //一般设置一下队列的持久化就好,其余两个就是默认false
        Queue directQueue = new Queue(MqProperties.TEST_QUEUE, true);
        rabbitAdmin.declareQueue(directQueue);
        return directQueue;
    }

    //Direct交换机
    @Bean
    public DirectExchange directExchange(RabbitAdmin rabbitAdmin) {
        DirectExchange directExchange = new DirectExchange(MqProperties.TEST_EXCHANGE, true, false);
        rabbitAdmin.declareExchange(directExchange);
        return directExchange;
    }

    //绑定交换机跟队列 将队列和交换机绑定, 并设置用于匹配键：TestDirectRouting
    @Bean
    public Binding bindExchageQueue(Queue directQueue, DirectExchange directExchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(directQueue).to(directExchange).with(MqProperties.TEST_ROUTING_KEY);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    //初始化template
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2Converter());
        return rabbitTemplate;
    }

    //jason转化
    @Bean
    public Jackson2JsonMessageConverter jackson2Converter() {
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        return converter;
    }




}
