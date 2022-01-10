package com.zjx.demo.DynamicBean;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MerchantReconciliationFileCsv {

    @CsvBindByPosition(position = 0)
    @CsvBindByName(column = "业务订单号")
    private String outTradeNo;

    @CsvBindByPosition(position = 1)
    @CsvBindByName(column = "交易订单号")
    private String tradeNo;

    @CsvBindByPosition(position = 2)
    @CsvBindByName(column = "交易类型")
    private String tradeType;

    @CsvBindByPosition(position = 3)
    @CsvBindByName(column = "请求时间")
    private String outTradeTime;

    @CsvBindByPosition(position = 4)
    @CsvBindByName(column = "订单金额")
    private String amount;

    @CsvBindByPosition(position = 5)
    @CsvBindByName(column = "手续费")
    private String fee;

    @CsvBindByPosition(position = 6)
    @CsvBindByName(column = "手续费明细")
    private String feeDetail;

    @CsvBindByPosition(position = 7)
    @CsvBindByName(column = "币种")
    private String cur;

    @CsvBindByPosition(position = 8)
    @CsvBindByName(column = "交易成功时间")
    private String finalTime;

    @CsvBindByPosition(position = 9)
    @CsvBindByName(column = "备注")
    private String remark;

    @CsvBindByPosition(position = 10)
    @CsvBindByName(column = "支付状态")
    private String status;

    @CsvBindByPosition(position = 11)
    @CsvBindByName(column = "支付方式")
    private String payInstrumentName;
}
