package com.zjx.demo.hutoolTest;

import cn.hutool.core.text.csv.CsvData;
import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvRow;
import cn.hutool.core.text.csv.CsvUtil;

import java.io.File;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @version: v1.0
 * @description: hutool各种功能测试
 * @author: Create by zjx
 * @create: 2020-11-19 11:42
 **/
public class HutoolTest {

    static String encode = "UTF-8";

    /**
     * scv文件测试
     */
    public void parserCsc() {
        File file = new File("");
        String separator = File.separator;
        CsvReader reader = CsvUtil.getReader();
        //设置文件读取的分隔符
        reader.setFieldSeparator(separator.toCharArray()[0]);
        //根据特定的编码方式读取File的内容
        CsvData csvData = reader.read(file, Charset.forName(encode));
        //读取文件中的所有的行数据
        List<CsvRow> rows = csvData.getRows();
        //若有标题，则获取首行标题
        List<String> firstRawList = rows.get(0).getRawList();
    }
}
