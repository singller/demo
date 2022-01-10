package com.zjx.demo.easyExcel;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.lang.Nullable;

/**
 * @author catalina
 * @date 2020/8/18
 */
public interface MessageResource {

    @Nullable
    String getMessage(String code, @Nullable Object[] args, @Nullable String defaultMessage);

    String getMessage(String code, @Nullable Object[] args) throws NoSuchMessageException;

    String getMessage(MessageSourceResolvable resolvable) throws NoSuchMessageException;

}
