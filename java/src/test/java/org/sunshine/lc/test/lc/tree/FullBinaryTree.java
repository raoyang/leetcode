package org.sunshine.lc.test.lc.tree;

import org.sunshine.lc.test.lc.TreeNode;

/***
 * 满二叉树
 */
public class FullBinaryTree {

    public static void main(String args[]){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        //root.right = new TreeNode(3);
        System.out.println(isFullBinaryTree(root));
    }

    private static boolean isFullBinaryTree(TreeNode root){
        ReturnType returnType = binaryTreeCount(root);
        return returnType.nodes == (returnType.high << 1) - 1;
    }

    /***
     * 计算二叉树的节点个数
     * @param node
     * @return
     */
    private static ReturnType binaryTreeCount(TreeNode node){
        if(node == null){
            return new ReturnType(0, 0);
        }
        ReturnType left = binaryTreeCount(node.left);
        ReturnType right = binaryTreeCount(node.right);
        int high = Math.max(left.high, right.high) + 1;
        int nodes = left.nodes + right.nodes + 1;
        return new ReturnType(high, nodes);
    }

    private static class ReturnType{
        private int high;
        private int nodes;
        public ReturnType(int high, int nodes){
            this.high = high;
            this.nodes = nodes;
        }

        public int getHigh() {
            return high;
        }

        public void setHigh(int high) {
            this.high = high;
        }

        public int getNodes() {
            return nodes;
        }

        public void setNodes(int nodes) {
            this.nodes = nodes;
        }
    }
}
