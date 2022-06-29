package com.zjx.demo.state.machine;

import java.util.function.Function;

/**
 * @author zjx
 */
public abstract class AbstractStateTransferHandler<T, R> implements StateTransferHandler<T, R> {

    private State preState;
    private State nextState;
    private Function<T, R> handler;

    public AbstractStateTransferHandler(State preState, State nextState) {
        this.preState = preState;
        this.nextState = nextState;
    }

    @Override
    public String getKey() {
        return preState + "-" + nextState;
    }

    @Override
    public State getPreState() {
        return preState;
    }

    @Override
    public State getNextState() {
        return nextState;
    }

    @Override
    public Function<T, R> getHandler() {
        return buildHandler();
    }

    protected abstract Function<T, R> buildHandler();

}
