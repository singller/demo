package com.zjx.demo.designMode.tactics.controller;

import com.zjx.demo.designMode.tactics.service.BizKeyService;
import com.zjx.demo.designMode.tactics.service.BizService;
import com.zjx.demo.designMode.tactics.service.BizUnitKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.designMode.tactics.controller
 * @date:2020/6/24
 **/
@RestController
public class BizController {

    @Autowired
    private BizService bizService;

    @Autowired
    private BizKeyService bizKeyService;

    @Autowired
    private BizUnitKeyService bizUnitKeyService;

    @PostMapping("/v1/biz/testSuper")
    public String test2(String order) {
        return bizService.getCheckResultSuper(order);
    }

    @PostMapping("/v1/bizKey/testSuper")
    public String test3(String order,int level) {
        return bizKeyService.getCheckResultSuper(order,level);
    }

    @PostMapping("/v1/bizKeyUnit/testSuper")
    public String test4(String order,int level) {
        return bizUnitKeyService.getCheckResultSuper(order,level);
    }
}
