package com.demo.datastructure.tree;

import org.junit.jupiter.api.Test;

/**
 * @author Shanks
 * @date 2019-06-20
 */
public class BinaryTreeUtilsTest {

    private static BinaryTreeNode node10 = new BinaryTreeNode(10, null, null);
    private static BinaryTreeNode node8 = new BinaryTreeNode(8, null, null);
    private static BinaryTreeNode node9 = new BinaryTreeNode(9, null, node10);
    private static BinaryTreeNode node4 = new BinaryTreeNode(4, null, null);
    private static BinaryTreeNode node5 = new BinaryTreeNode(5, node8, node9);
    private static BinaryTreeNode node6 = new BinaryTreeNode(6, null, null);
    private static BinaryTreeNode node7 = new BinaryTreeNode(7, null, null);
    private static BinaryTreeNode node2 = new BinaryTreeNode(2, node4, node5);
    private static BinaryTreeNode node3 = new BinaryTreeNode(3, node6, node7);
    private static BinaryTreeNode node1 = new BinaryTreeNode(1, node2, node3);
    private static BinaryTreeUtils treeUtils = new BinaryTreeUtils();

    @Test
    void testPreOrder() {
        System.out.println("-----前序遍历 - 递归 ------");
        treeUtils.preOrderTraversal(node1);

        System.out.println("-----前序遍历 - 非递归 ------");
        treeUtils.preOrderTraversalNonRecursive(node1);
    }

    @Test
    void testInOrder() {
        System.out.println("-----中序遍历 - 递归 ------");
        treeUtils.inOrderTraversal(node1);

        System.out.println("-----中序遍历 - 非递归 ------");
        treeUtils.inOrderTraversalNonRecursive(node1);
    }

    @Test
    void testPostOrder() {
        System.out.println("-----后序遍历 - 递归 ------");
        treeUtils.postOrderTraversal(node1);

        System.out.println("-----后序遍历 - 非递归 ------");
        treeUtils.postOrderTraversalNonRecursive(node1);
    }

    /**
     * 1	2	4	5	8	9	10	3	6	7
     * 从最左侧，每次遍历都直接干到底，然后依次往右边来
     */
    @Test
    void testLevelOrder() {
        System.out.println("----- BFS广度优先遍历 ------");
        treeUtils.breadthFirstSearch(node1);
        System.out.println();
    }

    @Test
    void testDepthFirstSearch() {
        System.out.println("----- DFS深度优先遍历 -----");
        treeUtils.depthFirstSearch(node1);
        System.out.println();
    }
}