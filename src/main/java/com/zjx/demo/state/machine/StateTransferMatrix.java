package com.zjx.demo.state.machine;

import com.zjx.demo.state.machine.State;

import java.util.Vector;

/**
 * @author Houfeng Luo
 * @since 1.0.0
 */
public class StateTransferMatrix {
    private Vector<State> states;
    private int[][] matrix;

    public StateTransferMatrix(Vector<State> states, int[][] matrix) {
        if(states.size() != matrix.length) {
            throw new RuntimeException("....");
        }
        if(states.size() != matrix[0].length) {
            throw new RuntimeException("。。。。");
        }
        this.states = states;
        this.matrix = matrix;
    }

    public boolean canTransfer(State preState, State nextState) {
        /**
         * Finds out the preIndex and the nextIndex
         */
        int preIndex = -1;
        int nextIndex = -1;
        for (int i = 0; i < states.size(); i++) {
            String value = states.get(i).getValue();
            if(preState.getValue().equals(value)) {
                preIndex = i;
            }
            if(nextState.getValue().equals(value)) {
                nextIndex = i;
            }
        }

        /**
         * Then, check the transfer relationship of the two states weather in the current
         */
        return matrix[preIndex][nextIndex] == 1;
    }

}
