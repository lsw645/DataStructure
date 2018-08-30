package com.lxw.data.algorithm;

/**
 * <pre>
 *     author : lxw
 *     e-mail : lsw@tairunmh.com
 *     time   : 2018/08/29
 *     desc   :面试题：输入两个递增排序的单链表，data 域为 int 型值。合并这两个链表，
 *     并使新链表中的结点也是按照递增排序的。例如链表 A：1->3->5，链表 B：2->4，
 *     那它们合并后就是 1->2->3->4->5
 * </pre>
 */

class Test10 {
    private static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        Node head1 = new Node(1);
        head1.next = new Node(3);
        head1.next.next = new Node(5);
        head1.next.next.next = new Node((7));

        Node head2 = new Node(2);
        head2.next = new Node(4);
        head2.next.next = new Node(6);
        head2.next.next.next = new Node(8);

        Node head = mergeNode2(head1, head2);
        while (head != null) {
            System.out.print(head.data + "->");
            head = head.next;
        }
        System.out.print("null");
    }

    private static Node mergeNode(Node node1, Node node2) {
        if (node1 == null) {
            return node2;
        }
        if (node2 == null) {
            return node1;
        }
        Node node = null;
        if (node1.data <= node2.data) {
            node = node1;
            node.next = mergeNode(node1.next, node2);
        } else {
            node = node2;
            node.next = mergeNode(node2.next, node1);
        }
        return node;
    }

    /**
     * 使用迭代代替 递归
     *
     * @return
     */
    private static Node mergeNode2(Node node1, Node node2) {
        if (node1 == null) {
            return node2;
        }
        if (node2 == null) {
            return node1;
        }
        Node node = new Node(0);
        Node temp = node;
        while (node1 != null && node2 != null) {
            if (node1.data < node2.data) {
                node.next = node1;
                node1 = node1.next;
            } else {
                node.next = node2;
                node2 = node2.next;
            }
            node = node.next;
        }
        if (node1 != null) {
            node.next = node1;
        }
        if (node2 != null) {
            node.next = node2;
        }

        return temp.next;
    }


}
