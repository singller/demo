package com.zjx.demo.mqconfig;

public interface MqProperties {

    //交换机
    String TEST_EXCHANGE = "ex-test";
    String TOPIC_TEST_EXCHANGE = "topic-ex-test";
    //队列
    String TEST_QUEUE= "test-queue";
    String TOPIC_ONE_QUEUE= "topic.one.queue";
    String TOPIC_TWO_QUEUE= "topic.two.queue";

    //routingkey
    String TEST_ROUTING_KEY= "test-routing";
    String TEST_ROUTING_KEY_ONE= "topic.one.routing";
    String TEST_ROUTING_KEY_TWO= "topic.two";
    String TEST_ROUTING_KEY_ALL= "topic.#";

}
