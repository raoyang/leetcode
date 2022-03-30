package org.sunshine.lc.test.lc.tree;

import org.sunshine.lc.test.lc.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 * 二叉树序列化和反序列化
 */
public class BinaryTreeSerialized {

    public static void main(String[] args){
        /**
         * 先序测试用例
        List<Integer> list = Arrays.asList(1, 2, 4, 8, null, null, 9, null, null, 5, 10, null, null, 11, null, null, 3, 6, 12, null, null, 13, null, null, 7, 14, null, null, 15, null, null);
        List<Integer> arrlist = new ArrayList<>(list);
        TreeNode node = rootFirstDeserializeBinaryTree(arrlist);

        System.out.println(node);
        List<Integer> arrayList = new ArrayList<>();
        rootFirstSerializeBinaryTree(node, arrayList);
        System.out.println(arrayList);*/


        /***
         * 后续测试用例
         */
        List<Integer> list = new ArrayList<>(Arrays.asList(null,null,4,null,null,5,2,null,null,6,null,null,7,3,1));
        List<Integer> reverseList = new ArrayList<>();
        for(int i = list.size() - 1 ; i >= 0 ; i --){
            reverseList.add(list.get(i));
        }
        TreeNode root = rootLastDeserializeBinaryTree(reverseList);

        List<Integer> res = new ArrayList<>();
        rootLastSerializeBinaryTree(root, res);
        System.out.println(res);
    }

    /***
     * 先序序列化二叉树
     * @param node
     * @param list
     * @return
     */
    private static void rootFirstSerializeBinaryTree(TreeNode node, List<Integer> list){
        if(node == null){
            list.add(null);
            return;
        }
        list.add(node.val);
        rootFirstSerializeBinaryTree(node.left, list);
        rootFirstSerializeBinaryTree(node.right, list);
    }

    /***
     * 先序反序列化二叉树
     * @param list
     * @return
     */
    private static TreeNode rootFirstDeserializeBinaryTree(List<Integer> list){
        if(list.isEmpty()){
            return null;
        }
        Integer val = list.remove(0);
        if(val == null){
            return null;
        }
        TreeNode head = new TreeNode(val);
        head.left = rootFirstDeserializeBinaryTree(list);
        head.right = rootFirstDeserializeBinaryTree(list);
        return head;
    }

    private static void rootMiddleSerializeBinaryTree(TreeNode node, List<Integer> list){
        if(node == null){
            list.add(null);
            return;
        }
        rootMiddleSerializeBinaryTree(node.left, list);
        list.add(node.val);
        rootMiddleSerializeBinaryTree(node.right, list);
    }

    /***
     * 中序无法完成反序列化，因为中序数组无法确定根节点是哪个
     * @param list
     * @return
     */
    private static TreeNode rootMiddleDeserializeBinaryTree(List<Integer> list){
        return null;
    }


    /***
     * 后续序列化二叉树
     * @param head
     * @param list
     */
    private static void rootLastSerializeBinaryTree(TreeNode head, List<Integer> list){
        if(head == null){
            list.add(null);
            return;
        }
        rootLastSerializeBinaryTree(head.left, list);
        rootLastSerializeBinaryTree(head.right, list);
        list.add(head.val);
    }

    /***
     * 先序反序列化二叉树
     * @param list
     * @return
     */
    private static TreeNode rootLastDeserializeBinaryTree(List<Integer> list){
        if(list.isEmpty()){
            return null;
        }
        Integer val = list.remove(0);
        if(val == null){
            return null;
        }
        TreeNode head = new TreeNode(val);
        head.right = rootLastDeserializeBinaryTree(list);
        head.left = rootLastDeserializeBinaryTree(list);
        return head;
    }
}
