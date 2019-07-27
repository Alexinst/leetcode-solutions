/*
 * https://leetcode-cn.com/problems/odd-even-linked-list/

给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。

示例 1:
	输入: 1->2->3->4->5->NULL
	输出: 1->3->5->2->4->NULL

示例 2:
	输入: 2->1->3->5->6->4->7->NULL 
	输出: 2->3->6->7->1->5->4->NULL

说明:
1. 应当保持奇数节点和偶数节点的相对顺序。
2. 链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。

-------------------------------------------------------------------------------------------------------------------------------

Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.
You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.

Example 1:
	Input: 1->2->3->4->5->NULL
	Output: 1->3->5->2->4->NULL

Example 2:
	Input: 2->1->3->5->6->4->7->NULL
	Output: 2->3->6->7->1->5->4->NULL

Note:
    1. The relative order inside both the even and odd groups should remain as it was in the input.
    2. The first node is considered odd, the second node even and so on ...
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
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        ListNode odd = new ListNode(0);
        ListNode even = new ListNode(0);
        ListNode oddNode = odd, evenNode = even;
        
        int i = 1;
        while (head != null) {
            if (i % 2 == 1) {
                oddNode.next = head;
                oddNode = oddNode.next;
            }
            else {
                evenNode.next = head;
                evenNode = evenNode.next;
            }
            head = head.next;
            i++;
        }
        
        oddNode.next = even.next;
        evenNode.next = null;
        
        return odd.next;
    }
}

class Solution1 {
    public ListNode oddEvenList(ListNode head) {
        if(head == null){
            return null;
        }
        /*
        head 奇数节点链表头
        odd  奇数节点链表尾
        evenHead 偶数节点链表头
        even 偶数节点链表尾
        */
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;
        while(even!=null && even.next!=null){
            odd.next = even.next;
            odd = odd.next;
            
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
}
