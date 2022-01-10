package com.zjx.demo.easyExcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: wenjun
 * @date 2019/7/3 18:12
 * @description:
 */
public class Excels {

    /**
     * 文件类型
     */
    public static String UPLOAD_FILE_SUFFIX_XLS = "XLS";
    public static String UPLOAD_FILE_SUFFIX_XLSX = "XLSX";

    /**
     * 导出excel
     *
     * @param fileName
     * @param data
     * @param head
     * @return
     */
    public static InputStream exportExcel(String fileName, List<?> data, Class head) {
        OutputStream outputStream = new ByteArrayOutputStream();
        ExcelWriterBuilder write = EasyExcel.write(outputStream, head);
        ExcelWriterSheetBuilder sheet = write.sheet(1, fileName);
        sheet.doWrite(data);
        ByteArrayOutputStream byteArrayOutputStream = (ByteArrayOutputStream) outputStream;
        ByteArrayInputStream swapStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        return swapStream;
    }

    /**
     * 导出excel(分批写入)
     *
     * @param fileName
     * @param appendListener
     * @param head
     * @return
     */
    public static InputStream exportAppendExcel(String fileName, AppendListener appendListener, Class head) {
        OutputStream outputStream = new ByteArrayOutputStream();
        ExcelWriterBuilder write = EasyExcel.write(outputStream, head);
        ExcelWriterSheetBuilder sheet = write.sheet(1, fileName);

        ExcelWriter excelWriter = write.build();
        appendListener.append(excelWriter, sheet.build());
        excelWriter.finish();

        ByteArrayOutputStream byteArrayOutputStream = (ByteArrayOutputStream) outputStream;
        ByteArrayInputStream swapStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        return swapStream;
    }

    /**
     * 导出多语言excel(分批写入)
     *
     * @param fileName
     * @param appendListener
     * @param head
     * @return
     */
    public static InputStream exportAppendExcelI18n(String fileName, AppendListener appendListener, Class head) {
        OutputStream outputStream = new ByteArrayOutputStream();
        ExcelWriterBuilder write = write(outputStream, head);
        ExcelWriterSheetBuilder sheet = write.sheet(1, fileName);

        ExcelWriter excelWriter = write.build();
        appendListener.append(excelWriter, sheet.build());
        excelWriter.finish();

        ByteArrayOutputStream byteArrayOutputStream = (ByteArrayOutputStream) outputStream;
        ByteArrayInputStream swapStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        return swapStream;
    }


    /**
     * 导出excel 多语言
     *
     * @param fileName
     * @param data
     * @param head
     * @return
     */
    public static InputStream exportExcelI18n(String fileName, List<?> data, Class head) {
        OutputStream outputStream = new ByteArrayOutputStream();
        ExcelWriterBuilder write = write(outputStream, head);
        ExcelWriterSheetBuilder sheet = write.sheet(1, fileName);
        sheet.doWrite(data);
        ByteArrayOutputStream byteArrayOutputStream = (ByteArrayOutputStream) outputStream;
        ByteArrayInputStream swapStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        return swapStream;
    }

    public static InputStream exportCustExcelI18n(String fileName, List<List<Object>> data, List<List<String>> head) {
        OutputStream outputStream = new ByteArrayOutputStream();
        ExcelWriterBuilder write = write(outputStream, head);
        ExcelWriterSheetBuilder sheet = write.sheet(1, fileName);
        sheet.doWrite(data);
        ByteArrayOutputStream byteArrayOutputStream = (ByteArrayOutputStream) outputStream;
        ByteArrayInputStream swapStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        return swapStream;
    }

    private static ExcelWriterBuilder write(OutputStream outputStream, List<List<String>> head) {
        ExcelWriterBuilder excelWriterBuilder = new ExcelWriterBuilder();
        excelWriterBuilder.file(outputStream);
        if (head == null) {
            throw new IllegalArgumentException("head must not be null");
        }
        excelWriterBuilder.head(head);
        excelWriterBuilder.registerWriteHandler(new I18nCellWriteHandler());
        excelWriterBuilder.registerWriteHandler(new LongestMatchColumnWidthStyleStrategy());
        return excelWriterBuilder;
    }

    private static ExcelWriterBuilder write(OutputStream outputStream, Class head) {
        ExcelWriterBuilder excelWriterBuilder = new ExcelWriterBuilder();
        excelWriterBuilder.file(outputStream);
        if (head == null) {
            throw new IllegalArgumentException("head must not be null");
        }
        excelWriterBuilder.head(head);
        excelWriterBuilder.registerWriteHandler(new I18nCellWriteHandler());
        return excelWriterBuilder;
    }

    public static ExcelReaderBuilder readI18n(InputStream inputStream, Class head, I18nAnalysisListener analysisListener) {
        ExcelReaderBuilder excelReaderBuilder = new ExcelReaderBuilder();
        excelReaderBuilder.file(inputStream);
        if (head == null || analysisListener == null) {
            throw new IllegalArgumentException("head or analysisListener must not be null");
        }
        excelReaderBuilder.head(head);
        excelReaderBuilder.registerReadListener(analysisListener);
        return excelReaderBuilder;
    }

    public static ExcelReaderBuilder read(InputStream inputStream, Class head, AnalysisEventListener analysisListener) {
        ExcelReaderBuilder excelReaderBuilder = new ExcelReaderBuilder();
        excelReaderBuilder.file(inputStream);
        if (head == null || analysisListener == null) {
            throw new IllegalArgumentException("head or analysisListener must not be null");
        }
        excelReaderBuilder.head(head);
        excelReaderBuilder.registerReadListener(analysisListener);
        return excelReaderBuilder;
    }

    public static <T> List<T> read(InputStream inputStream, Class<T> head) {
        ExcelReaderBuilder excelReaderBuilder = new ExcelReaderBuilder();
        excelReaderBuilder.file(inputStream);
        if (head == null) {
            throw new IllegalArgumentException("head or analysisListener must not be null");
        }
        BaseAnalysisEventListener<T> listener = new BaseAnalysisEventListener<T>();
        excelReaderBuilder.head(head);
        excelReaderBuilder.registerReadListener(listener);
        excelReaderBuilder.sheet().doRead();
        return listener.getDataList();
    }

    private static class BaseAnalysisEventListener<T> extends AnalysisEventListener<T> {

        private List<T> dataList = new ArrayList<>();

        public List<T> getDataList() {
            return dataList;
        }

        @Override
        public void invoke(T data, AnalysisContext context) {
            dataList.add(data);
        }

        @Override
        public void doAfterAllAnalysed(AnalysisContext context) {

        }
    }

    /**
     * 是否是上传文件是否为".xls".xlsx"结尾
     *
     * @param fileName 上传文件名
     * @return
     */
    public static void checkFileNameSuffix(String fileName) throws Exception {
        if (StringUtils.isBlank(fileName)) {
            throw new Exception();
        }
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        if (!(UPLOAD_FILE_SUFFIX_XLS.equals(suffix.toUpperCase()) ||
                UPLOAD_FILE_SUFFIX_XLSX.equals(suffix.toUpperCase()))) {
            throw new Exception();
        }
    }
}
