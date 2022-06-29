package com.zjx.demo.reconciliation.listener;

import com.zjx.demo.reconciliation.BasicCheckData;
import com.zjx.demo.reconciliation.TargetCheckData;

/**
 * @author zjx
 */
public interface DataCheckingConsistenceListener<T> {

    /**
     * 是否一致
     * @param basicCheckEntity
     * @param targetCheckEntity
     * @return
     */
    boolean isCheckConsistent(BasicCheckData basicCheckEntity, TargetCheckData targetCheckEntity);

    /**
     * 数据修复
     */
    void fixData();

    /**
     * 是否需要二次对账
     * @return
     */
    boolean needDoubleCheck();
}
