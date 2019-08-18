/*
 * https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii/
 
给定一个二叉树
	struct Node {
	  int val;
	  Node *left;
	  Node *right;
	  Node *next;
	}
填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。

初始状态下，所有 next 指针都被设置为 NULL。


示例：
	https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/02/15/117_sample.png
	输入：{"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":null,"right":null,"val":4},"next":null,"right":{"$id":"4","left":null,"next":null,"right":null,"val":5},"val":2},"next":null,"right":{"$id":"5","left":null,"next":null,"right":{"$id":"6","left":null,"next":null,"right":null,"val":7},"val":3},"val":1}

	输出：{"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":{"$id":"4","left":null,"next":{"$id":"5","left":null,"next":null,"right":null,"val":7},"right":null,"val":5},"right":null,"val":4},"next":{"$id":"6","left":null,"next":null,"right":{"$ref":"5"},"val":3},"right":{"$ref":"4"},"val":2},"next":null,"right":{"$ref":"6"},"val":1}

	解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。
 

提示：
	1. 你只能使用常量级额外空间。
	2. 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。

---------------------------------------------------------------------------------

Given a binary tree
	struct Node {
	  int val;
	  Node *left;
	  Node *right;
	  Node *next;
	}
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Example:
	https://assets.leetcode.com/uploads/2019/02/15/117_sample.png
	Input: {"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":null,"right":null,"val":4},"next":null,"right":{"$id":"4","left":null,"next":null,"right":null,"val":5},"val":2},"next":null,"right":{"$id":"5","left":null,"next":null,"right":{"$id":"6","left":null,"next":null,"right":null,"val":7},"val":3},"val":1}
	Output: {"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":{"$id":"4","left":null,"next":{"$id":"5","left":null,"next":null,"right":null,"val":7},"right":null,"val":5},"right":null,"val":4},"next":{"$id":"6","left":null,"next":null,"right":{"$ref":"5"},"val":3},"right":{"$ref":"4"},"val":2},"next":null,"right":{"$ref":"6"},"val":1}

	Explanation: Given the above binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B.
 
Note:
	1. You may only use constant extra space.
	2. Recursive approach is fine, implicit stack space does not count as extra space for this problem.
*/

/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val,Node _left,Node _right,Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/
class MySolution {
    public Node connect(Node root) {
        if (root == null)
            return root;
        
        Node left = root.left, right = root.right;
        while (left != null && right != null) {
            left.next = right;
            left = left.right != null ? left.right : left.left;
            right = right.left != null ? right.left : right.right;
        }
        connect(root.left);
        connect(root.right);
        return root;
    }
}

class Solution1 {
    public Node connect(Node root) {
        if (root == null) return null;
        if (root.left != null) {
            if (root.right != null)
                root.left.next = root.right;
            else
                root.left.next = nextNode(root.next);
        }
        if (root.right != null)
            root.right.next = nextNode(root.next);

        connect(root.right);
        connect(root.left);
        return root;
    }

    public Node nextNode(Node root) {
        if (root == null) return null;
        while (root != null) {
            if (root.left != null) 
				return root.left;
            else if (root.right != null) 
				return root.right;
            else 
				root = root.next;
        }
        return null;
    }
}

class Solution2 {
    public Node connect(Node root) {
        if (root == null) 
            return null;

        if (root.left != null) {
            if (root.right != null) {
                root.left.next = root.right;
            } else {
                Node p = root.next;
                while (p != null) {
                    if (p.left != null) {
                        p = p.left;
                        break;
                    }
                    if (p.right != null) {
                        p = p.right;
                        break;
                    }
                    p = p.next;
                }
                root.left.next = p;
            }
        }
        if (root.right != null) {
            Node p = root.next;
            while (p != null) {
                if (p.left != null) {
                    p = p.left;
                    break;
                }
                if (p.right != null) {
                    p = p.right;
                    break;
                }
                p = p.next;
            }
            root.right.next = p;
        }
        connect(root.right);
        connect(root.left);
        return root;
    }
}
