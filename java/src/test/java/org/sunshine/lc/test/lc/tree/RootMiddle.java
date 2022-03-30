package org.sunshine.lc.test.lc.tree;

import org.sunshine.lc.test.lc.TreeNode;

import java.util.Stack;

public class RootMiddle {

    public static void main(String args[]){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        root.left.left.left = new TreeNode(8);
        root.left.left.right = new TreeNode(9);
        root.left.right.left = new TreeNode(10);
        root.left.right.right = new TreeNode(11);
        orderSecond(root);
    }

    /***
     * 递归中序遍历二叉树
     * @param root
     */
    private static void orderFirst(TreeNode root){
        if(root == null){
            return;
        }
        orderFirst(root.left);
        System.out.println(root.val);
        orderFirst(root.right);
    }

    /***
     * 非递归，采用栈的形式打印
     * 思想：
     * 把一棵树的所有左节点，依次压栈，
     * 如果没有了左节点，则弹出该节点N
     * 对弹出的节点N的右节点继续做上述操作
     * @param root
     */
    private static void orderSecond(TreeNode root){
        if(root == null){
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        while(!stack.isEmpty() || root != null){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            TreeNode node = stack.pop(); //可能会把栈弹空，此时需要判断root!=null
            System.out.println(node.val);
            root = node.right;
        }
    }
}
