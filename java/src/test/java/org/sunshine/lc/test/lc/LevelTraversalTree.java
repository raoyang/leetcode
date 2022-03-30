package org.sunshine.lc.test.lc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/***
 * 层序遍历二叉树
 */
public class LevelTraversalTree {

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

        levelOrder(root);
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        if(root == null){
            return new ArrayList<>();
        }
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        linkedList.addLast(root);
        List<List<Integer>> res = new ArrayList<>();
        while(!linkedList.isEmpty()){
            List<Integer> list = new ArrayList<>();
            LinkedList<TreeNode> temp = new LinkedList<>();
            while(!linkedList.isEmpty()){
                TreeNode node = linkedList.pollFirst();
                list.add(node.val);
                if(node.left != null){
                    temp.addLast(node.left);
                }
                if(node.right != null){
                    temp.addLast(node.right);
                }
            }
            linkedList = temp;
            if(!list.isEmpty()){
                res.add(list);
            }
        }
        return res;
    }

    private static void levelOrderPrint(TreeNode root){
        if(root == null){
            return;
        }
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        linkedList.addLast(root);
        while(!linkedList.isEmpty()){
            List<Integer> list = new ArrayList<>();
            LinkedList<TreeNode> temp = new LinkedList<>();
            while(!linkedList.isEmpty()){
                TreeNode node = linkedList.pollLast();
                list.add(node.val);
                if(node.left != null){
                    temp.addLast(node.left);
                }
                if(node.right != null){
                    temp.addLast(node.right);
                }
            }
            linkedList = temp;
        }
    }
}
