package com.zjx.demo.lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.lru
 * @date:2020/8/13
 **/
public class LRUCache<K,V> extends LinkedHashMap<K,V> {

    private final int CACHE_SIZE;

    public LRUCache(int cacheSize){
        super((int)Math.ceil(cacheSize/0.75) +1,0.75f,true);
        CACHE_SIZE = cacheSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size()>CACHE_SIZE;
    }


}
