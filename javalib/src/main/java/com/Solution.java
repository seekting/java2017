package com;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/9/16.
 */

public class Solution {

    public static void main(String args[]) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        listNode1.next = listNode2;
        listNode2.next = listNode3;

//        ArrayList<Integer> k = new Solution().printListFromTailToHead(listNode1);
//        new Solution().print(listNode1);

        ListNode head = revert( listNode3);
        while (head != null) {
            System.out.print(head.val + "->");
            head = head.next;
        }


    }

    public static ListNode revert(ListNode head) {

        ListNode pre = null;
        ListNode current = head;
        while (current != null) {
            ListNode next = current.next;
            current.next = pre;
            pre = current;
            current = next;
        }
        return pre;
    }

    public static ListNode revert(ListNode pre, ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode next = head.next;
        head.next = pre;
        if (next == null) {
            return head;
        }

        return revert(head, next);

    }

    public void print(ListNode listNode) {
        if (listNode == null) {
            return;
        }
        ListNode next = listNode.next;
        print(next);
        System.out.println(listNode.val);
    }

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> result = new ArrayList<>();
        if (listNode == null) {
            return result;
        }
        while (listNode != null) {
            result.add(0, listNode.val);
            listNode = listNode.next;
        }

        return result;

    }

    public ArrayList<Integer> printListFromTailToHead1(ListNode listNode) {
        ArrayList<Integer> result = new ArrayList<>();
        if (listNode == null) {
            return result;
        }
        if (listNode.next == null) {
            result.add(listNode.val);
            return result;
        }
        add(result, listNode);
        return result;

    }

    public void add(ArrayList<Integer> list, ListNode listNode) {
        if (listNode == null) {
            return;
        }
        ListNode next = listNode.next;

        add(list, next);
        list.add(listNode.val);

    }

    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
}


