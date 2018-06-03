package com.lxw.data.tree;

/**
 * <pre>
 *     author : lxw
 *     e-mail : lsw@tairunmh.com
 *     time   : 2018/06/03
 *     desc   :
 * </pre>
 */

public class AVLTree<E extends Comparable> {
    private TreeNode<E> root;

    private static final int EQUAL_HIGH = 0;
    private static final int LEFT_HIGH = 1;
    private static final int RIGHT_HIGH = -1;
    private int size;

    public boolean put(E ele) {
        TreeNode<E> node = root;
        if (node == null) {
            node = new TreeNode<E>(ele);
            node.balance = EQUAL_HIGH;
            root = node;
            return true;
        }
        TreeNode<E> parent = node;
        int cmp = 0;
        while (node != null) {
            parent = node;
            cmp = node.data.compareTo(ele);
            if (cmp < 0) {
                node = node.leftChild;
            } else if (cmp > 0) {
                node = node.rightChild;
            } else {
                return false;
            }
        }
        TreeNode<E> newNode = new TreeNode<E>(ele);
        if (cmp < 0) {
            parent.leftChild = newNode;
        } else {
            parent.rightChild = newNode;
        }
        newNode.parent = parent;
        size++;

        while (parent != null) {
            if (cmp < 0) {
                parent.balance++;
            } else {
                parent.balance--;
            }
            //如果父节点 已经平衡了，则说明 深度没有增加
            if (parent.balance == EQUAL_HIGH) {
                break;
            }
            if (Math.abs(parent.balance) == 2) {
                //  修复平衡
                fixAfterInsertion(parent);
                break;
            }
            parent = parent.parent;
        }

        return true;
    }

    private void fixAfterInsertion(TreeNode<E> parent) {
        if (parent.balance == 2) {
            leftBalance(parent);
        } else if (parent.balance == -2) {
            rightBalance(parent);
        }
    }

    private void rightBalance(TreeNode<E> node) {
        TreeNode<E> right = node.rightChild;
        switch (right.balance){
            case RIGHT_HIGH:
                leftRotate(node);
                right.balance = EQUAL_HIGH;
                node.balance =EQUAL_HIGH;
                break;
            case LEFT_HIGH:
                TreeNode<E> rl = right.leftChild;
                switch (rl.balance) {
                    case LEFT_HIGH:
                        rl.balance =EQUAL_HIGH;
                        right.balance = RIGHT_HIGH;
                        node.balance =EQUAL_HIGH;
                        break;
                    case RIGHT_HIGH:
                        rl.balance =EQUAL_HIGH;
                        right.balance = EQUAL_HIGH;
                        node.balance =LEFT_HIGH;
                        break;
                    case EQUAL_HIGH:
                        node.balance = EQUAL_HIGH;
                        rl.balance = EQUAL_HIGH;
                        right.balance = EQUAL_HIGH;
                        break;
                }
                rightRotate(right);
                leftRotate(node);

                break;
        }
    }

    private void leftBalance(TreeNode<E> node) {
        TreeNode<E> left = node.leftChild;
        switch (left.balance) {
            //添加到左子树的左子树时
            case LEFT_HIGH:
                rightRotate(left);
                left.balance = EQUAL_HIGH;
                node.balance = EQUAL_HIGH;
                break;
            case RIGHT_HIGH:
                TreeNode<E> lr = left.rightChild;
                switch (lr.balance) {
                    case LEFT_HIGH:

                        node.balance = RIGHT_HIGH;
                        lr.balance = EQUAL_HIGH;
                        left.balance = EQUAL_HIGH;
                        break;
                    case RIGHT_HIGH:
                        node.balance = EQUAL_HIGH;
                        lr.balance = EQUAL_HIGH;
                        left.balance = LEFT_HIGH;
                        break;
                    case EQUAL_HIGH:
                        node.balance = EQUAL_HIGH;
                        lr.balance = EQUAL_HIGH;
                        left.balance = EQUAL_HIGH;
                        break;
                }
                leftRotate(left);
                rightRotate(node);
                break;
            case EQUAL_HIGH:
                break;
        }
    }

    private void leftRotate(TreeNode<E> node) {
        if (node == null) {
            return;
        }
        TreeNode<E> right = node.rightChild;

        //右子树的左子树 断开连接给node的右子树
        node.rightChild = right.leftChild;
        if (node.leftChild != null) {
            node.leftChild.parent = node;
        }

        TreeNode<E> parent = node.parent;
        right.parent = parent;
        //若旋转的点为 root节点，需要给root赋值
        if (parent == null) {
            root = right;
        } else {
            if (parent.leftChild == node) {
                parent.leftChild = right;
            } else {
                parent.rightChild = right;
            }
        }
        right.leftChild = node;
        node.parent = right;
    }


    private void rightRotate(TreeNode<E> node) {
        if (node == null) {
            return;
        }
        TreeNode<E> left = node.leftChild;

        node.rightChild = left.rightChild;
        if (left.rightChild != null) {
            left.rightChild.parent = node;
        }

        TreeNode<E> parent = node.parent;
        left.parent = parent;
        if (parent == null) {
            root = left;
        } else {
            if (parent.leftChild == node) {
                parent.leftChild = left;
            } else {
                parent.rightChild = left;
            }
        }
        node.parent = left;
        left.rightChild = node;
    }

    public static class TreeNode<E extends Comparable> {
        int balance;
        TreeNode<E> parent;
        TreeNode<E> leftChild;
        TreeNode<E> rightChild;
        E data;

        public TreeNode(E data) {
            this.data = data;
        }
    }
}
