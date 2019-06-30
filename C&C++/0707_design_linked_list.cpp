/*
 设计链表的实现。您可以选择使用单链表或双链表。单链表中的节点应该具有两个属性：val 和 next。val 是当前节点的值，next 是指向下一个节点的指针/引用。如果要使用双向链表，则还需要一个属性 prev 以指示链表中的上一个节点。假设链表中的所有节点都是 0-index 的。

在链表类中实现这些功能：
	get(index)：获取链表中第 index 个节点的值。如果索引无效，则返回-1。
	addAtHead(val)：在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
	addAtTail(val)：将值为 val 的节点追加到链表的最后一个元素。
	addAtIndex(index,val)：在链表中的第 index 个节点之前添加值为 val  的节点。如果 index 等于链表的长度，则该节点将附加到链表的末尾。如果 index 大于链表长度，则不会插入节点。
	deleteAtIndex(index)：如果索引 index 有效，则删除链表中的第 index 个节点。
 

示例：
	MyLinkedList linkedList = new MyLinkedList();
	linkedList.addAtHead(1);
	linkedList.addAtTail(3);
	linkedList.addAtIndex(1,2);   //链表变为1-> 2-> 3
	linkedList.get(1);            //返回2
	linkedList.deleteAtIndex(1);  //现在链表是1-> 3
	linkedList.get(1);            //返回3
 
提示：
1. 所有值都在 [1, 1000] 之内。
2. 操作次数将在  [1, 1000] 之内。
3. 请不要使用内置的 LinkedList 库。
*/

class MyLinkedList {
public:
    ListNode *linkedList;
    
    /** Initialize your data structure here. */
    MyLinkedList() {
        linkedList = 0;
    }
    
    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    int get(int index) {
        int i = 0;
        ListNode *head = linkedList;
        while (head && i < index)
        {
            head = head->next;
            i++;
        }
        if (head && i == index)
            return head->val;
        else
            return -1;
    }
    
    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    void addAtHead(int val) {
        ListNode *head = (ListNode *)malloc(sizeof(ListNode));
        head->next = linkedList;
        head->val = val;
        linkedList = head;        
    }
    
    /** Append a node of value val to the last element of the linked list. */
    void addAtTail(int val) {
        ListNode *head = linkedList;
        ListNode *node = (ListNode *)malloc(sizeof(ListNode));
        node->next = 0;
        node->val = val;
        if (!head) {
            linkedList = node;
            return;
        }
        while (head->next) {
            head = head->next;
        }
        head->next = node;
    }
    
    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    void addAtIndex(int index, int val) {
        int i = 0;
        ListNode *head = linkedList;
        if (!head && index == 0)  // 链表为空且index=0
        {
            ListNode *node = (ListNode *)malloc(sizeof(ListNode));
            node->val = val;
            node->next = 0;
            linkedList = node;
            return;
        }
        while (head && i < index - 1)
        {
            head = head->next;
            i++;
        }
        if (head && head->next == 0) // head非空且所加值在末尾
        {
            ListNode *node = (ListNode *)malloc(sizeof(ListNode));
            node->val = val;
            node->next = 0;
            head->next = node;
        }
        else if (i == index - 1 && head && head->next) // head非空且index有效（i=index-1）且所加值不是在链表末尾
        {
            ListNode *node = (ListNode *)malloc(sizeof(ListNode));
            node->val = val;
            node->next = head->next;
            head->next = node;
        }
    }
    
    /** Delete the index-th node in the linked list, if the index is valid. */
    void deleteAtIndex(int index) {
        int i = 0;
        ListNode *head = linkedList;
        while (head && i < index - 1)
        {
            head = head->next;
            i++;
        }
        if (head == 0) return;
        if (head->next == 0 && index == 0)
        {
            linkedList = 0;
            return;
        }
        if (head->next)
        {
            ListNode *node = head->next;
            head->next = node->next;
            free(node);
        }
    }
};
