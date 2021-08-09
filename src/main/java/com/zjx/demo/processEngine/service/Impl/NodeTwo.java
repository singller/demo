package com.zjx.demo.processEngine.service.Impl;

import com.zjx.demo.processEngine.FlowEngine;
import com.zjx.demo.processEngine.ProcessEngineContext;
import com.zjx.demo.processEngine.service.FlowNodeInterface;
import org.springframework.stereotype.Service;

/**
 * @author 65454
 */
@Service
public class NodeTwo implements FlowNodeInterface {
    @Override
    public Object invokeNode(FlowEngine.RunData nodeData, ProcessEngineContext context) {
        System.out.println("执行方法" + nodeData.getParamTwo());
        return nodeData.getParamTwo();
    }

    @Override
    public void afterIncode(FlowEngine.RunData nodeData, ProcessEngineContext context) {

    }

    @Override
    public String resultKey() {
        return "NodeTwo";
    }
}
