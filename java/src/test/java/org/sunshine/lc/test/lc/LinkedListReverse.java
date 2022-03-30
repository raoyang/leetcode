package org.sunshine.lc.test.lc;

/***
 * 链表反转
 */
public class LinkedListReverse {

    public static void main(String args[]){
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        ListNode ln = reverse(head);
        System.out.print(ln);
    }

    /***
     * 遍历链表
     * 指针挨个的反过来指
     * @param head
     * @return
     */
    private static ListNode reverse(ListNode head){
        ListNode pre = null;
        ListNode cur = head;
        while(cur != null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
