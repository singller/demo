package com.zjx.demo.jsonmock;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.zjx.demo.utils.ResourceHelper;
import org.powermock.reflect.Whitebox;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Stack;

/**
 * @author 65454
 * 简化数据模拟代码
 */
@Component
public class DataSimulation {

    private static final String path = "src/test/resources/";

        public void classAttributesConvert(){

            String text = ResourceHelper.getResourceAsString(getClass(), path + "languageMap.json");
            Map<Long, String> languageMap = JSON.parseObject(text, new TypeReference<Map<Long, String>>() {});
            Whitebox.setInternalState( "languageMap", languageMap);
        }


    public static void main(String[] args) {
        Stack stack = new Stack();
    }

}
