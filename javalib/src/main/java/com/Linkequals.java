package com;

/**
 * Created by Administrator on 2017/9/2.
 */

public class Linkequals {


    public static void main(String args[]) {

        Node node8 = new Node(8);
        Node node5 = new Node(node8, 5);
        Node node4 = new Node(node5, 4);
        Node node2 = new Node(node4, 2);
        Node head1 = new Node(node2, 1);


        node8 = new Node(8);
        Node node7 = new Node(node8, 7);
        Node node6 = new Node(node7, 6);
        Node node3 = new Node(node6, 3);
        node2 = new Node(node3, 2);
        Node head2 = node2;
        printSame(head1, head2);


    }

    private static void printSame(Node head1, Node head2) {

        while (true) {
            if (head1 == null || head2 == null) {
                return;
            }
            if (head1.value == head2.value) {
                System.out.println(head1.value);
                head1 = head1.next;
                head2 = head2.next;
            } else if (head1.value < head2.value) {
                head1 = head1.next;
            } else {
                head2 = head2.next;
            }
        }


    }
}
