package com.zjx.demo.elasticsearch.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.elasticsearch.dto
 * @date:2020/10/29
 **/
@Data
@Accessors(chain = true)
@Document(indexName = "springboot_elasticsearch_example_phone")
public class PhoneModel implements Serializable {
    private static final long serialVersionUID = -5087658155687251393L;

    /* ID */
    @Id
    private String id;

    /* 名称 */
    private String name;

    /* 颜色，用英文分号(;)分隔 */
    private String colors;

    /* 卖点，用英文分号(;)分隔 */
    private String sellingPoints;

    /* 价格 */
    private String price;

    /* 产量 */
    private Long yield;

    /* 销售量 */
    private Long sale;

    /* 上市时间 */
    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date marketTime;

    /* 数据抓取时间 */
    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

}
