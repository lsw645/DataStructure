package com.lxw.data.tree;

import java.util.LinkedList;
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


    public TreeNode put(int data) {
        TreeNode node = root;
        if (node == null) {
            node = new TreeNode(data);
            root = node;
            return node;
        }

        TreeNode parent = node;
        while (node != null) {
            parent = node;
            if (data < node.data) {
                node = node.leftChild;
            } else if (data > node.data) {
                node = node.rightChild;
            } else {
                return node;
            }
        }
        TreeNode newNode = new TreeNode(data);
        newNode.parent = parent;
        if (parent.data > data) {
            parent.leftChild = newNode;
        } else {
            parent.rightChild = newNode;
        }
        return newNode;
    }


    public void delNode(int data) {
        TreeNode node = root;
        if (node == null) {
            throw new NullPointerException("tree is empty");
        }
        //查找要删除的节点
        while (node != null) {
            if (data < node.data) {
                node = node.leftChild;
            } else if (data > node.data) {
                node = node.rightChild;
            } else {
                break;
            }
        }

        if (node != null) {
            TreeNode parent = node.parent;
            //第一种情况 叶子节点  没有子树，直接删除
            if (node.leftChild == null && node.rightChild == null) {
                if (parent == null) {
                    root = null;
                } else {
                    if (parent.leftChild == node) {
                        parent.leftChild = null;
                    } else if (parent.rightChild == node) {
                        parent.rightChild = null;
                    }
                }
                //第二种情况 只有一个子节点
            } else if (node.leftChild != null && node.rightChild == null) {
                TreeNode leftChild = node.leftChild;
                leftChild.parent = parent;
                if (parent == null) {
                    root = leftChild;
                } else {
                    if (parent.leftChild == node) {
                        parent.leftChild = leftChild;
                    } else if (parent.rightChild == node) {
                        parent.rightChild = leftChild;
                    }
                }
                //第二种情况 只有一个子节点
            } else if (node.leftChild == null && node.rightChild != null) {
                TreeNode rightChild = node.rightChild;
                rightChild.parent = parent;
                if (parent == null) {
                    root = rightChild;
                } else {
                    if (parent.leftChild == node) {
                        parent.leftChild = rightChild;
                    } else if (parent.rightChild == node) {
                        parent.rightChild = rightChild;
                    }
                }
                //第三中情况 有两个子节点 寻找后续节点
            } else {
                //后继节点
                if (node.rightChild.leftChild == null) {
                    TreeNode rightChild = node.rightChild;
                    rightChild.parent = parent;
                    if (parent == null) {
                        root = rightChild;
                    } else {
                        if (parent.leftChild == node) {
                            parent.leftChild = rightChild;
                        } else if (parent.rightChild == node) {
                            parent.rightChild = rightChild;
                        }
                    }
                } else {
                    TreeNode miniNode = getMiniTreeNode(node.rightChild);
                    //如果最小点有右子树，则右子树移动到 最小点parent的左子树
                    if (miniNode.rightChild != null) {
                        miniNode.rightChild.parent = miniNode.parent;
                        miniNode.parent.leftChild = miniNode.rightChild;
                    }

                    miniNode.parent = node.parent;
                    if (parent == null) {
                        root = miniNode;
                    } else {
                        if (parent.leftChild == node) {
                            parent.leftChild = miniNode;
                        } else if (parent.rightChild == node) {
                            parent.rightChild = miniNode;
                        }
                    }

                    miniNode.leftChild = node.leftChild;
                    node.leftChild.parent = miniNode;

                    miniNode.rightChild = node.rightChild;
                    node.rightChild.parent = miniNode;
                }
            }
        }
    }

    private TreeNode getMiniTreeNode(TreeNode node) {
        TreeNode leftNode = node;
        TreeNode parent = leftNode;
        while (leftNode != null) {
            parent = leftNode;
            leftNode = leftNode.leftChild;
        }
        return parent;

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
     *
     * @param root
     */
    public void midOrder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node = root;
        TreeNode parent = node;

        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.leftChild;
            }
            TreeNode treeNode = stack.pop();
            System.out.print(treeNode.data + "  ");
            if (treeNode.rightChild != null) {
                node = treeNode.rightChild;
            }
        }
    }

    /**
     * 使用stack 实现 后序 LRD
     *
     * @param root
     */
    public void postOrder(TreeNode root) {

        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                if (root.rightChild != null) {
                    stack.push(root.rightChild);
                }
                stack.push(root);
                root = root.leftChild;
            }
            TreeNode node = stack.pop();
            if (node.rightChild != null && !stack.isEmpty() && node.rightChild == stack.peek()) {//一定不要忘了栈为空的时候
                stack.pop();
                stack.push(node);
                root = node.rightChild;
            } else {
                System.out.print(node.data + " ");
                root = null;
            }
        }
    }

    // -------------------------------------------------------------
    public void displayTreeByStack() {
        Stack<TreeNode> globalStack = new Stack();
        globalStack.push(root);
        int nBlanks = 32;
        boolean isRowEmpty = false;
        System.out.println(
                "......................................................");
        while (!isRowEmpty) {
            Stack<TreeNode> localStack = new Stack();
            isRowEmpty = true;

            for (int j = 0; j < nBlanks; j++)
                System.out.print(' ');

            while (!globalStack.isEmpty()) {
                TreeNode temp = globalStack.pop();
                if (temp != null) {
                    System.out.print(temp.data);
                    localStack.push(temp.leftChild);
                    localStack.push(temp.rightChild);

                    if (temp.leftChild != null ||
                            temp.rightChild != null)
                        isRowEmpty = false;
                } else {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < nBlanks * 2 - 2; j++)
                    System.out.print(' ');
            }  // end while globalStack not empty
            System.out.println();
            nBlanks /= 2;
            while (!localStack.isEmpty())
                globalStack.push(localStack.pop());
        }  // end while isRowEmpty is false
        System.out.println(
                "......................................................");
    }  // end displayTreeByStack()


    public void displayTree() {
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int  i = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            i++;
            if((i&(i-1))==0){
                System.out.println("");

            }
            if(node ==null){
                System.out.print("_"+" ");
            }else{
                System.out.print(node.data+" ");
                queue.offer(node.leftChild);
                queue.offer(node.rightChild);
            }


        }
    }
}
