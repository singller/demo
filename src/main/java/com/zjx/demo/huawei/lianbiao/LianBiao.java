package com.zjx.demo.huawei.lianbiao;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author zjx
 */
public class LianBiao {

    /**
     * HJ48 从单向链表中删除指定值的节点
     * @param args
     */
    public static void solution48(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int total = sc.nextInt();
            int head = sc.nextInt();

            List<Integer> linkedlist = new ArrayList<>();

            linkedlist.add(head);
            for (int i = 0; i < total - 1; i ++) {
                int value = sc.nextInt();
                int target = sc.nextInt();
                linkedlist.add(linkedlist.indexOf(target) + 1, value);
            }

            int remove = sc.nextInt();
            linkedlist.remove(linkedlist.indexOf(remove));
            for (int i : linkedlist) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    /**
     * HJ51 输出单向链表中倒数第k个结点
     * @param args
     */
    public static void solutio51(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            int num = scan.nextInt();
            ListNode header = new ListNode();
            for (int i=0; i< num;i++) {
                int value = scan.nextInt();
                ListNode node = new ListNode(value, header.next);
                header.next = node;
            }
            int target = scan.nextInt();
            for (int i=0; i<target; i++) {
                header = header.next;
            }
            System.out.println(header.value);
        }
    }
    static class ListNode{
        int value;
        ListNode next;
        public ListNode(){

        }
        public ListNode(int value, ListNode next){
            this.value = value;
            this.next = next;
        }
    }
}


