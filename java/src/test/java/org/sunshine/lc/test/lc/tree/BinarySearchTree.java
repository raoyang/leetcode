package org.sunshine.lc.test.lc.tree;

import org.sunshine.lc.test.lc.TreeNode;

public class BinarySearchTree {

    public static void main(String args[]){
        TreeNode root = new TreeNode(Integer.MAX_VALUE);
        //root.left = new TreeNode(6);
        //root.right = new TreeNode(20);
        ReturnType returnType = isValidBST(root);

        System.out.println(returnType);
    }

    /***
     * 动态规划思路： 判断左子树是不是二叉搜索树，判断右子树是不是二叉搜索树
     * @param root
     * @return
     */
    private static ReturnType isValidBST(TreeNode root) {
        if(root == null){
            return null;
        }
        ReturnType leftType = isValidBST(root.left);
        ReturnType rightType = isValidBST(root.right);
        if(leftType == null){
            leftType = new ReturnType(true, Long.MIN_VALUE, Long.MAX_VALUE);
        }
        if(rightType == null){
            rightType = new ReturnType(true, Long.MIN_VALUE, Long.MAX_VALUE);
        }
        boolean isBst = false;
        long max = Long.MIN_VALUE;
        long min = Long.MAX_VALUE;
        isBst = leftType.isBST && rightType.isBST && root.val > leftType.max && root.val < rightType.min;
        max = Math.max(rightType.max, root.val);
        min = Math.min(leftType.min, root.val);
        return new ReturnType(isBst, max, min);
    }

    private static class ReturnType{
        private boolean isBST;
        private long max;
        private long min;

        public ReturnType(boolean isBst, long max, long min){
            this.isBST = isBst;
            this.max = max;
            this.min = min;
        }

        public boolean isBST() {
            return isBST;
        }

        public void setBST(boolean BST) {
            isBST = BST;
        }

        public long getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public long getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }
    }
}
