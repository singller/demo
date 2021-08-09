package com.zjx.demo.processEngine;

import com.zjx.demo.processEngine.service.FlowNodeInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.concurrent.Callable;

/**
 * @author 65454
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NodeExecuteTask implements Callable {

    private FlowNodeInterface flowNodeInterface;
    private FlowEngine.RunData runData;
    private ProcessEngineContext context;


    @Override
    public Object call() throws Exception {
        return excute();
    }

    public Object excute() {
        Object o = flowNodeInterface.invokeNode(runData, context);
        flowNodeInterface.afterIncode(runData, context);
        return o;
    }
}
