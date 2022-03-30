package org.sunshine.lc.test.lc;

import java.util.Stack;

/***
 * 给你一个链表，每k个节点一组进行翻转，请你返回翻转后的链表。
 *
 * k是一个正整数，它的值小于或等于链表的长度。
 *
 * 如果节点总数不是k的整数倍，那么请将最后剩余的节点保持原有顺序。
 */
public class ReverseKCountLinkedListGroup {

    public static void main(String args[]){
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        //ListNode node = reverseKGroup(head, 3);
        //System.out.print(node);

        ListNode node = reverse(head);
        System.out.print(node);
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        if(head == null){
            return null;
        }
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode temp = pre;
        int count = k;
        while(count > 0 && temp != null){
            temp = temp.next;
            count--;
        }
        if(temp == null){
            return pre.next;
        }

        ListNode h1 = temp.next;
        temp.next = null;

        ListNode re = reverseListNode(pre.next);
        ListNode tre = re;
        while(tre != null && tre.next!= null){
            tre = tre.next;
        }
        tre.next = reverseKGroup(h1, k);
        return re;
    }


    /***
     * 单链表反转
     * @param head 链表头节点
     * @return
     */
    public static ListNode reverseListNode(ListNode head){
        if(head == null){
            return null;
        }
        Stack<ListNode> stack = new Stack<>();
        ListNode temp = head;
        while(temp != null){
            stack.push(temp);
            temp = temp.next;
        }
        ListNode reverseHead = stack.pop();
        ListNode pre = reverseHead;
        while(!stack.isEmpty()){
            ListNode node = stack.pop();
            node.next = null;
            pre.next = node;
            pre = node;
        }
        return reverseHead;
    }

    private static ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }
}
