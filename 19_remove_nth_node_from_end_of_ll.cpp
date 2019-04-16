#include <iostream>

struct ListNode {
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
 };

class MySolution {
public:
    ListNode* removeNthFromEnd(ListNode* head, int n) {
        if (!head)
            return head;

        ListNode *node = head;
        ListNode *res;
        int len;
        while (node)
        {
            len++;
            node = node->next;
        }

        node = head;
        for (int i = 0; i < len - n - 1; i++)
            node = node->next;          // The node before the one to be removed.

        if (n == len)                   // Execute if the node to be removed is the first one.
            res = head->next;
        else if (!node->next->next)     // Execute if the node to be removed is the last one.
        {
            res = head;
            node->next = NULL;
        }
        else
        {
            res = head;
            node->next = node->next->next;
        }

        return res;
    }
};
