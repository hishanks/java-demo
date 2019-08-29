package com.demo.datastructure.tree;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Shanks
 * @date 2019-03-07
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BinaryTreeNode {

    private int data;
    private BinaryTreeNode left;
    private BinaryTreeNode right;
}