package com.zjx.demo.elasticsearch.controller;

import com.zjx.demo.elasticsearch.dao.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.elasticsearch.controller
 * @date:2020/10/29
 **/
public class PhoneController {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private PhoneRepository phoneRepository;


}
