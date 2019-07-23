/*

https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/

给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。

示例 1:
	输入: 1->1->2
	输出: 1->2

示例 2:
	输入: 1->1->2->3->3
	输出: 1->2->3

---------------------------------------------------------------------

Given a sorted linked list, delete all duplicates such that each element appear only once.

Example 1:
	Input: 1->1->2
	Output: 1->2

Example 2:
	Input: 1->1->2->3->3
	Output: 1->2->3
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
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode last = dummy;
        while (head != null) {
            if (head.next != null && head.val == head.next.val) 
                last.next = head.next;
            else 
                last = head;
            
            head = head.next;
        }
        
        return dummy.next;
    }
}


class Solution1 {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode cur = head;
        while (cur!=null && cur.next != null) {
            if (cur.val == cur.next.val)
                cur.next = cur.next.next;
            else 
                cur = cur.next;
        }
        
        return head;
    }
}
