/*
反转一个单链表。

示例:
	输入: 1->2->3->4->5->NULL
	输出: 5->4->3->2->1->NULL

进阶:
	你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// 迭代版本
class MySolution {
    public ListNode reverseList(ListNode head) {
        ListNode last = null, next = null;
        while (head != null) {
            next = head.next;  // 原序的下一节点
            head.next = last;
            last = head;
            head = next;
        }
        
        return last;
    }
}

// 递归版本
class MySolution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        
        head = reverse(head, null);
        return head;
    }
    
    private ListNode reverse(ListNode head, ListNode last) {
        if (head == null) return last;
        
        ListNode next = head.next;
        head.next = last;
        last = head;
        head = next;
        
        return reverse(head, last);
    }
}
