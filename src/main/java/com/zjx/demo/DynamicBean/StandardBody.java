package com.zjx.demo.DynamicBean;


import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * @author zjx
 */
@Getter
@Setter
public class StandardBody {

    @Order("")
    @FieldDesc("金融交换订单号")
    private String transactionNo;


    @Order("")
    @FieldDesc("交易成功时间")
    private String tranSuccTime;

    @Order("")
    @FieldDesc("交易成功时间")
    private String tranSuccDate;

    @Order("")
    @FieldDesc("订单金额")
    private String orderAmount;

    @Order("")
    @FieldDesc("手续费")
    private String serviceFee;

    @Order("")
    @FieldDesc("费率")
    private String rate;

    @Order("")
    @FieldDesc("币种")
    private String cur;

    @Order("")
    @FieldDesc("金融机构订单号")
    private String fiTransactionNo;

}
