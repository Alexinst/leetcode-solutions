/*

https://leetcode-cn.com/problems/partition-list/
   
给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
你应当保留两个分区中每个节点的初始相对位置。

示例:
	输入: head = 1->4->3->2->5->2, x =
	输出: 1->2->2->4->3-

-----------------------------------------------------------------------------------------------

Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
You should preserve the original relative order of the nodes in each of the two partitions.

Example:
	Input: head = 1->4->3->2->5->2, x = 3
	Output: 1->2->2->4->3->5
*/

// 遍历链表，分成两子链表，一条的全部节点小于x，另一条的全部节点大于或等于x
// 第一条链表的末尾连接第二条链表的首部
class MySolution {
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null)
            return head;
        
        ListNode leftHead = new ListNode(0);    // 全部节点小于 x
        ListNode rightHead = new ListNode(0);   // 全部节点大于或等于 x
        ListNode left = leftHead, right = rightHead;    // 链表指针

        while (head != null) {
            if (head.val < x) {
                left.next = head;
                left = left.next;
            }
            else {
                right.next = head;
                right = right.next;
            }
            head = head.next;
        }
        left.next = rightHead.next;     // 第一条链表的末尾连接第二条链表的首部
        right.next = null;
        
        return leftHead.next;
    }
}


