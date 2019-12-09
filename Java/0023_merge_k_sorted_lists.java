/*
 * https://leetcode-cn.com/problems/merge-k-sorted-lists/ 
 
合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。

示例:
	输入:
	[
	  1->4->5,
	  1->3->4,
	  2->6
	]
	输出: 1->1->2->3->4->4->5->6

----------------------------------------------------------------------------------------------------

Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

Example:
	Input:
	[
	  1->4->5,
	  1->3->4,
	  2->6
	]
	Output: 1->1->2->3->4->4->5->6
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
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;

        while (lists.length > 1) {
            ListNode[] newLists = new ListNode[(lists.length + 1) / 2];

            for (int i = 0; i < lists.length; i += 2) {
                if (i + 1 < lists.length) {
                    newLists[(i + 1) / 2] = merge2Lists(lists[i], lists[i + 1]);
                }
            }
            
            if (newLists[newLists.length - 1] == null)
                newLists[newLists.length - 1] = lists[lists.length - 1];
            
            lists = newLists;
        }

        return lists[0];
    }

    private ListNode merge2Lists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode node = head;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                node.next = l1;
                l1 = l1.next;
            } else {
                node.next = l2;
                l2 = l2.next;
            }

            node = node.next;
        }

        while (l1 != null) {
            node.next = l1;
            l1 = l1.next;
            node = node.next;
        }

        while (l2 != null) {
            node.next = l2;
            l2 = l2.next;
            node = node.next;
        }

        return head.next;
    }
}

class Solution1 {
    public ListNode mergeKLists(ListNode[] lists) {
        int len = lists.length;
        if (len == 0) {
            return null;
        }
        while (len > 1) {
            for (int i = 0; i < len / 2; i++) {
                lists[i] = mergeTwo(lists[i], lists[len - i - 1]);
            }
            len = (len + 1) / 2;
        }
        return lists[0];
    }

    private ListNode mergeTwo(ListNode left, ListNode right) {
        ListNode head = new ListNode(-1);
        ListNode index = head;
        
        while (left != null && right != null) {
            if (left.val < right.val) {
                index.next = left;
                left = left.next;
            } else {
                index.next = right;
                right = right.next;
            }
            index = index.next;
        }

        if (left != null) {
            index.next = left;
        } else if (right != null) {
            index.next = right;
        }

        return head.next;
    }
}
