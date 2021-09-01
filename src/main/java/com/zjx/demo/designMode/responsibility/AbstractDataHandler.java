package com.zjx.demo.designMode.responsibility;

/**
 * @author 65454
 */
public abstract class AbstractDataHandler<T> {

    protected abstract T doRequest(String query);
}
