package com.lxw.data.stack;

/**
 * <pre>
 *     author : lxw
 *     e-mail : lsw@tairunmh.com
 *     time   : 2018/08/22
 *     desc   :输入一个单链表的头结点，从尾到头打印出每个结点的值。
 * </pre>
 */

class Test1 {
    private static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        printLinkReverse(head);
    }

    private static void printLinkReverse(Node head) {
        if (head != null) {
            printLinkReverse(head.next);
            System.out.println(head.data);
        }
    }

}
