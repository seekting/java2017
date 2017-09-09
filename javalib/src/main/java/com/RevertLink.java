package com;

/**
 * 递归实现链表倒转
 * Created by Administrator on 2017/9/2.
 */

public class RevertLink {
    public static void main(String args[]) {
        //12458
//        Node node8 = new Node(8);
//        Node node5 = new Node(node8, 5);
//        Node node4 = new Node(node5, 4);
        Node node2 = new Node(null, 2);
        Node head1 = new Node(node2, 1);

        Node.print(head1);
//        Node head2 = reverse1(null, head1);
        Node head2 = revertFor(head1);
        Node.print(head2);

    }

    /**
     * 1>2>4>5>8
     *
     *
     * c=1,n=2,nn=4
     * 1<2 c=2 n=4 nn=5
     * 1<2<4 c=4,n=5 nn=8
     * 1<2<4<5 c=5,n=8 nn=null
     * 1<2<4<5<8 c=8 n=null
     *
     * @param head
     * @return
     */
    private static Node revertFor(Node head) {

        Node current = head;
        Node next = head.next;
        current.next = null;
        if (next == null) {
            return head;
        }
        while (true) {
            Node nextNext = next.next;
            next.next = current;
            current = next;
            next = nextNext;
            if (next == null) {
                break;
            }
        }
        return current;
    }


//    12458

    /**
     * @param left
     * @param node
     */
    private static Node reverse1(Node left, Node node) {
        if (node == null) {
            return left;
        }
        Node next = node.next;
        node.next = left;
        return reverse1(node, next);

    }

}
