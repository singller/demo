package com.zjx.demo.DynamicBean;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zjx
 */
@Retention(RetentionPolicy.SOURCE)
@Target(value = ElementType.FIELD)
public @interface FieldDesc {

    String value();

}
