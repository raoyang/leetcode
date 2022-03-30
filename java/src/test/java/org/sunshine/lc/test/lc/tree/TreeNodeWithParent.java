package org.sunshine.lc.test.lc.tree;

public class TreeNodeWithParent {
    public TreeNodeWithParent parent; //父节点
    public TreeNodeWithParent left; //左
    public TreeNodeWithParent right; //右
    public int val;

    public TreeNodeWithParent() {
    }

    public TreeNodeWithParent(int val) {
        this.val = val;
    }

    public TreeNodeWithParent(int val, TreeNodeWithParent parent, TreeNodeWithParent left, TreeNodeWithParent right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
