/*
 * https://leetcode-cn.com/problems/reorder-list/

给定一个单L0 -> L1 -> ... -> Ln-1 -> Ln，
将其重新排列后Ln→L1→Ln-1→L2→Ln-2→…

你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

示例 1:
	给定链表 1->2->3->4, 重新排列为 1->4->2->3.
	
示例 2:
	给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.

-----------------------------------------------------------------------------------------

Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You may not modify the values in the list's nodes, only nodes itself may be changed.

Example 1:
	Given 1->2->3->4, reorder it to 1->4->2->3.

Example 2:
	Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
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
    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null)
            return;
        
        ListNode fast = head, slow = head, pre = null;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // 分成左段子链表和右段子链表；且将右段子链表逆转顺序
        ListNode tmp = slow.next;
        slow.next = null;
        ListNode left = head;
        ListNode right = reverseList(tmp);
        
        // 合并两段子链表
        while (right != null) {
            ListNode tmp1 = left.next, tmp2 = right.next;
            left.next = right;
            right.next = tmp1;
            left = tmp1;
            right = tmp2;
        }
    }
    
    private ListNode reverseList(ListNode node) {
        ListNode pre = null;
        while (node != null) {
            ListNode tmp = node.next;
            node.next = pre;
            pre = node;
            node = tmp;
        }
        
        return pre;
    }
}
