/*
将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 

示例：
	输入：1->2->4, 1->3->4
	输出：1->1->2->3->4->4
*/

class MySolution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 != null) return l2;
        if (l1 != null && l2 == null) return l1;

        ListNode ptr = null, res = null;
        int counter = 0;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                if (counter == 0) ptr = l1;
                else ptr.next = l1;

                l1 = l1.next;
            }
            else {
                if (counter == 0) ptr = l2;
                else ptr.next = l2;

                l2 = l2.next;
            }
            
            if (counter == 0) res = ptr;
            else ptr = ptr.next;
            counter++;
        }

        if (ptr != null)
            ptr.next = l1 == null ? l2 : l1;

        return res;
    }
}

class Solution1 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // maintain an unchanging reference to node ahead of the return node.
        ListNode prehead = new ListNode(-1);

        ListNode prev = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }

        // exactly one of l1 and l2 can be non-null at this point, so connect
        // the non-null list to the end of the merged list.
        prev.next = l1 == null ? l2 : l1;

        return prehead.next;
    }
}
