/*
 * https://leetcode-cn.com/problems/second-minimum-node-in-a-binary-tree/ 
 
给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。如果一个节点有两个子节点的话，那么这个节点的值不大于它的子节点的值。 

给出这样的一个二叉树，你需要输出所有节点中的第二小的值。如果第二小的值不存在的话，输出 -1 。

示例 1:
	输入: 
	    2
	   / \
	  2   5
	     / \
	    5   7

	输出: 5
	说明: 最小的值是 2 ，第二小的值是 5 。
	
示例 2:
	输入: 
	    2
	   / \
	  2   2

	输出: -1
	说明: 最小的值是 2, 但是不存在第二小的值。

-----------------------------------------------------------------------------------------------

Given a non-empty special binary tree consisting of nodes with the non-negative value, where each node in this tree has exactly two or zero sub-node. If the node has two sub-nodes, then this node's value is the smaller value among its two sub-nodes. More formally, the property root.val = min(root.left.val, root.right.val) always holds.

Given such a binary tree, you need to output the second minimum value in the set made of all the nodes' value in the whole tree.

If no such second minimum value exists, output -1 instead.

	Example 1:
	Input: 
	    2
	   / \
	  2   5
	     / \
	    5   7

	Output: 5
	Explanation: The smallest value is 2, the second smallest value is 5.
	 
Example 2:
	Input: 
	    2
	   / \
	  2   2

	Output: -1
	Explanation: The smallest value is 2, but there isn't any second smallest value.
*/


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class MySolution {
    
    private int second = -1;
    
    public int findSecondMinimumValue(TreeNode root) {
        // 二叉树非空
        findSecond(root, root.val);
        return second;
    }
    
    private void findSecond(TreeNode node, int last) {
        if (node == null)
            return ;
        
        if (node.val > last) {
            if (second != -1)
                second = Math.min(second, node.val);
            else
                second = node.val;
        }
        
        findSecond(node.left, last);
        findSecond(node.right, last);   
    }
}

class Solution1 {
    public int findSecondMinimumValue(TreeNode root) {
        if (root == null) return -1;
        if (root.left == null && root.right == null) return -1;

        int l = root.left.val, r = root.right.val;
        if (l == root.val)
            l = findSecondMinimumValue(root.left);
        if (r == root.val)
            r = findSecondMinimumValue(root.right);
        
        if (l != -1 && r != -1)
            return Math.min(l, r);
        if (l == -1)
            return r;
        
        return l;   // r = -1
    }
}
