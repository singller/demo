package com.zjx.demo.state.machine.state;

import com.zjx.demo.state.machine.State;

/**
 * @author zjx
 */

public enum OrderState implements State {
    UN_COMMIT("未提交"),
    TO_AUDIT("待审核"),
    PASSED("审核通过"),
    REJECTED("审核不通过"),
    FALLBACK("已打回");

    private String label;

    OrderState(String label) {
        this.label = label;
    }

    @Override
    public String getValue() {
        return null;
    }
}
