package com.lxw.data.algorithm;

/**
 * <pre>
 *     author : lxw
 *     e-mail : lsw@tairunmh.com
 *     time   : 2018/08/24
 *     desc   :
 *     输入一个单链表的头结点，返回它的中间元素。为了方便，元素值用整型表示。
 * </pre>
 */

class Test6 {
    private static class TreeNode {
        int data;
        TreeNode next;

        public TreeNode(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.next = new TreeNode(2);
        node.next.next = new TreeNode(3);
        node.next.next.next = new TreeNode(4);
        node.next.next.next.next = new TreeNode(5);
        node.next.next.next.next.next = new TreeNode(6);
        node.next.next.next.next.next.next = new TreeNode(7);
//        node.next.next.next.next.next.next.next = node;
//        TreeNode midNode = getMidNode2(node);
//        System.out.println(midNode.data);

        boolean is = isCircularLinkedList(node);
        System.out.println(is);

        System.out.println(getReversKNodeData(2, node));
        System.out.println(getReversKNodeData(10, node));
    }

    /**
     * 遍历太多次，时间复杂度太高
     *
     * @param node
     * @return
     */
    private static TreeNode getMidNode(TreeNode node) {
        if (node == null) {
            throw new IllegalArgumentException("node header require not null");
        }
        TreeNode head = node;
        if (head.next == null) {
            return head;
        }

        int count = 1;
        while (head.next != null) {
            count++;
            head = head.next;
        }
        head = node;
        for (int i = 0; i < count / 2; i++) {
            head = head.next;
        }
        return head;
    }

    /**
     * 优化思路：
     * 快慢指针 快指针走两步，而慢指针走一步
     *
     * @param node
     * @return
     */
    private static TreeNode getMidNode2(TreeNode node) {
        if (node == null) {
            throw new IllegalArgumentException("node header require not null");
        }
        TreeNode quick = node;
        if (quick.next == null) {
            return quick;
        }
        TreeNode slow = node;
        int i = 0;
        while (quick.next != null) {
            quick = quick.next;
            i++;
            while (i == 2) {
                i = 0;
                slow = slow.next;
            }
        }
        return slow;
    }

    /**
     * 面试题：给定一个单链表的头结点，判断这个链表是否是循环链表。
     * 同样是快慢指针
     *
     * @return
     */
    private static boolean isCircularLinkedList(TreeNode node) {
        if (node == null) {
            throw new IllegalArgumentException("node header require not null");
        }
        TreeNode head = node;
        while (head.next != null) {
            head = head.next;
            if (head == node) {
                return true;
            }
        }
        return false;
    }


    /**
     * * 面试题：输入一个单链表的头结点，输出该链表中倒数第 k 个节点的值。
     *
     * @param k    倒数第 k 个节点
     * @param node 头结点
     * @return 倒数第 k 个节点的值。
     */
    private static int getReversKNodeData(int k, TreeNode node) {
        if (node == null) {
            throw new IllegalArgumentException("node header require not null");
        }
        int count = 0;
        TreeNode quick = node;
        TreeNode slow = node;
        while (quick.next != null) {

            if (count >= k) {
                slow = slow.next;
            }
            count++;
            quick = quick.next;
        }
        if (slow == node) {
            throw new IllegalArgumentException("the k is larger than the length of TreeNode ");
        }
        return slow.data;
    }
}
