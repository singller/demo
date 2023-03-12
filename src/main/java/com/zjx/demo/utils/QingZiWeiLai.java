package com.zjx.demo.utils;

import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author zjx
 */
public class QingZiWeiLai {


    public static Node[] sout(Node[] root) {
        Set<Integer> set = new HashSet<>();
        for (Node no : root) {
            Node temp = no.next;
            while (null != temp) {
                if (no.equals(temp)) {
                    set.add(temp.getIndex());
                    break;
                }
                if (temp.getIndex() != no.getIndex()) {
                    set.add(temp.getIndex());
                }
                temp = temp.next;
            }
        }
        for (int i : set) {
            int index = i - 1;
            root[index] = null;
        }
        return root;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    static class Node {
        public int index;
        public Node next;
    }

//    public static void main(String[] args) {
//
//
////        Node node1 = new Node();
////        node1.setIndex(1);
////        Node node11 = new Node();
////        Node node111 = new Node();
////        node111.setIndex(1);
////        node11.setIndex(1);
////        node11.setNext(node111);
////        node1.setNext(node11);
////
////        Node node2 = new Node();
////        Node node22 = new Node();
////        Node node222 = new Node();
////        node2.setIndex(2);
////        node22.setIndex(2);
////        node222.setIndex(2);
////        node22.setNext(node222);
////        node2.setNext(node22);
////
////        Node node3 = new Node();
////        Node node33 = new Node();
////        node3.setIndex(3);
////        node33.setIndex(3);
////        node3.setNext(node33);
////
////        Node node4 = new Node();
////        Node node44 = new Node();
////        node4.setIndex(4);
////        node44.setIndex(4);
////        node44.setNext(node222);
////        node4.setNext(node44);
////
////        Node node5 = new Node();
////        node5.setIndex(5);
////        node5.setNext(node44);
////
////        Node[] root = {node1,node2,node3,node4,node5};
////        Node[] sout = QingZiWeiLai.sout(root);
//////        System.out.println(sout);sout
////        int[] nums = {1, 3, 3, 3, 4, 5, 6, 7, 8, 8, 8, 8, 8, 9, 10, 11, 11, 12, 23, 33, 33, 10, 2, 2, 2, 2, 2, 2, 1, 1, 1};
////        Integer[] arr1 = {1, 1, 3, 3, 5, 6, 7, 7, 7, 8, 10, 15, 20, 22, 23, 23, 45, 56, 80, 80, 45, 45, 43, 42, 32, 32, 14, 11, 6, 3, 3};
////        Integer[] arr2 = {1, 2, 2, 3, 3, 4, 5, 5, 9, 10, 23, 23, 23, 23, 23, 78, 78, 78, 2, 2, 2, 2, 2, 1, 1, 1, 1};
////        Integer[] arr3 = {8, 8, 8, 8, 8, 8, 8};
////        Integer[] arr4 = {1, 3, 3, 3, 4, 5, 5, 5, 5, 5, 6, 7, 8, 8, 8, 8, 8};
////        int[] arr5 = {9, 9, 9, 9, 9, 6, 6, 6, 2, 2, 2, 1};
////        solutionTwo(arr5);
//    }


    public static void findMax(int[] nums) {
        int idx = 0;
        HashMap<Integer, Integer> map = Maps.newHashMap();
        for(int i=1;i<nums.length;i++){
            if(nums[i]>nums[idx]){
                idx = i;
                map.put(nums[i],1);
            }else if(nums[i]== nums[idx]){
                idx = i;
                if(map.containsKey(nums[i])){
                    map.put(nums[i],map.get(nums[i])+1);
                }
            }else {
                break;
            }
        }
        System.out.println(nums[idx]+"::"+map.get(nums[idx]));
    }


    public static void solutionTwo(int[] nums){

        int left = 0;
        int right = nums.length-1;
        HashMap <Integer,Integer> map = new HashMap<Integer,Integer>();
        int max =0;
        while(left<right){

        }


    }


    private static Node[] initNode2(){

        Node[] array = new Node[5];

        Node node0 = new Node();
        Node node1 = new Node();
        Node node2 = new Node();
        Node node3 = new Node();
        Node node4 = new Node();
        node0.index=0;
        node0.next=node1;
        array[0]= node0;

        node1.index=1;
        node1.next=node1;
        array[1]= node1;

        node2.index=(2);
        node2.next=node2;
        array[2]= node2;

        node3.index=(3);
        node3.next=node1;
        array[3]= node3;

        node4.index=4;
        node4.next=node3;
        array[4]= node4;

        return array;
    }

    public static Node[] solution(Node[] root){

        Set<Integer> set = new HashSet<>();
        for(Node node:root){
            //获取next节点
            Node temp = node.next;
            while(null != temp){
                //如果temp节点等于自己
                if(node.equals(temp)){
                    break;
                }
                if(temp.getIndex() != node.getIndex()){
                    set.add(temp.getIndex());
                    break;
                }
                temp = temp.next;
            }
        }
        for(int i:set){
            int index = i;
            root[index] = null;
        }
        return root;
    }

    public static void main(String[] args) {
        Node[] nodes = initNode2();
        Node[] solution = solution(nodes);
        System.out.println();
    }

}
