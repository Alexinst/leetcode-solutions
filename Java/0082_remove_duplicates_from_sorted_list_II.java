/*

https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/

给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。

示例 1:
	输入: 1->2->3->3->4->4->5
	输出: 1->2->5

示例 2:
	输入: 1->1->1->2->3
	输出: 2->3

------------------------------------------------------------------------------------

Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

Example 1:
	Input: 1->2->3->3->4->4->5
	Output: 1->2->5

Example 2:
	Input: 1->1->1->2->3
	Output: 2->3

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
        boolean isRepeated = false;
        while (head != null) {
            if (head.next != null) {    // head 并非末尾节点
                if (head.val == head.next.val) {
                    head.next = head.next.next;
                    isRepeated = true;
                } 
				else {
                    if (isRepeated) {
                        last.next = head.next;
                        isRepeated = false;
                    }
                    else
                        last = head;

                    head = head.next;
                }
            }
            else {
                if (isRepeated)
                    last.next = null;
                head = head.next;
            }
        }

        return dummy.next;
    }
}

class Solution1 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode dH = new ListNode(-1);
        ListNode cur = head, pre = dH;
        dH.next = head;
        while (cur != null) {
            if (cur.next != null && cur.val == cur.next.val) {
                while (cur.next != null && cur.val == cur.next.val)
                    cur = cur.next;

                pre.next = cur.next;
            }
            else {
                pre = pre.next;
			}
			cur = cur.next;
        }

        return dH.next;
    }
}
