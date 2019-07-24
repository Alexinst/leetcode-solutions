/*
 * https://leetcode-cn.com/problems/add-two-numbers-ii/submissions/

给定两个非空链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储单个数字。将这两数相加会返回一个新的链表。

你可以假设除了数字 0 之外，这两个数字都不会以零开头。

进阶:
	如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。

示例:
	输入: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
	输出: 7 -> 8 -> 0 -> 7

-------------------------------------------------------------------------------------------------

You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Follow up:
	What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

Example:
	Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
	Output: 7 -> 8 -> 0 -> 7
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 逆转链表
        l1 = reverse(l1);
        l2 = reverse(l2);
        
        ListNode res = new ListNode(0);
        ListNode cur = res;
        int carry = 0;  // 进位
        while (l1 != null || l2 != null || carry == 1) {
            if (l1 == null) l1 = new ListNode(0);
            if (l2 == null) l2 = new ListNode(0);
            
            cur.next = new ListNode((l1.val + l2.val + carry) % 10);
            carry = (l1.val + l2.val + carry) / 10;
            
            l1 = l1.next;
            l2 = l2.next;
            cur = cur.next;
        }
        
        return reverse(res.next);
    }
    
    private ListNode reverse(ListNode node) {
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

class Solution1 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    Deque<Integer> l1Stack = new ArrayDeque<>();
    Deque<Integer> l2Stack = new ArrayDeque<>();

    while (l1 != null) {
      l1Stack.push(l1.val);
      l1 = l1.next;
    }
    while (l2 != null) {
      l2Stack.push(l2.val);
      l2 = l2.next;
    }

    ListNode head = new ListNode(-1);
    int carry = 0;

    while (!l1Stack.isEmpty() || !l2Stack.isEmpty() || carry != 0) {
      int x = l1Stack.isEmpty() ? 0 : l1Stack.pop();
      int y = l2Stack.isEmpty() ? 0 : l2Stack.pop();

      int sum = x + y + carry;
      ListNode node = new ListNode(sum % 10);

      // 头插法
      node.next = head.next;
      head.next = node;
      carry = sum / 10;
    }

    return head.next;
  }
}
