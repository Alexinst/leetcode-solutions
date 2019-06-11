/*
给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。

如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。

您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

示例：
	输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
	输出：7 -> 0 -> 8
	原因：342 + 465 = 807
*/

class ListNode {
	int val;
	ListNode next;

	ListNode(x) { val = x; }
}

class MySolution {
    private ListNode cur;
    private ListNode head;
    
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        add(l1, l2, 0);        
        return head;
    }

    private void add(ListNode l1, ListNode l2, int carry) {
        if (l1 == null && l2 == null && carry == 0) return;
        
        if (l1 == null) l1 = new ListNode(0);
        if (l2 == null) l2 = new ListNode(0);
        int n = l1.val + l2.val + carry;
        ListNode node = new ListNode(n % 10);
        if (cur == null) {
            cur = node;
            head = node;
        }
        else {
            cur.next = node;
            cur = node;
        }
        
        carry = n - 10 >= 0 ? 1 : 0;
        add(l1.next, l2.next, carry);
    }
}

class Solution1 {
   public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        helper(dummy, l1, l2, 0);
        return dummy.next;
    }

    private void helper(ListNode curr, ListNode l1, ListNode l2, int carry) {
        if (l1 == null && l2 == null) {
            if (carry > 0) {
                curr.next = new ListNode(carry);
            }
            return;
        }
        //链表长度不一样的情况
        if (l1 != null) {
            carry += l1.val;
            l1 = l1.next;
        }
        if (l2 != null) {
            carry += l2.val;
            l2 = l2.next;
        }
        //进位
        curr.next = new ListNode(carry % 10);
        helper(curr.next, l1, l2, carry / 10);
    }
}
