package com.zjx.demo.flowEngine;


import com.zjx.demo.DemoApplication;
import com.zjx.demo.processEngine.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@SpringBootTest(classes = DemoApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class FlowEngineTest {


    @Test
    public void flowEngineTest() throws Exception {
        FlowNode testFlow = Flow.getTestFlow();
        FlowEngine flowEngine = (FlowEngine) BeanService.getBeanByName("flowEngine");
        FlowEngine.RunData runData = new FlowEngine.RunData();
        runData.setParamOne("one");
        runData.setParamTwo("two");
        ProcessEngineContext context = new ProcessEngineContext();
        flowEngine.execute(testFlow, runData, context);
        Map<String, Object> adaptorMap = context.getResultMap();

        System.out.println(adaptorMap.get("NodeOne"));
        System.out.println(adaptorMap.get("NodeTwo"));
    }
}
