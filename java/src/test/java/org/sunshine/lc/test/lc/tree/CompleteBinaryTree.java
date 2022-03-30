package org.sunshine.lc.test.lc.tree;

import org.sunshine.lc.test.lc.TreeNode;

import java.util.LinkedList;

public class CompleteBinaryTree {

    public static void main(String args[]){

    }

    /***
     * 判断一棵二叉树是否是完全二叉树
     * 无法使用动态规划判断的树
     * @param root
     * @return
     */
    private static boolean isCompleteBinaryTree(TreeNode root){

        LinkedList<TreeNode> linkedList = new LinkedList<>();
        linkedList.addLast(root);

        boolean firstLeaf = false;
        while(!linkedList.isEmpty()){
            TreeNode treeNode = linkedList.pollFirst();
            if(treeNode.left == null && treeNode.right != null){
                return false;
            }
            if(treeNode.left == null || treeNode.right == null){
                firstLeaf = true;
            }
            if(firstLeaf){
                if(treeNode.left!=null || treeNode.right!=null){
                    return false;
                }
            }
            if(treeNode.right != null){
                linkedList.addLast(treeNode.right);
            }
            if(treeNode.left != null){
                linkedList.addLast(treeNode.left);
            }
        }
        return true;
    }
}
