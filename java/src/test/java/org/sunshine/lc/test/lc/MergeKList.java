package org.sunshine.lc.test.lc;

import java.util.Arrays;

/***
 *
 */
public class MergeKList {

    public static void main(String args[]){

    }

    /***
     * 另外一种合并方法
     * @param lists
     * @return
     */
    public static ListNode mergeKLists2(ListNode[] lists) {
        if(lists.length == 0){
            return null;
        }
        if(lists.length == 1){
            return lists[0];
        }
        int size = (lists.length + 1) / 2;
        ListNode[] temp = new ListNode[size];
        int i = 0; int k = lists.length - 1;
        while(i < k){
            temp[i] = merge(lists[i], lists[k]);
            i++;
            k--;
        }
        if(lists.length % 2 != 0){
            temp[i] = lists[i];
        }
        return mergeKLists2(temp);
    }

    /***
     * 一个个的去合并
     * @param lists
     * @return
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0){
            return null;
        }
        if(lists.length == 1){
            return lists[0];
        }
        ListNode listNode = lists[0];
        for(int i = 1 ; i < lists.length ; i ++){
            listNode = merge(listNode, lists[i]);
        }
        return listNode;
    }

    public static ListNode merge(ListNode L1, ListNode L2){
        if(L1 == null){
            return L2;
        }
        if(L2 == null){
            return L1;
        }
        if(L1.val < L2.val){
            L1.next = merge(L1.next, L2);
            return L1;
        }else {
            L2.next = merge(L1, L2.next);
            return L2;
        }
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
