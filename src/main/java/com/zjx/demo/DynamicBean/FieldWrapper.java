package com.zjx.demo.DynamicBean;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;

/**
 * @author zjx
 */
@Getter
@Setter
public class FieldWrapper implements Comparable<FieldWrapper> {

    private Field field;

    private String order;

    @Override
    public int compareTo(FieldWrapper o) {
        return getOrder().compareTo(o.getOrder());
    }

}
