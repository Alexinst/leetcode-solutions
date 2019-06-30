/*
给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。

你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

示例:
	给定 1->2->3->4, 你应该返回 2->1->4->3.
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class MySolution {
    public ListNode swapPairs(ListNode head) {
        ListNode ret = head;
        ListNode cur = head;
        ListNode last = null;
        int count = 0;
        while (cur != null && cur.next != null) {
            ListNode next = cur.next;
            ListNode third = next.next;
            
            if (count == 0) 
                ret = next;
            else
                last.next = next;
            next.next = cur;
            cur.next = third;
            last = cur;
            cur = third;
            count++;
        }
        
        return ret;
    }
}

class Solution1 {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode n = head.next;
        head.next = swapPairs(head.next.next);
        n.next = head;
        return n;
    }
}
