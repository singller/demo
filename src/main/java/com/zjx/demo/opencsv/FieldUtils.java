package com.zjx.demo.opencsv;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lingzhen on 17/9/24.
 */
public class FieldUtils {

    private static Map<Class, Map<String, Field>> beanFieldIgnoreCaseCache = new ConcurrentHashMap<>();

    public static Field[] getAllFields(Class<?> clazz) {
        return org.apache.commons.lang3.reflect.FieldUtils.getAllFields(clazz);
    }

    public static Field getDeclaredField(Class<?> clazz, String fieldName) {
        return org.apache.commons.lang3.reflect.FieldUtils.getDeclaredField(clazz, fieldName);
    }

    public static Field getDeclaredFieldIgnoreCase(Class<?> clazz, String fieldName) {
        if (!beanFieldIgnoreCaseCache.containsKey(clazz)) {
            // 已经缓存了则取缓存中的数据
            fetchBeanFieldIgnoreCase(clazz);
        }
        return beanFieldIgnoreCaseCache.get(clazz).get(fieldName.toLowerCase());
    }

    public static void fetchBeanFieldIgnoreCase(Class<?> clazz) {
        Field[] fileds = getAllFields(clazz);
        Map<String, Field> fieldMap = new HashMap<>();
        for (Field field : fileds) {
            fieldMap.put(field.getName().toLowerCase(), field);
        }
        beanFieldIgnoreCaseCache.put(clazz, fieldMap);
    }
}
