package com.zjx.demo.designMode.tactics.service;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.designMode.tactics.service
 * @date:2020/6/24
 **/
@Service
public class BizKeyService {

    /**
     * 业务逻辑分派Map
     * Function为函数式接口，下面代码中 Function<String, String> 的含义是接收一个Stirng类型的变量，返回一个String类型的结果
     */
    private Map<String, Function<String, String>> checkResultDispatcher = new HashMap<>(4);

    /**
     * 初始化 业务逻辑分派Map 其中value 存放的是 lambda表达式
     */
    @PostConstruct
    public void checkResultDispatcherInit() {
        checkResultDispatcher.put("key_订单1", order -> String.format("对%s执行业务逻辑1", order));
        checkResultDispatcher.put("key_订单2", order -> String.format("对%s执行业务逻辑2", order));
        checkResultDispatcher.put("key_订单3", order -> String.format("对%s执行业务逻辑3", order));

    }


    public String getCheckResultSuper(String order, int leverl) {

        String key = getDispatcherKey(order, leverl);
        Function<String, String> result = checkResultDispatcher.get(key);
        if (result != null) {
            //执行这段表达式获得String类型的结果
            return result.apply(order);
        }
        return "不在处理的逻辑中返回业务错误";
    }

    /**
     * 判断条件方法
     */
    private String getDispatcherKey(String order, int level) {
        StringBuilder key = new StringBuilder("key");
        key.append("_" + order + level);
        return key.toString();
    }
}
