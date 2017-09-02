package com;

/**
 * Created by Administrator on 2017/9/2.
 */

public class RevertLink {
    public static void main(String args[]) {
        //12458
        Node node8 = new Node(8);
        Node node5 = new Node(node8, 5);
        Node node4 = new Node(node5, 4);
        Node node2 = new Node(node4, 2);
        Node head1 = new Node(node2, 1);

        Node.print(head1);
        Node head2 = n(null, head1);
        Node.print(head2);

    }



//    12458

    /**
     * @param left
     * @param node
     */
    private static Node n(Node left, Node node) {
        if (node == null) {
            return left;
        }
        Node next = node.next;
        node.next = left;
        return n(node, next);

    }

}
