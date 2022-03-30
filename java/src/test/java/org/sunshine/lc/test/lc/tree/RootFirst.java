package org.sunshine.lc.test.lc.tree;

import org.sunshine.lc.test.lc.TreeNode;

import java.util.Stack;

public class RootFirst {

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

        orderFirst(root);
    }

    /***
     * 递归先序遍历二叉树
     * @param root
     */
    private static void orderFirst(TreeNode root){
        if(root == null){
            return;
        }
        System.out.println(root.val);
        orderFirst(root.left);
        orderFirst(root.right);
    }

    /***
     * 非递归，采用栈的形式打印
     * @param root
     */
    private static void orderSecond(TreeNode root){
        if(root == null){
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            System.out.println(node.val);
            if(node.left!=null){
                stack.push(node.right);
            }
            if(node.right!=null){
                stack.push(node.left);
            }
        }
    }
}
