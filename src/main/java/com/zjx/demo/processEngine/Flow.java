package com.zjx.demo.processEngine;

import com.zjx.demo.processEngine.service.Impl.NodeOne;
import com.zjx.demo.processEngine.service.Impl.NodeTwo;

/**
 * @author 65454
 */
public class Flow {
    private static FlowNode testFlow = new FlowNode();

    static {
        testFlow.add(NodeOne.class, new FlowNode.NodeConf());
        testFlow.add(NodeTwo.class, new FlowNode.NodeConf());
        testFlow.add("three", NodeOne.class, new FlowNode.NodeConf());
        testFlow.add("three", NodeTwo.class, new FlowNode.NodeConf());
    }

    public static FlowNode getTestFlow() {
        return testFlow;
    }
}
