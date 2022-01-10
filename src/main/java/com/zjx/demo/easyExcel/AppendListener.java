package com.zjx.demo.easyExcel;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;

public interface AppendListener {
    void append(ExcelWriter excelWriter, WriteSheet writeSheet);
}