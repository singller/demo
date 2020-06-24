package com.zjx.demo.designMode.tactics.service;

import org.springframework.stereotype.Service;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.designMode.tactics.service
 * @date:2020/6/24
 **/
@Service
public class BizUnitService {

    public String bizOne(String order) {
        return order + "各种花式操作1";
    }
    public String bizTwo(String order) {
        return order + "各种花式操作2";
    }
    public String bizThree(String order) {
        return order + "各种花式操作3";
    }
}
