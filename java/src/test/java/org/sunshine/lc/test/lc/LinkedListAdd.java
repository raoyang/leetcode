package org.sunshine.lc.test.lc;

public class LinkedListAdd {

    public static void main(String args[]){
        ListNode a = getNode(new int[]{5,5,6,7,8});
        ListNode b = getNode(new int[]{7,7,8,9,1,3,5});

        ListNode result = LinkedListAdd(a, b);
        System.out.print(result);
    }

    private static ListNode getNode(int arr[]){
        ListNode head = new ListNode(arr[0]);
        ListNode cur = head;
        for(int i = 1 ; i < arr.length ; i ++){
            ListNode node = new ListNode(arr[i]);
            cur.next = node;
            cur = node;
        }
        return head;
    }

    private static ListNode LinkedListAdd(ListNode a, ListNode b){
        if(a == null){
            return b;
        }
        if(b == null){
            return a;
        }

        int overValue = (a.val + b.val) / 10;
        ListNode head = new ListNode((a.val + b.val) % 10);
        ListNode cur = head;

        ListNode curA = a.next;
        ListNode curB = b.next;
        while(true){
            if(curA == null && curB == null){
                if(overValue > 0){
                    cur.next = new ListNode(overValue);
                }
                break;
            }
            int v1 = curA == null ? 0 : curA.val;
            int v2 = curB == null ? 0 : curB.val;
            int val = (v1 + v2 + overValue) % 10;
            ListNode node = new ListNode(val);
            cur.next = node;
            cur = node;
            overValue = (v1 + v2 + overValue) / 10;
            curA = curA == null ? null : curA.next;
            curB = curB == null ? null : curB.next;
        }
        return head;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {}

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
