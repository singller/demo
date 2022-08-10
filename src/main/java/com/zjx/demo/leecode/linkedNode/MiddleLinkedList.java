package com.zjx.demo.leecode.linkedNode;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.leecode.linkedNode   这题就是给一个链表，然后找到它的中点，如果是奇数个很好办，如果是偶数个，题目要求返回第二个。
 * 比如：
 * 1 -> 2 -> 3 -> 4 -> 5 -> NULL，需要返回 3 这个 ListNode；
 * 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> NULL，需要返回 4 这个 ListNode。
 * @date:2020/10/21
 **/
public class MiddleLinkedList {

    /**
     * @Description    先遍历获取链表长度  再取中点
     * @Param * @param head
     * @Return com.zjx.demo.leecode.linkedNode.ListNode
     * @Author zhangjianxun
     * @Date 2020/10/21
     **/
    public ListNode middleNode(ListNode head) {

        if (null == head) {
            return null;
        }

        int len = 0;
        ListNode curr = head;
        while (null != curr) {
            len++;
            curr = curr.next;
        }

        len /= 2;
        ListNode result = head;
        while (len > 0) {
            result = result.next;
            len--;
        }
        return result;
    }

    //双指针法   快指针每次跳跃两个  慢指针每次跳跃一个
    public ListNode middleNode2(ListNode head) {
        if(head ==null) return null;
        ListNode fast = head;
        ListNode slow = head;
        while (fast!=null && fast.next!= null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
