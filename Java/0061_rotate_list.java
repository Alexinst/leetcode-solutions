/*
 * https://leetcode-cn.com/problems/rotate-list/

给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。

示例 1:
	输入: 1->2->3->4->5->NULL, k = 2
	输出: 4->5->1->2->3->NULL
	解释: 向右旋转 1 步: 5->1->2->3->4->NULL
	      向右旋转 2 步: 4->5->1->2->3->NULL

示例 2:
	输入: 0->1->2->NULL, k = 4
	输出: 2->0->1->NULL
	解释: 向右旋转 1 步: 2->0->1->NULL
	      向右旋转 2 步: 1->2->0->NULL
	      向右旋转 3 步: 0->1->2->NULL
	      向右旋转 4 步: 2->0->1->NULL

---------------------------------------------------------------------------

Given a linked list, rotate the list to the right by k places, where k is non-negative.

Example 1:
	Input: 1->2->3->4->5->NULL, k = 2
	Output: 4->5->1->2->3->NULL
	Explanation: rotate 1 steps to the right: 5->1->2->3->4->NULL
	             rotate 2 steps to the right: 4->5->1->2->3->NULL

Example 2:
	Input: 0->1->2->NULL, k = 4
	Output: 2->0->1->NULL
	Explanation: rotate 1 steps to the right: 2->0->1->NULL
	             rotate 2 steps to the right: 1->2->0->NULL
	             rotate 3 steps to the right: 0->1->2->NULL
	             rotate 4 steps to the right: 2->0->1->NULL
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
    public ListNode rotateRight(ListNode head, int k) {
        if (k == 0 || head == null || head.next == null)
            return head;
        
        // 计算链表长度，并将其变为环形链表
        ListNode cur = head;
        int len = 0;
        while (cur.next != null) {
            len++;
            cur = cur.next;
        }
        len++;
        if (k % len == 0) return head;  // 若旋转步数 k 为链表长度的倍数
        cur.next = head;
        
        k = len - k % len;
        cur = head;
        while (k-- > 1) {
            cur = cur.next;
        }
        head = cur.next;
        cur.next = null;    // 断开环形
        
        return head;
    }
}

class Solution1 {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0) return head;
        int len = 0;
        ListNode p = head;
        //统计得到链表的长度
        while (p != null){
            len++;
            p = p.next;
        }
        k = k % len;
        if (k == 0) return head;
        ListNode prev = head;
        for (int i = 0; i < len - k - 1; i++){
            prev = prev.next;
        }

        p = prev.next;
        ListNode q = p;
        while (q.next != null) {
            q = q.next;
        }

        q.next = head;
        prev.next = null;

        return p;
    }
}
