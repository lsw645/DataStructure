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
//        TreeNode newNode = deleteTheLastKNode3(8, node);
        TreeNode newNode = reverseLink(node);
        while (newNode != null) {
            System.out.println(newNode.data);
            newNode = newNode.next;
        }
//        node.next.next.next.next.next.next.next = node;
//        TreeNode midNode = getMidNode2(node);
//        System.out.println(midNode.data);

        boolean is = isCircularLinkedList(node);
        System.out.println(is);

//        System.out.println(getReversKNodeData(2, node));
//        System.out.println(getReversKNodeData(10, node));
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
     * 分析：
     * 要删除倒数第K个结点，
     * 则需要 倒数第K+1个节点
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

//    /**
//     * 面试题：给定一个单链表的头结点，删除倒数第 k 个结点
//     *
//     * @return
//     */
//    private static boolean deleteTheLastKNode(int k, TreeNode node) {
//        if (node == null) {
//            throw new IllegalArgumentException("node header require not null");
//        }
//        TreeNode quick = node;
//        TreeNode slow = node;
//        int count = 0;
//        while (quick.next != null) {
//            if (count > k + 1) {
//                slow = slow.next;
//            }
//            quick = quick.next;
//            count++;
//        }
//        //k数值大于 链表长度
//        if (slow == node) {
//            return false;
//        }
//
//        //倒数k+1个 结点
//        TreeNode k1Node = slow;
//        slow.next = k1Node.next.next;
//        return true;
//    }
//
//    private static boolean deleteTheLastKNode2(int k, TreeNode node) {
//        if (node == null) {
//            throw new IllegalArgumentException("node header require not null");
//        }
//        if (k < 0) {
//            return false;
//        }
//        TreeNode quick = node;
//        int count = 0;
//        if (k == 1) {
//            //获得倒数第一个
//            TreeNode temp = quick;
//            while (quick.next != null) {
//                temp = quick;
//                quick = quick.next;
//                count++;
//            }
//            if (count + 1 == 1) {
//                node = null;
//                return true;
//            } else {
//                temp.next = null;
//                return true;
//            }
//
//        } else {
//            TreeNode preSlow = node;
//            while (quick.next != null) {
//                //快指针走K+1步后，slow再走
//                if (count == k + 1) {
//                    preSlow = preSlow.next;
//                }
//                quick = quick.next;
//                count++;
//            }
//            //删除倒数第K个结点
//            preSlow.next = preSlow.next.next;
//            return true;
//        }
//    }

    /**
     * @param k
     * @param node
     * @return 返回更新过的treenode
     */
    private static TreeNode deleteTheLastKNode3(int k, TreeNode node) {
        if (node == null) {
            throw new IllegalArgumentException("node header require not null");
        }
        if (k <= 0) {
            k = 1;
        }


        TreeNode slow = node;
        TreeNode quick = node;
//        TreeNode node = new TreeNode(1);
//        node.next = new TreeNode(2);
        // k =2
        //先走K步
        for (int i = 0; i < k; i++) {
            if (quick == null) {
                throw new IllegalArgumentException("the length of node is lager than k");
            }
            quick = quick.next;
        }
        //if k == length
        if (quick == null) {
            return node.next;
        }

        while (quick.next != null) {
            quick = quick.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;
        return node;

    }

    /**
     * 面试题：定义一个单链表，输入一个链表的头结点，
     * 反转该链表并输出反转后链表的头结点。
     * 为了方便，我们链表的 data 采用整型。
     *
     * @param head
     * @return 思路：反转结点，其实就是  A->B, 变成B->A,
     * 使用temp保存链表的上一个值，然后遍历，遍历出来的值都指向上一个
     */
    private static TreeNode reverseLink(TreeNode head) {
        if (head == null) {
            throw new IllegalArgumentException("node header require not null");
        }
        TreeNode node = head;
        TreeNode next = null;
        TreeNode pre = null;
//        while (node != null) {
////            next = node;
////
////            pre.next = next;
////
////            node = next;
////
////            pre = node;
//            next = node.next;
//            pre = node;
//            pre.next =next;
//            node = next;
//
//        }

        while (node != null) {
            // 先用 next 保存下一个要反转的结点，不然会导致链表断裂。
            next = node.next;
            // 再把现在结点的 next 引用指向上一个结点
            node.next = pre;
            // 把当前结点赋值给 nodePre 变量，以便于下一次赋值
            pre = node;
            // 向后遍历
            node = next;
        }
        return pre;
    }

    private static TreeNode reverseLink2(TreeNode head) {
        if (head == null) {
            throw new IllegalArgumentException("node header require not null");
        }
        TreeNode node = head;
        TreeNode next = null;
        TreeNode pre = null;
        while (node != null) {
            next = node.next;
            node.next = pre;
            pre =node;
            node = next;

        }
        return pre;
    }
    //1->2->3->4->5->6->7
    //7->6->5->4->3->2->1
    //遍历第一个链表时 获取
    // pre next
    //   next =node;
    //  pre =node.next;
    //  next =p
}
