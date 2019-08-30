/*
 * https://leetcode-cn.com/problems/reverse-nodes-in-k-group/

给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。

k 是一个正整数，它的值小于或等于链表的长度。

如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。

示例 :
	给定这个链表：1->2->3->4->5

	当 k = 2 时，应当返回: 2->1->4->3->5

	当 k = 3 时，应当返回: 3->2->1->4->5

	说明 :
		1. 你的算法只能使用常数的额外空间。
		2. 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

--------------------------------------------------------------------------------------------------------

Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

Example:
	Given this linked list: 1->2->3->4->5

	For k = 2, you should return: 2->1->4->3->5

	For k = 3, you should return: 3->2->1->4->5

	Note:
		1. Only constant extra memory is allowed.
		2. You may not alter the values in the list's nodes, only nodes itself may be changed.
*/

/**思路：
 * 1. 方法：递归
 * 2. 先确定链表剩余长度是否大于等于 k，否则直接返回 head
 * 3. 翻转 k 个节点。剩余链表的原首节点（pastHead）变为第 k 个， 原第 k 个节点 变为首节点（head）
 * 4. 对 首节点为第 k + 1 个节点（nextHead）的新链表继续进行翻转。
 */
class MySolution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode nextHead = head;
        for (int i = 0; i < k; i++) {
            if (nextHead == null) return head;

            nextHead = nextHead.next;
        }

        ListNode pastHead = head;
        head = reverse(head, k);
        pastHead.next = reverseKGroup(nextHead, k);

        return head;
    }

    private ListNode reverse(ListNode head, int k) {
        ListNode last = null;
        while (k-- > 1) {
            ListNode tmp = head;
            head = head.next;
            tmp.next = last;
            last = tmp;
        }

        return last;
    }
}

class Solution1 {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 0 || k == 1) return head;

        ListNode nextHead = head;
        for (int i = 0; i < k; i ++) {
            if (nextHead == null) {// 最后剩余的节点保持原有顺序
                return head;
            }
            nextHead = nextHead.next;
        }

        ListNode end = head;// 翻转后，head即为end
        // 翻转链表
        ListNode preNode = null;
        for (int i = 0; i < k; i ++) {
            ListNode next = head.next;
            head.next = preNode;
            preNode = head;
            head = next;
        }

        end.next = reverseKGroup(nextHead, k);// 递归后面的链表

        return preNode;
    }
}
