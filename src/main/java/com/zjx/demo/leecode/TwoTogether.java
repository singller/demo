package com.zjx.demo.leecode;

import java.util.LinkedList;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.leecode
 *
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 **/
public class TwoTogether {

    public static void main(String[] args) {
        int i =13;
        int j = i / 10;
        int o = i % 10;
        System.out.println(j);
        System.out.println(o);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        //
        ListNode node = new ListNode(0);
        ListNode p = l1,q = l2, curr =node;
        int carry = 0;
        //两个链表都不为空
        while (p!= null || q!=null){
            int i = (p != null) ? p.val : 0;
            int j = (q != null) ? q.val : 0;
            int sum = carry + i+j;
            carry = sum /10;
            curr.next = new ListNode(sum %10);
            curr = curr.next;

            if(p!=null) p=p.next;
            if(q!=null) q=q.next;
        }
        if(carry >0){
            curr.next = new ListNode(carry);
        }
        return node.next;
    }


    public ListNode getSumLindNode(ListNode l1,ListNode l2){

        //定义接受最终的node
        ListNode node = new ListNode(0);
        //定义接收l1 l2的临时变量 跟总变量
        ListNode p =l1,q = l2 ,curr = node;
        //定义接收加法的多进位值
        int carry = 0;
        //遍历判断 p q 两个链表
        while (p!=null || q!=null){
            int i = p != null ? p.val : 0;
            int j = q != null ? q.val : 0;
            int sum = i + j +carry;
            carry = sum/10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if(p!= null)p = p.next;
            if(q!= null)q = q.next;
        }
        if(carry>0){
            curr.next = new ListNode(carry);
        }
        return node.next;
    }




}
