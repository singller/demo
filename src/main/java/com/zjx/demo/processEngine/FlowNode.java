package com.zjx.demo.processEngine;

import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author 65454
 */
public class FlowNode {

    private Map<String, NodeConf> nodeMap = Maps.newLinkedHashMap();


    public void add(String groupName, Class nodeName, NodeConf nodeConf) {
        String key = null;
        if (StringUtils.isNotBlank(groupName)) {
            key = groupName + "_" + nodeName.getName();
        } else {
            key = nodeName.getName();
        }

        if (nodeMap.containsKey(key)) {
            return;
        }
        nodeMap.put(key, nodeConf);
    }

    public void add(Class nodeName, NodeConf nodeConf) {
        add(nodeName.getName(), nodeName, nodeConf);
    }

    public void replace(String groupName, Class nodeName, NodeConf nodeConf) {
        String key = null;
        if (StringUtils.isNotBlank(groupName)) {
            key = groupName + "_" + nodeName.getName();
        } else {
            key = nodeName.getName();
        }
        nodeMap.put(key, nodeConf);
    }

    public void replace(Class nodeName, NodeConf nodeConf) {
        replace(nodeName.getName(), nodeName, nodeConf);
    }

    public void remove(String groupName, Class nodeName) {
        String key = null;
        if (StringUtils.isNotBlank(groupName)) {
            key = groupName + "_" + nodeName.getName();
        } else {
            key = nodeName.getName();
        }
        nodeMap.remove(key);
    }

    public void remove(Class nodeName) {
        remove(nodeName.getName(), nodeName);
    }

    public Set<String> getNodeList() {
        return nodeMap.keySet();
    }

    public Map<String, NodeConf> getNodeMap() {
        return nodeMap;
    }

    public void setMap(LinkedHashMap<String, NodeConf> map) {
        this.nodeMap = map;
    }

    @NoArgsConstructor
    @Getter
    @Setter
    public static class NodeConf {

        private int timeout = 100;

    }
}


