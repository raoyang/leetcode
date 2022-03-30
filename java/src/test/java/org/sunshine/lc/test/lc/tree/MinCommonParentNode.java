package org.sunshine.lc.test.lc.tree;

import org.sunshine.lc.test.lc.TreeNode;

/***
 * 二叉树最小公共父节点
 */
public class MinCommonParentNode {

    public static void main(String args[]){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        TreeNode node = findCommonParent(root, root.left.left, root.left.right);
        System.out.println(node);
    }

    private static TreeNode findCommonParent(TreeNode node, TreeNode p1, TreeNode p2){
        if(node == null || node == p1 || node == p2){
            return node;
        }
        TreeNode left = findCommonParent(node.left, p1, p2);
        TreeNode right = findCommonParent(node.right, p1, p2);
        //node节点，左边、右边都包含，说明就是node
        if(left != null && right != null){
            return node;
        }

        return left != null ? left : right;
    }
}
