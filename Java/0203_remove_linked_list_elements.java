/*
删除链表中等于给定值 val 的所有节点。

示例:
	输入: 1->2->6->3->4->5->6, val = 6
	输出: 1->2->3->4->5

------------------------------------------------------------

Remove all elements from a linked list of integers that have value val.

Example:
	Input:  1->2->6->3->4->5->6, val = 6
	Output: 1->2->3->4->5
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
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) return head;
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode cur = head, last = dummy;
        while (cur != null) {
            if (cur.val == val) 
                last.next = cur.next;
            else
                last = cur;
            
            cur = cur.next;
        }
        
        return dummy.next;
    }
}
