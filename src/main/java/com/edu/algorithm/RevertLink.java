package com.edu.algorithm;

/**
 * 给出一个链表，每 k 个节点一组进行翻转，并返回翻转后的链表。

 k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么将最后剩余节点保持原有顺序。

 示例 :

 给定这个链表：1->2->3->4->5

 当 k = 2 时，应当返回: 2->1->4->3->5

 当 k = 3 时，应当返回: 3->2->1->4->5

 说明 :

 你的算法只能使用常数的额外空间。
 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * Created by zhangxuan on 2019/4/4.
 */
public class RevertLink {
    static class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            val = x;
        }
        ListNode(int x , ListNode next){
            this.val = x;
            this.next = next;
        }
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode p = head;
        ListNode n = head;
        ListNode next;
        ListNode preNext;
        int i = 1;
        while(i < k && p != null){
            p = p.next;
            i++;
        }
        if (p == null) return head;
        next = reverseKGroup(p.next,k);
        while(n != p){
            preNext = n.next;
            n.next = next;
            next = n;
            n = preNext;
        }
        p.next = next;
        return p;
    }


    public static void main(String[] args) {
        ListNode n = new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(4,new ListNode(5,null)))));

        ListNode listNode = reverseKGroup(n, 2);
        System.out.println(listNode);
    }
}
