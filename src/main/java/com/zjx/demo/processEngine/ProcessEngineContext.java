package com.zjx.demo.processEngine;

import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * @author zjx
 */
@Getter
@Setter
public class ProcessEngineContext {

    private Map<String,Object> resultMap = Maps.newHashMap();

}
