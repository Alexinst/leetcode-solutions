/*
 *

给定一个链表（链表结点包含一个整型值）的头结点 head。
同时给定列表 G，该列表是上述链表中整型值的一个子集。
返回列表 G 中组件的个数，这里对组件的定义为：链表中一段最长连续结点的值（该值必须在列表 G 中）构成的集合。

示例 1：
	输入: head: 0->1->2->3 G = [0, 1, 3]
	输出: 2
	解释: 链表中,0 和 1 是相连接的，且 G 中不包含 2，所以 [0, 1] 是 G 的一个组件，同理 [3] 也是一个组件，故返回 2。

示例 2：
	输入: head: 0->1->2->3->4 G = [0, 3, 1, 4]
	输出: 2
	解释: 链表中，0 和 1 是相连接的，3 和 4 是相连接的，所以 [0, 1] 和 [3, 4] 是两个组件，故返回 2。

注意:
1. 如果 N 是给定链表 head 的长度，1 <= N <= 10000。
2. 链表中每个结点的值所在范围为 [0, N - 1]。
3. 1 <= G.length <= 10000
4. G 是链表中所有结点的值的一个子集.

-------------------------------------------------------------------------------------------------

We are given head, the head node of a linked list containing unique integer values.
We are also given the list G, a subset of the values in the linked list.
Return the number of connected components in G, where two values are connected if they appear consecutively in the linked list.

Example 1:
	Input: head: 0->1->2->3 G = [0, 1, 3]
	Output: 2
	Explanation: 0 and 1 are connected, so [0, 1] and [3] are the two connected components.

Example 2:
	Input: head: 0->1->2->3->4 G = [0, 3, 1, 4]
	Output: 2
	Explanation: 0 and 1 are connected, 3 and 4 are connected, so [0, 1] and [3, 4] are the two connected components.

Note:
1. If N is the length of the linked list given by head, 1 <= N <= 10000.
2. The value of each node in the linked list will be in the range [0, N - 1].
3. 1 <= G.length <= 10000.
4. G is a subset of all values in the linked list.
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
    public int numComponents(ListNode head, int[] G) {
        // 统计 G 的元素分布
        int[] arr = new int[10000];
        for (int i = 0; i < G.length; i++) {
            arr[G[i]]++;
        }
        
        int n = 0;  // 组件个数
        boolean hasCom = false; // 是否已有 至少一个节点的组件
        while (head != null) {
            if (hasCom && arr[head.val] <= 0) {
                n++;
                hasCom = false;
            }
            else if (arr[head.val] > 0) {
                arr[head.val]--;
                hasCom = true;
            }
            
            head = head.next;
        }
        
        // 末尾节点的值在列表G中
        if (hasCom) n++;
        
        return n;
    }
}

class Solution1 {
    public int numComponents(ListNode head, int[] G) {
        if (head == null || G == null || G.length < 1) {
            return 0;
        }

        int[] bitmap = new int[10000];
        for (int i = 0; i < G.length; i++) {
            bitmap[G[i]] = 1;
        }
        int n = 0;
        ListNode cur = head;
        while (cur != null) {
            if (bitmap[cur.val] == 1) {
                while (cur.next != null && bitmap[cur.next.val] == 1) {
                    cur = cur.next;
                }
                n++;
            }
            cur = cur.next;
        }
        return n;
    }
}
