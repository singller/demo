package com.zjx.demo.batch;

import org.springframework.batch.item.function.FunctionItemProcessor;

import java.util.function.Function;

public class Person1ItemProcessor extends FunctionItemProcessor {
    public Person1ItemProcessor(Function function) {
        super(function);
        System.out.println(function);
    }
}
