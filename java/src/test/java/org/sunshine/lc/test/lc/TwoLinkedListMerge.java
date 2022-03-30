package org.sunshine.lc.test.lc;

/***
 * 两个有序链表合并
 */
public class TwoLinkedListMerge {

    public static void main(String args[]){
        ListNode L1 = new ListNode(1);
        L1.next = new ListNode(3);
        L1.next.next = new ListNode(5);

        ListNode L2 = new ListNode(2);
        L2.next = new ListNode(4);
        L2.next.next = new ListNode(6);

        ListNode node = merge(L1, L2);
        System.out.print(node);
    }

    /***
     * 两个有序链表合并
     * 采用动态规划的思想： L1和L2合并，相当于从L1和L2的头节点，拿出最小的值，然后让剩下的两个链表合并
     * @param L1
     * @param L2
     * @return
     */
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

    public static class ListNode {
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
