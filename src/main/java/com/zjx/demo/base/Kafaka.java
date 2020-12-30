package com.zjx.demo.base;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.base  理解jvm内存 demo
 * @date:2020/7/23
 **/
@Component
public class Kafaka {

    public static RelicaManager relicaManager = new RelicaManager();

    private String time;
    private String SCB_QR = "SCB_QR.csv";
    private String SCB_BILL_PAYMENT = "SCB_BILL_PAYMENT.csv";

    private List<String> fileNameList = new ArrayList<>();

    @PostConstruct
    public void initDate() {
        DateFormat df1 = DateFormat.getDateInstance(DateFormat.SHORT, Locale.CHINA);
        String format = df1.format(new Date());
        time = format;
        fileNameList.add(SCB_QR + time);
        fileNameList.add(SCB_BILL_PAYMENT + time);
    }

    public void parser() {
        for (String str : fileNameList) {
            System.out.println(str);
        }
    }
}

class RelicaManager {

    private RelicaFetcher relicaFetcher = new RelicaFetcher();

    public void load() {
        boolean flag = true;
        System.out.println(flag);
    }
}

class RelicaFetcher {


}


