package com.zjx.demo.leecode.linkedNode;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.leecode.linkedNode   双指针的题
 * @date:2020/10/21
 **/
public class LinkedCycle {


    //判断链表是否有环   快慢指针一起从 head 出发，每次快指针走 2 步，慢指针只走 1 步，如果存在环，那么两个指针一定会相遇。
    public boolean hasCycle(ListNode head){

        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null){
            fast = head.next.next;
            slow = head.next;
            if(fast == slow){
                return true;
            }
        }
        return false;
    }

    //p返回有环链表的环的起点
    public ListNode hasCycle1(ListNode head){

        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null){
            fast = head.next.next;
            slow = head.next;
            if(fast == slow){
                ListNode x = head;
                ListNode y = slow;
                while (x != y){
                    x=x.next;
                    y=y.next;
                }
                return x;
            }
        }
        return null;
    }


}
