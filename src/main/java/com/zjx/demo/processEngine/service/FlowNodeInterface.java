package com.zjx.demo.processEngine.service;


import com.zjx.demo.processEngine.FlowEngine;
import com.zjx.demo.processEngine.ProcessEngineContext;

/**
 * @author 65454
 */
public interface FlowNodeInterface<T> {

    /**
     * Node 执行方法
     *
     * @param nodeData
     * @param context
     * @return
     */
    T invokeNode(FlowEngine.RunData nodeData, ProcessEngineContext context);

    /**
     * node执行完后执行的方法
     *
     * @param nodeData
     * @param context
     */
    void afterIncode(FlowEngine.RunData nodeData, ProcessEngineContext context);

    /**
     * 从Context中获取次node结果的key
     *
     * @return
     */
    String resultKey();
}
