package com.zjx.demo.DynamicBean;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ContentRowHeight(20)
@ColumnWidth(30)
public class MerchantReconciliationMultiLanguageFileExcel {

    @ExcelProperty({"Transaction Type","1231231"})
    private String tradeType;

    @ExcelProperty("Business Order No.")
    private String outTradeNo;

    @ExcelProperty("Transaction Order No.")
    private String tradeNo;

    @ExcelProperty("Request Time")
    private String outTradeTime;

    @ExcelProperty(value = "Amount")
    private BigDecimal amount;

    @ExcelProperty("Service Charge")
    private String fee;

    @ExcelProperty("Charge Detail")
    private String feeDetail;

    @ExcelProperty("Currency")
    private String cur;

    @ExcelProperty("Transaction Success Time")
    private String finalTime;


    @ExcelProperty("Payment Status")
    private String status;

    @ExcelProperty("Payment Method")
    private String payInstrumentName;
}
