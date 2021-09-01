package com.zjx.demo.flowEngine;


import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.zjx.demo.DemoApplication;
import com.zjx.demo.designMode.factory.factorymethold.sharedemo.Share;
import com.zjx.demo.designMode.factory.factorymethold.sharedemo.ShareFactory;
import com.zjx.demo.designMode.prototype.ItemSold;
import com.zjx.demo.designMode.responsibility.DataAggregation;
import com.zjx.demo.processEngine.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest(classes = DemoApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class DesignModeTest {


    @Test
    public void flowEngineTest() throws Exception {
        FlowNode testFlow = Flow.getTestFlow();
        FlowEngine flowEngine = (FlowEngine) BeanService.getBeanByName("flowEngine");
        FlowEngine.RunData runData = new FlowEngine.RunData();
        runData.setParamOne("oneParam");
        runData.setParamTwo("twoParam");
        ProcessEngineContext context = new ProcessEngineContext();
        flowEngine.execute(testFlow, runData, context);
        Map<String, Object> adaptorMap = context.getResultMap();

        System.out.println(adaptorMap.get("NodeOne"));
        System.out.println(adaptorMap.get("NodeTwo"));
    }


    @Autowired
    ShareFactory shareFactory;

    @Test
    public void factoryTest(){
        Share shareFunction = shareFactory.getShareFunction(ShareFactory.EnumShareType.SUCCESS_ORDER.getName());
        String success_order = shareFunction.mainProcess("Success order");
        System.out.println(success_order);
    }


    @Autowired
    private DataAggregation dataAggregation;

    @Test
    public void responsibilityTest(){

        Map map = dataAggregation.convertItemDetail();
        System.out.println(JSON.toJSON(map));
    }


}
