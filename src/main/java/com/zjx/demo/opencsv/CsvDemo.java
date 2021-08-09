package com.zjx.demo.opencsv;


import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class CsvDemo {


    @CsvBindByPosition(position = 0)
    @CsvBindByName(column = "对账批次号")
    private String aaa;

    @CsvBindByPosition(position = 1)
    @CsvBindByName(column = "对账批次号")
    private String bbb;

    @CsvBindByPosition(position = 2)
    @CsvBindByName(column = "对账批次号")
    private String ccc;

}
