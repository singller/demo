package com.zjx.demo.designMode.responsibility;

import com.google.common.collect.Maps;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 65454
 */
@Service
public class DataAggregation {

    @Autowired
    private SkuInfoHandler skuInfoHandler;

    @Autowired
    private ItemInfoHandler itemInfoHandler;

    public Map convertItemDetail() {
        HashMap<String, Object> result = Maps.newHashMap();
        result.put("skuInfoHandler", skuInfoHandler.doRequest("sku信息组装"));
        result.put("itemInfoHandler", itemInfoHandler.doRequest("item信息组装"));
        return result;
    }

}
