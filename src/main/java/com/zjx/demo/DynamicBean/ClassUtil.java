package com.zjx.demo.DynamicBean;


import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import lombok.Getter;
import lombok.Setter;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class ClassUtil {

    /**
     *
     * @param object   旧的对象带值
     * @param addMap   动态需要添加的属性和属性类型
     * @param addValMap  动态需要添加的属性和属性值
     * @return  新的对象
     * @throws Exception
     */
    public Object dynamicClass(Object object,HashMap addMap, HashMap addValMap) throws Exception {
        HashMap returnMap = new HashMap();
        HashMap typeMap = new HashMap();


        Class<?> type = object.getClass();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(object);
                //可以判断为 NULL不赋值
                returnMap.put(propertyName, result);
                typeMap.put(propertyName, descriptor.getPropertyType());
            }
        }

        returnMap.putAll(addValMap);
        typeMap.putAll(addMap);
        //map转换成实体对象
        DynamicBean bean = new DynamicBean(typeMap);
        //赋值
        Set keys = typeMap.keySet();
        for (Iterator it = keys.iterator(); it.hasNext(); ) {
            String key = (String) it.next();
            bean.setValue(key, returnMap.get(key));
        }
        Object obj = bean.getObject();
        return obj;
    }

    @Setter
    @Getter
    public static class Order{
        private int id;
        private String name;
    }

    @Setter
    @Getter
    public static class OrderDetail{
        private int orderid;
        private String orderPrice;
        private String orderSku;

    }

    public static void main(String[] args) {
        Order order = new Order();
        order.setId(1);
        order.setName("order1");
        List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderid(1);
        orderDetail.setOrderPrice("1USD");
        orderDetail.setOrderSku("Sku1");

        orderDetailList.add(orderDetail);

        OrderDetail orderDetail2 = new OrderDetail();
        orderDetail2.setOrderid(1);
        orderDetail2.setOrderPrice("2USD");
        orderDetail2.setOrderSku("Sku2");
        orderDetailList.add(orderDetail2);

        try {
            HashMap addMap = new HashMap();
            HashMap addValMap = new HashMap();
            addMap.put("orderDetail", Class.forName("java.util.List"));
//            addMap.put("orders",Class.forName("java.lang.String"));
            addValMap.put("orderDetail", orderDetailList);

            Object obj2= new ClassUtil().dynamicClass(order,addMap,addValMap);

            System.out.println(obj2);

            Class<?> aClass = obj2.getClass();
            Object o = aClass.newInstance();
            Field orders = aClass.getField("$cglib_prop_name");
            Object o1 = orders.get(o);
            System.out.println(o1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}