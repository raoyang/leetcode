package org.sunshine.lc.test.lc.tree;

/***
 * 找二叉树的后继节点
 * 后继节点定义：中序遍历的下一个节点
 */
public class BinaryTreeLastNode {

    //方法一：中序遍历二叉树，然后匹配遍历结果中的节点，找到下一个

    //进阶：假如二叉树中，给一个属性指向父节点，怎么快速的找到一个节点的后继
    public static void main(String args[]){

    }

    /***
     * 思路：1. 如果node节点，它的右孩子不为空，则后继为又孩子的最左非空节点
     *      2. 如果node节点没有右孩子，从node开始，递归找父节点，如果node是父节点的右孩子，继续递归，直到找到的node节点不是父节点的右孩子，此刻父节点即为后继
     * @param node
     * @return
     */
    public static TreeNodeWithParent getTreeNodeLast(TreeNodeWithParent node){
        if(node.right != null){
            TreeNodeWithParent cur = node.right;
            TreeNodeWithParent res = null;
            while(cur != null){
                res = cur;
                cur = cur.left;
            }
            return res;
        }else{
            TreeNodeWithParent parent = node.parent;
            while(parent != null && parent.right == node){
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }
}
