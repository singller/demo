package com.zjx.demo.state.machine;

import java.util.function.Function;

/**
 * @author Houfeng Luo
 * @since 1.0.0
 */
public interface StateTransferHandler<T, R> {

    String getKey();

    State getPreState();

    State getNextState();

    Function<T, R> getHandler();
}
