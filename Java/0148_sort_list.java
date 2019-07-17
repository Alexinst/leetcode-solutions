/*
在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。

示例 1:
	输入: 4->2->1->3
	输出: 1->2->3->4

示例 2:
	输入: -1->5->3->4->0
	输出: -1->0->3->4->5

-----------------------------------------------------------------------

Sort a linked list in O(n log n) time using constant space complexity.

Example 1:
	Input: 4->2->1->3
	Output: 1->2->3->4

Example 2:
	Input: -1->5->3->4->0
	Output: -1->0->3->4->5
*/

class Solution1 {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        
        ListNode fast = head, slow = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode right = slow.next;
        slow.next = null;
        ListNode l = sortList(head);
        ListNode r = sortList(right);
        
        return merge(l, r);
    }
    
    private ListNode merge(ListNode l, ListNode r) {
        ListNode res = new ListNode(0), node = res;
        while (l != null && r != null) {
            if (l.val <= r.val) {
                node.next = l;
                l = l.next;
            }
            else {
                node.next = r;
                r = r.next;
            }
            node = node.next;
        }
        
        while (l != null) {
            node.next = l;
            l = l.next;
            node = node.next;
        }
        
        while (r != null) {
            node.next = r;
            r = r.next;
            node = node.next;
        }
        
        return res.next;
    }
}
