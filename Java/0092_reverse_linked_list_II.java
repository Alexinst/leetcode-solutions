/*
 *

反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。

说明:
	1 ≤ m ≤ n ≤ 链表长度。

示例:
	输入: 1->2->3->4->5->NULL, m = 2, n = 4
	输出: 1->4->3->2->5->NULL

----------------------------------------------------------

Reverse a linked list from position m to n. Do it in one-pass.

Note: 1 ≤ m ≤ n ≤ length of list.

Example:
	Input: 1->2->3->4->5->NULL, m = 2, n = 4
	Output: 1->4->3->2->5->NULL
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

    private ListNode next = null;

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (m == n) return head;

        ListNode pre = null, left = head, next = null;

        for (int i = 0; i < m - 1; i++) {
            pre = left;
            left = left.next;
        }

        ListNode right = reverse(left, n - m + 1);

        if (m == 1)
            head = right;
        else
            pre.next = right;
        left.next = this.next;

        return head;

    }

    private ListNode reverse(ListNode head, int len) {
        ListNode pre = null;
        while (len > 0) {
            ListNode tmp = head.next;
            head.next = pre;
            pre = head;
            head = tmp;
            len--;
        }
        next = head;

        return pre;
    }
}


