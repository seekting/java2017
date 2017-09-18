package com;

/**
 * Created by Administrator on 2017/8/31.
 */

public class TestLinked {

    private static class Node {
        //        Node pre;
        Node next;

        public Node(int value) {
            this.value = value;
        }

        int value;
    }

    public static void main(String args[]) {
        Node head = new Node(0);

        Node node1 = new Node(1);
        head.next = node1;

        Node node2 = new Node(2);
        node1.next = node2;


        Node node3 = new Node(3);
        node2.next = node3;

        Node node4 = new Node(4);
        node3.next = node4;


        Node node5 = new Node(5);
        node4.next = node5;
        node4.next = node2;
//        boolean exit = exitHuan(head);
//        System.out.println("exit=" + exit);


    }

    public static class NodeResult {

        Node firstHit = null;
        boolean hasHuan = false;


    }

    public static NodeResult exitHuan(Node head) {
        NodeResult nodeResult = new NodeResult();

        Node firstHit = findHit(head);
        if (firstHit != null) {
            nodeResult.hasHuan = true;
            nodeResult.firstHit = firstHit;

        }


        return nodeResult;
    }

    private static Node findHit(Node begin) {
        Node current = begin;
        Node current1 = begin;
        while (true) {
            current = current.next;
            Node temp = current1.next;
            if (temp == null) {
                return null;
            } else {
                Node nNext = temp.next;
                if (nNext == null) {
                    return null;
                } else {
                    current1 = nNext;
                    if (current1 == current) {
                        return current;
                    }
                }
            }

        }
    }
}
