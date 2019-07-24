/*

https://leetcode-cn.com/problems/palindrome-linked-list/

请判断一个链表是否为回文链表。

示例 1:
	输入: 1->2
	输出: false

示例 2:
	输入: 1->2->2->1
	输出: true

进阶：
	你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？

-----------------------------------------------------------------

Given a singly linked list, determine if it is a palindrome.

Example 1:
	Input: 1->2
	Output: false

Example 2:
	Input: 1->2->2->1
	Output: true

Follow up:
	Could you do it in O(n) time and O(1) space?

*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

/**
 * 将链表前半段反转方向，再与后半段进行对比
 */
class MySolution {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null)
            return true;
        
       	ListNode fast = head, slow = head, pre = null;
        boolean isOdd = false;      // 链表长度是否为奇数
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            
            ListNode tmp = slow.next;
            slow.next = pre;
            pre = slow;
            slow = tmp;
            
            if (fast != null && fast.next == null) {
                isOdd = true;
            }
        }
        
        if (isOdd)
            slow = slow.next;
        
        // slow 代表后半段，pre 代表逆转的前半段
        while (slow != null) {
            if (slow.val != pre.val)
                return false;
            else {
                slow = slow.next;
                pre = pre.next;
            }
        }
        
        return true;
    }
}

// 如果链表长度 <=1，则直接返回 true
// 先找到链表的中点（快慢指针）
// 再以中点为起点，将后面链表翻转（迭代、遍历）
// 最后分别以头结点和中点为起点，向后遍历，比较值是否相等，如果出现不等，则不是回文链表
class Solution1 {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        // 找到链表的中点
        ListNode mid = findMid(head);
        // 翻转中点后的链表
        mid = reverseList(mid);
        // 比较两段链表
        while (mid != null) {
            if (head.val != mid.val) {
                return false;
            }
            head = head.next;
            mid = mid.next;
        }
        return true;
    }
    // 翻转链表（迭代、递归）
    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        return prev;
    }
    
    // 找到链表的中间节点    
    private ListNode findMid(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}

class Solution2 {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        if (head.next.next == null) {
            return head.val == head.next.val;
        }

        ListNode fast = head.next;
        ListNode slow = head;

        while (fast.next != null) {
            // 不停的从slow的后一个开始遍历，知道找到值相同的节点
            // 一次完成后，再移动到原节点的下一个节点开始，继续重复上面的步骤
            if (fast.next.val == slow.val) {
                if (fast.next.next != null) {
                    return false;
                }
                fast.next = null;
                slow = slow.next;
                fast = slow.next;
                if (fast == null || fast.val == slow.val) {
                    return true;
                }
            } else {
                fast = fast.next;
            }
        }
        return false;
    }
}
