package com.lxw.data.tree;

import java.util.Stack;

/**
 * <pre>
 *     author : lxw
 *     e-mail : lsw@tairunmh.com
 *     time   : 2018/05/28
 *     desc   :
 * </pre>
 */

public class SearchBinaryTree {
    private int size;

    public TreeNode root;

    public SearchBinaryTree() {

    }

//    public TreeNode put(int data) {
//        TreeNode node = root;
//        if (node == null) {
//            node = new TreeNode(data);
//            root = node;
//            return node;
//        }
//
//        TreeNode parent = null;
//        while (node != null) {
//            parent = node;
//            if (node.data > data) {
//                node = node.leftChild;
//            } else if (node.data < data) {
//                node = node.rightChild;
//            } else {
//                return node;
//            }
//        }
//
//        TreeNode newNode = new TreeNode(data);
//        if (parent.data > data) {
//            parent.leftChild = newNode;
//        } else if (parent.data < data) {
//            parent.rightChild = newNode;
//        }
//        newNode.parent = parent;
//        return newNode;
//    }
public TreeNode put(int data) {
    if (root == null) {
        TreeNode node = new TreeNode(data);
        root = node;
        return node;
    }

    TreeNode parent = null;
    TreeNode node = root;
    for(; node != null;) {
        parent = node;
        if (data < node.data) {
            node = node.leftChild;
        } else if(data > node.data) {
            node = node.rightChild;
        } else {
            return node;
        }
    }
    TreeNode newNode = new TreeNode(data);
    if (data < parent.data) {
        parent.leftChild = newNode;
    } else {
        parent.rightChild = newNode;
    }

    // 有坑
    newNode.parent = parent;
    return newNode;

}

    static class TreeNode {
        TreeNode parent;
        TreeNode leftChild;
        TreeNode rightChild;
        int data;

        public TreeNode(int data) {
            this.data = data;
        }
    }

    /**
     * 前序遍历
     */
    public void preOrderTraversal(TreeNode node) {
        if (node == null) {
            return;
        }

        System.out.println("pre:  " + node.data);
        preOrderTraversal(node.leftChild);
        preOrderTraversal(node.rightChild);
    }

    /**
     * 中序遍历
     */
    public void midOrderTraversal(TreeNode node) {
        if (node == null) {
            return;
        }

        midOrderTraversal(node.leftChild);
        System.out.println("mid: " + node.data);
        midOrderTraversal(node.rightChild);
    }

    /**
     * 后序遍历
     */
    public void postOrderTreversal(TreeNode node) {
        if (node == null) {
            return;
        }

        postOrderTreversal(node.leftChild);
        postOrderTreversal(node.rightChild);
        System.out.println("mid: " + node.data);
    }


    public void preOrder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.rightChild != null) {
                stack.push(node.rightChild);
            }

            if (node.leftChild != null) {
                stack.push(node.leftChild);
            }

            System.out.print(node.data + "  ");

        }
    }

    /**
     * 使用stack 实现 中序  LDR
     * @param root
     */
    public void midOrder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node = root;
        TreeNode parent = node;

        while(node!=null||!stack.isEmpty()){
            while(node!=null){
                stack.push(node);
                node = node .leftChild;
            }
            TreeNode treeNode = stack.pop();
            System.out.println(treeNode.data);
            if(treeNode.rightChild!=null){
                node =treeNode.rightChild;
            }
        }
    }

    /**
     * 使用stack 实现 后序 LRD
     * @param root
     */
    public void postOrder(TreeNode root) {

        Stack<TreeNode> stack = new Stack<>();
        while(root!=null||!stack.isEmpty()){
            while(root!=null){
                if(root.rightChild!=null){
                    stack.push(root.rightChild);
                }
                stack.push(root);
                root = root.leftChild;
            }
            TreeNode node = stack.pop();
            if(node.rightChild!=null&&!stack.isEmpty()&&node.rightChild==stack.peek()){//一定不要忘了栈为空的时候
                stack.pop();
                stack.push(node);
                root = node.rightChild;
            }else {
                System.out.print(node.data+" ");
                root = null;
            }
        }
    }
}
