package com.zjx.demo.elasticsearch.controller;

import com.zjx.demo.elasticsearch.dto.BlogModel;
import lombok.Data;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.elasticsearch.controller
 * @date:2020/10/27
 **/
@Data
public class Result {

    private int code;
    private String msg;
    private Object data;

    public static Result success(){
        return new Result(0,"Success");
    }

    public static Result success(Object object){
        return new Result(0,"Success",object);
    }

    public static Result error(){
        return new Result(400,"Error");
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
