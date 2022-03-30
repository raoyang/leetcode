package org.sunshine.lc.test.lc.tree;

/***
 * 折纸
 */
public class FoldPaper {

    public static void main(String args[]){
        printFoldPaperInfo(4);
    }

    /***
     * 打印折纸之后的信息
     * @param level 对折的次数
     */
    private static void printFoldPaperInfo(int level){
        print(1, level, "凹"); //根节点是凹
    }

    /***
     *
     * @param i 当前第几层
     * @param level 总共层数
     * @param ao
     */
    private static void print(int i, int level, String ao){
        if(i > level){
            return;
        }
        print(i+1, level, "凹"); //左子树的根节点是凹
        System.out.println(ao);
        print(i+1, level, "凸"); //右子树的根节点是凸
    }
}
