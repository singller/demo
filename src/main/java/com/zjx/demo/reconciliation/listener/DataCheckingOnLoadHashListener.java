package com.zjx.demo.reconciliation.listener;

import com.zjx.demo.reconciliation.BasicCheckData;
import com.zjx.demo.reconciliation.TargetCheckData;

import java.util.Map;

/**
 * @author zjx
 */
public interface DataCheckingOnLoadHashListener<K> {


    /**
     * @return
     */
    Map<K, BasicCheckData> loadBasicData2Map();

    Map<K, TargetCheckData> loadTargeDataMap();
}
