package com.zjx.demo.leecode.linkedNode;

/**
 * @author: create by zhangjianxun
 * @version: v1.0
 * @description: com.zjx.demo.leecode    虚拟节点
 * <p>
 * 两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 * @date:2020/8/6
 **/
public class MerageOrderLinked {

    /*
     * 递归算法
     * 时间O(m+n)
     * 空间(m+n)
     * */
    public ListNode1 mergeTwoLists(ListNode1 l1, ListNode1 l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }
        //如果l1小于l2
        if (l1.val < l2.val) {
            //l1得下一个节点指向重新判断得  l2跟l1.next  结果
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l2.next, l1);
            return l2;
        }
    }


    /*
     * 迭代算法
     * 时间O(m+n)
     * 空间(1)
     * */
    public ListNode1 mergeTwoLists1(ListNode1 l1, ListNode1 l2) {
        //初始哨兵节点
        ListNode1 preHead = new ListNode1(-1);

        ListNode1 pre = preHead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                pre.next = l1;
                l1 = l1.next;
            } else {
                pre.next = l2;
                l2 = l2.next;
            }
            pre = pre.next;
        }
        //判断剩下的不为空的节点
        pre.next = l1 == null ? l2 : l1;
        return preHead.next;
    }
}


class ListNode1 {
    int val;
    ListNode1 next;

    ListNode1() {
    }

    ListNode1(int val) {
        this.val = val;
    }

    ListNode1(int val, ListNode1 next) {
        this.val = val;
        this.next = next;
    }
}
