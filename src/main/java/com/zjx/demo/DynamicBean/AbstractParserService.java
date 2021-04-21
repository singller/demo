package com.zjx.demo.DynamicBean;


import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.RandomAccessFile;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.*;

/**
 * 对账文件下载
 *
 * @author zjx
 * @date 2020/11/16
 */
@Slf4j
public class AbstractParserService {

    private static final int DOWNLOAD_TRY_TIMES = 3;// 下载尝试次数
    protected String firstLineFlag = "H";
    protected String currency = "RMB";

    protected String fileEndFlag = "T";
    protected String fileEndFlagByPayout = "F";
    protected String statusReject = "RJCT";


    public static final String LOCAL_PATH = "/tmp/reconciliation/";


    private void prcessAnnotation(String str) {
        Map mapType = JSON.parseObject(str, Map.class);

        mapType.forEach((k, v) -> {
            //获取Bar的val字段
            Field field = null;
            try {
                field = StandardBody.class.getDeclaredField(k.toString());
                //获取val字段上的Foo注解实例
                Order foo = field.getAnnotation(Order.class);
                //获取Foo注解实例的 value 属性值
                String value = foo.value();
                //打印该值
                System.out.println("k:" + k.toString() + "   v1:" + value); // ddd

                //获取 foo 这个代理实例所持有的 InvocationHandler
                InvocationHandler h = Proxy.getInvocationHandler(foo);
                // 获取 AnnotationInvocationHandler 的 memberValues 字段
                Field hField = h.getClass().getDeclaredField("memberValues");
                ;
                // 因为这个字段事 private final 修饰，所以要打开权限
                hField.setAccessible(true);
                // 获取 memberValues
                Map memberValues = (Map) hField.get(h);
                // 修改 value 属性值
                memberValues.put("value", v.toString());
                // 获取 foo 的 value 属性值
                String value2 = foo.value();
                System.out.println("k:" + k.toString() + "    v2:" + value2); // ddd
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args) throws Exception {
//        String str = "{\n" +
//                "    \"transactionNo\":\"5\",\n" +
//                "    \"tranSuccDate\":\"2\",\n" +
//                "    \"tranSuccTime\":\"3\",\n" +
//                "    \"orderAmount\":\"11\",\n" +
//                "    \"fiTransactionNo\":\"6\"\n" +
//                "}";
//
//        AbstractParserService service = new AbstractParserService();
//        service.prcessAnnotation(str);
//        String localPath = "C:\\Users\\65454\\Desktop\\fsdownload\\SCB_Bill_Payment.csv";
//        service.processData(localPath);

        String str = "";
        String s = Optional.ofNullable(str).map(sw -> "2").orElse("1");
        System.out.println(s);

    }

    /**
     * 对账文件正文读取入库
     *
     * @param localFilePath 文件在本地服务器所在路径
     */
    private void processData(String localFilePath) throws Exception {
        List channelFlowList = new ArrayList<>();
        RandomAccessFile raf = new RandomAccessFile(localFilePath, "r");
        String line = raf.readLine();
        // 读取报文消息体
        while ((line = raf.readLine()) != null) {
            // 读取到文件末尾
            try {
                // 拆分数据
                line = new String(line.getBytes("ISO-8859-1"), "utf-8");
                //分割双引号里的逗号不分割
                String[] data = line.split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)", -1);
                StandardBody channelFlow = innerProcess(data);
                if (Objects.nonNull(channelFlow)) {
                    channelFlowList.add(channelFlow);
                }
            } catch (Exception e) {
            }
        }
        System.out.println(Arrays.asList(channelFlowList));
    }


    public boolean deleteServerFile(String filePath) {
        boolean delete_flag = false;
        File file = new File(filePath);
        if (file.exists() && file.isFile() && file.delete()) {
            delete_flag = true;
        }
        return delete_flag;
    }

    /**
     * 单条数据处理
     *
     * @return
     */
    protected StandardBody innerProcess(String[] data) {
        StandardBody body = createCheckBean(data, StandardBody.class);
        return body;
    }

    /**
     * 对账文件单条消息体封装
     */
    protected <T> T createCheckBean(String[] data, Class<T> beanClass) {
        try {
            List<Field> fields = getFields(beanClass);
            List<FieldWrapper> wrapperList = Lists.newArrayList();
            for (int i = 0; i < fields.size(); i++) {
                Field field = fields.get(i);
                Order order = field.getAnnotation(Order.class);
                if (order != null && StringUtils.isNotBlank(order.value())) {
                    FieldWrapper wrapper = new FieldWrapper();
                    wrapper.setField(field);
                    wrapper.setOrder(order.value());
                    wrapperList.add(wrapper);
                }
            }
            Collections.sort(wrapperList);
            T bean = beanClass.newInstance();
            for (FieldWrapper wrapper : wrapperList) {
                if (Integer.valueOf(wrapper.getOrder()) >= data.length) {
                    continue;
                }
                String value = data[Integer.valueOf(wrapper.getOrder())];
                Field field = wrapper.getField();
                field.setAccessible(true);
                field.set(bean, value);
            }
            return bean;
        } catch (Exception e) {
        }
        return null;
    }

    private List<Field> getFields(Class beanClass) {
        List<Field> fieldList = Lists.newArrayList();
        while (!beanClass.equals(Object.class)) {
            fieldList.addAll(Lists.newArrayList(beanClass.getDeclaredFields()));
            beanClass = beanClass.getSuperclass();
        }
        return fieldList;
    }


}
