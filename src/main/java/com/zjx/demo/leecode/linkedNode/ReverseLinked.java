package com.zjx.demo.leecode.linkedNode;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.leecode 反转链表
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * @date:2020/8/4
 **/
public class ReverseLinked {

    public static void main(String[] args) {

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = null;

//        ListNode reverseList1 = reverseList1(node1);
////        System.out.println(reverseList1.val);

        ListNode reverseList2 = reverseList2(node1);
        System.out.println(reverseList2.val);

    }

    /*
    递归方法
     * */
    public static ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode newNode = reverseList1(head.next);
        head.next.next = head; //反转
        head.next = null;
        return newNode;
    }


    /*
     * 遍历链表移动
     * */
    public static ListNode reverseList2(ListNode head) {
        ListNode pre =null;
        ListNode curr = head;
        while (curr != null){
            ListNode next = curr.next;
            //反转链表
            curr.next = pre;
            //三人行
            pre = curr;
            curr = next;
        }
        return pre;
    }
}


class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
