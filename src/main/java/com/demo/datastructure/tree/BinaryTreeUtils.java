package com.demo.datastructure.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author Shanks
 * @date 2019-03-07
 */
public class BinaryTreeUtils {

    /**
     * 前序遍历（递归）
     *
     * @param root root
     */
    public void preOrderTraversal(BinaryTreeNode root) {
        if (root != null) {
            System.out.print(root.getData() + "\t");
            preOrderTraversal(root.getLeft());
            preOrderTraversal(root.getRight());
        }
    }

    /**
     * 前序遍历（非递归）
     *
     * @param root root
     */
    public void preOrderTraversalNonRecursive(BinaryTreeNode root) {
        Stack<BinaryTreeNode> stack = new Stack<>();
        while (true) {
            while (root != null) {
                System.out.print(root.getData() + "\t");
                stack.push(root);
                root = root.getLeft();
            }
            if (stack.isEmpty()) {
                break;
            }
            root = stack.pop();
            root = root.getRight();
        }
    }

    /**
     * 中序遍历（递归）
     *
     * @param root root
     */
    public void inOrderTraversal(BinaryTreeNode root) {
        if (null != root) {
            inOrderTraversal(root.getLeft());
            System.out.print(root.getData() + "\t");
            inOrderTraversal(root.getRight());
        }
    }

    /**
     * 中序遍历（非递归）
     *
     * @param root root
     */
    public void inOrderTraversalNonRecursive(BinaryTreeNode root) {
        Stack<BinaryTreeNode> stack = new Stack<>();
        while (true) {
            while (root != null) {
                stack.push(root);
                root = root.getLeft();
            }
            if (stack.isEmpty()) {
                break;
            }
            root = stack.pop();
            System.out.print(root.getData() + "\t");
            root = root.getRight();
        }
    }

    /**
     * 后序遍历采用递归的方式
     *
     * @param root root
     */
    public void postOrderTraversal(BinaryTreeNode root) {
        if (root != null) {
            postOrderTraversal(root.getLeft());
            postOrderTraversal(root.getRight());
            System.out.print(root.getData() + "\t");
        }
    }

    /**
     * 后序遍历采用非递归的方式
     *
     * @param root root
     */
    public void postOrderTraversalNonRecursive(BinaryTreeNode root) {
        Stack<BinaryTreeNode> stack = new Stack<>();
        while (true) {
            if (root != null) {
                stack.push(root);
                root = root.getLeft();
            } else {
                if (stack.isEmpty()) {
                    return;
                }
                if (null == stack.lastElement().getRight()) {
                    root = stack.pop();
                    System.out.print(root.getData() + "\t");
                    while (root == stack.lastElement().getRight()) {
                        System.out.print(stack.lastElement().getData() + "\t");
                        root = stack.pop();
                        if (stack.isEmpty()) {
                            break;
                        }
                    }
                }
                if (!stack.isEmpty()) {
                    root = stack.lastElement().getRight();
                } else {
                    root = null;
                }
            }
        }
    }

    /**
     * 广度优先遍历 - BFS
     * 利用队列Queue的FIFO特性实现比较简单
     *
     * @param root root
     */
    public void breadthFirstSearch(BinaryTreeNode root) {
        BinaryTreeNode temp;
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        // 添加一个元素并返回true，如果队列已满，则返回false
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 移除并返问队列头部的元素，如果队列为空，则返回null
            temp = queue.poll();
            System.out.print(temp.getData() + "\t");
            if (temp.getLeft() != null) {
                queue.offer(temp.getLeft());
            }
            if (temp.getRight() != null) {
                queue.offer(temp.getRight());
            }
        }
    }

    /**
     * 深度优先遍历 - DFS
     * 利用栈Stack的FILO特性实现
     *
     * @param root root
     */
    public void depthFirstSearch(BinaryTreeNode root) {
        if (root == null) {
            return;
        }
        Stack<BinaryTreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            BinaryTreeNode temp = stack.peek();
            System.out.print(temp.getData() + "\t");
            stack.pop();
            if (temp.getRight() != null) {
                stack.push(temp.getRight());
            }
            if (temp.getLeft() != null) {
                stack.push(temp.getLeft());
            }
        }
    }
}