package com.zjx.demo.designMode.responsibility;

/**
 * @author 65454
 */
public abstract class AbstractDataHandler<T> {

    /**
     *  处理请求
     * @param query
     * @return T
     */
    protected abstract T doRequest(String query);
}
