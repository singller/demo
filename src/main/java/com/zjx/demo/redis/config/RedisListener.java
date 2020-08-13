package com.zjx.demo.redis.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.redis.config
 * @date:2020/8/13
 **/
@Component
@Slf4j
public class RedisListener implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] pattern){

        log.debug("从消息通道={}监听到消息",new String(pattern));
        log.debug("从消息通道={}监听到消息",new String(message.getChannel()));
        log.debug("元消息={}",new String(message.getBody()));

        // 新建一个用于反序列化的对象，注意这里的对象要和前面配置的一样
        // 因为我前面设置的默认序列化方式为GenericJackson2JsonRedisSerializer
        // 所以这里的实现方式为GenericJackson2JsonRedisSerializer
        RedisSerializer serializer=new GenericJackson2JsonRedisSerializer();
        log.debug("反序列化后的消息={}",serializer.deserialize(message.getBody()));
    }




    @Bean
    public  RedisMessageListenerContainer container(RedisConnectionFactory factory) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(factory);
        container.addMessageListener(new RedisListener(), new PatternTopic("demo-channel"));
        return container;

    }

}
