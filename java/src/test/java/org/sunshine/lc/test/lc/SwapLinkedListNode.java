package org.sunshine.lc.test.lc;

/***
 * 两两交换链表中的相邻节点节点
 * 比如链表 1 -> 2 -> 3 -> 4
 * 交换之后 2 -> 1 -> 4 -> 3
 */
public class SwapLinkedListNode {

    public static void main(String args[]){
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        ListNode result = swapPairs(head);
        System.out.print(result);
    }

    private static ListNode swapPairs(ListNode head) {
        if(head == null){
            return null;
        }
        if(head.next == null){
            return head;
        }
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode temp = pre;
        while(temp.next != null && temp.next.next != null){
            ListNode L1 = temp.next;
            ListNode L2 = temp.next.next;
            temp.next = L2;
            L1.next = L2.next;
            L2.next = L1;
            temp = L1;
        }
        return pre.next;
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
