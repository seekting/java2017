package com;

/**
 * Created by Administrator on 2017/9/2.
 */

public class Node {
    Node next;

    public Node(Node next, int value) {
        this.next = next;
        this.value = value;
    }

    public Node(int value) {
        this.value = value;
    }

    int value;

    public Node(Node next) {
        this.next = next;
    }

    public static void print(Node node) {
        System.out.print("[");
        Node current = node;
        while (true) {
            System.out.print(current.value + ",");
            current = current.next;
            if (current == null) {
                break;
            }


        }
        System.out.println("]");

    }
}
