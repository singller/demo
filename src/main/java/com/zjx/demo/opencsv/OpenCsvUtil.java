package com.zjx.demo.opencsv;

import com.opencsv.CSVWriter;
import com.opencsv.bean.*;
import org.assertj.core.util.Lists;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @version: v1.0
 * @description:
 * @author: Create by zjxX
 * @create: 2020-11-24 21:38
 **/
public class OpenCsvUtil {

    /**
     * 解析csv文件并转成bean
     *
     * @param file  csv文件
     * @param clazz 类
     * @return 泛型bean集合
     */
    public static <T> List<T> getCsvData(File file, Class<T> clazz) {
        InputStreamReader in = null;
        try {
            in = new InputStreamReader(new FileInputStream(file), "gbk");
        } catch (Exception e) {
            e.printStackTrace();
        }

        HeaderColumnNameMappingStrategy<T> strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(clazz);

        CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(in)
                .withSeparator(',')
                .withQuoteChar('\'')
                .withMappingStrategy(strategy).build();
        return csvToBean.parse();
    }

    /**
     * 将数据写入CSV并返回给前端
     *
     * @param fileName 文件名
     * @param data     数据
     * @param bean     类型
     * @param response 响应
     */
    public static <T> void writeCSV(String fileName, List<T> data, Class<T> bean, HttpServletResponse response) {
        try {
            response.reset();
            response.setContentType("APPLICATION/OCTET-STREAM");
            fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
            response.setHeader("Content-disposition", "attachment;  filename=" + fileName + ".csv");
            response.setCharacterEncoding("gbk");

            CSVWriter writer = new CSVWriter(response.getWriter());

            CustomMappingStrategy<T> mappingStrategy = new CustomMappingStrategy<>();
            mappingStrategy.setType(bean);
            StatefulBeanToCsvBuilder<T> builder = new StatefulBeanToCsvBuilder<>(writer);
            StatefulBeanToCsv<T> beanToCsv = builder.withMappingStrategy(mappingStrategy)
                    .withSeparator(',').withApplyQuotesToAll(false).build();
            beanToCsv.write(data);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将数据写入CSV并返回给前端
     *
     * @param fileName 文件名
     * @param data     数据
     * @param bean     类型
     */
    public static <T> void writeCSV(String fileName, List<T> data, Class<T> bean,boolean flag) {
        try {
            fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");

            CSVWriter writer = new CSVWriter(new FileWriter(fileName,flag));

            CustomMappingStrategy<T> mappingStrategy = new CustomMappingStrategy<>();
            mappingStrategy.setType(bean);
            StatefulBeanToCsvBuilder<T> builder = new StatefulBeanToCsvBuilder<>(writer);
            StatefulBeanToCsv<T> beanToCsv = builder
                    .withMappingStrategy(!flag==true? mappingStrategy:null)
                    .withSeparator(',')
                    .withApplyQuotesToAll(false)
                    .build();
            beanToCsv.write(data);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {

        CsvDemo demo1 = CsvDemo
                .builder()
                .aaa("aaa")
                .bbb("bbb")
                .ccc("ccc")
                .build();
        ArrayList<CsvDemo> objects = Lists.newArrayList();
        objects.add(demo1);
        writeCSV("C:/temp/test.csv",objects,CsvDemo.class,false);
        CsvDemo demo2 = CsvDemo
                .builder()
                .aaa("aaa2")
                .bbb("bbb2")
                .ccc("ccc2")
                .build();
        ArrayList<CsvDemo> objects1 = Lists.newArrayList();
        objects1.add(demo2);
        writeCSV("C:/temp/test.csv",objects1,CsvDemo.class,true);

    }


}
