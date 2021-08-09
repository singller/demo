package com.zjx.demo.processEngine;

import cn.hutool.core.thread.NamedThreadFactory;
import com.google.common.collect.Maps;
import com.zjx.demo.processEngine.service.FlowNodeInterface;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author 65454
 */
@Service
public class FlowEngine {

    /**
     * 引擎执行入口
     * @param flowNode
     * @param runData
     * @param context
     * @throws Exception
     */
    public void execute(FlowNode flowNode, RunData runData, ProcessEngineContext context) throws Exception {
        Map<String, List<String>> nodeGroup = groupByGroupName(flowNode);
        Map<String, FlowNode.NodeConf> nodeMap = flowNode.getNodeMap();
        for (String groupName : nodeGroup.keySet()) {
            boolean needThrowExp = false;
            List<String> nodeNameList = nodeGroup.get(groupName);
            //只有一个Node的节点，串行执行
            if (nodeNameList.size() == 1) {
                String nodeName = nodeNameList.get(0);
                FlowNodeInterface detailNode = (FlowNodeInterface)
                        BeanService.getSingleBeanByType(Class.forName(nodeName));
                NodeExecuteTask nodeExecuteTask = new NodeExecuteTask(detailNode, runData, context);

                try {
                    Object result = nodeExecuteTask.excute();
                    context.getResultMap().put(detailNode.resultKey(), result);
                } catch (Exception e) {
                    needThrowExp = true;
                }
            } else {
                //多个节点，并行执行
                //执行结果list
                ArrayList<Future> resultList = Lists.newArrayList();
                //执行的node节点
                ArrayList<String> executedNodeNameList = Lists.newArrayList();
                //执行的node节点任务
                ArrayList<NodeExecuteTask> executeNodeList = Lists.newArrayList();
                //遍历node
                for (String nodeName : nodeNameList) {
                    //通过流程name获取流程执行类
                    FlowNodeInterface detailNode = (FlowNodeInterface) BeanService.getSingleBeanByType(Class.forName(nodeName));
                    //构造流程执行任务
                    NodeExecuteTask nodeParallelTask = new NodeExecuteTask(detailNode, runData, context);
                    //执行流程添加
                    executeNodeList.add(nodeParallelTask);
                    executedNodeNameList.add(nodeName);
                    resultList.add(threadPool.submit(nodeParallelTask));
                }

                //遍历执行结果list
                for (int i = 0; i < resultList.size(); i++) {
                    //获取执行
                    String nodeName = executedNodeNameList.get(i);
                    String nodeKey = groupName + "_" + nodeName;
                    FlowNodeInterface detailNode = (FlowNodeInterface) BeanService.getSingleBeanByType(Class.forName(nodeName));
                    FlowNode.NodeConf nodeConf = nodeMap.get(nodeKey);
                    int timeout = nodeConf.getTimeout();
                    Future future = resultList.get(i);
                    try {
                        Object o = future.get(timeout, TimeUnit.MILLISECONDS);
                        //保存任务执行结果
                        context.getResultMap().put(detailNode.resultKey(), o);
                    } catch (ExecutionException e) {
                        needThrowExp = true;
                    } catch (TimeoutException o) {
                        needThrowExp = true;
                    } catch (Exception e) {
                        needThrowExp = true;
                    }
                }
                if (needThrowExp) {
                    throw new RuntimeException();
                }
            }
        }
    }

    /**
     * 流程中的参数
     */
    @Setter
    @Getter
    public static class RunData {
        private String paramOne;

        private String paramTwo;
    }

    private Map<String, List<String>> groupByGroupName(FlowNode flowNode) {
        LinkedHashMap<String, List<String>> nodeGroup = Maps.newLinkedHashMap();

        for (String nodeKey : flowNode.getNodeList()) {
            String groupName = getGroupName(nodeKey);
            String nodeName = getNodeName(nodeKey);

            if (StringUtils.isBlank(groupName)) {
                ArrayList<String> nodeNameList = Lists.newArrayList();
                nodeNameList.add(nodeName);
                nodeGroup.put(groupName, nodeNameList);
            } else {
                List<String> nodeNameList = nodeGroup.get(groupName);
                if (null == nodeNameList) {
                    nodeNameList = Lists.newArrayList();
                }
                nodeNameList.add(nodeName);
                nodeGroup.put(groupName, nodeNameList);
            }
        }
        return nodeGroup;
    }

    private String getGroupName(String nodeKey) {
        String[] arr = nodeKey.split("_");
        return arr.length == 2 ? arr[0] : null;
    }

    private String getNodeName(String nodeKey) {
        String[] arr = nodeKey.split("_");
        return arr.length == 2 ? arr[1] : null;
    }

    public static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 10, 60L, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>(500), new
            NamedThreadFactory("engine processor", false), new ThreadPoolExecutor.AbortPolicy() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            throw new RejectedExecutionException("Task" + r.toString() + "rejected from" + executor.toString());
        }
    });
}
