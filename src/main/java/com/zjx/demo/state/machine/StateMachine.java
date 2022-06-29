package com.zjx.demo.state.machine;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Houfeng Luo
 * @since 1.0.0
 */
public class StateMachine {

    private Map<String, Function> handleMappings = new HashMap<>();
    private StateTransferMatrix stateTransferMatrix = null;

    public StateMachine(StateTransferMatrix stateTransferMatrix) {
        this.stateTransferMatrix = stateTransferMatrix;
    }

    public <T, R> void registerStateTransferHandler(StateTransferHandler<T, R> stateTransferHandler) {
        handleMappings.put(stateTransferHandler.getKey(), stateTransferHandler.getHandler());
    }

    public <T, R> R start(T inputParameter, State preState,State nextState) {
        
        Function<T,R> function = handleMappings.get(preState + "-" + nextState);
        boolean canTransfer = stateTransferMatrix.canTransfer(preState, nextState);
        if (!canTransfer) {
            throw new RuntimeException(String.format("can't transfer from %s to %s", preState, nextState));
        }
        return function.apply(inputParameter);
    }

}
