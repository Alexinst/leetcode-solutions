/*
 * https://leetcode-cn.com/problems/binary-tree-tilt/

给定一个二叉树，计算整个树的坡度。一个树的节点的坡度定义即为，该节点左子树的结点之和和右子树结点之和的差的绝对值。

空结点的的坡度是0。整个树的坡度就是其所有节点的坡度之和。

示例:
	输入: 
		 1
	       /   \
	      2     3
	输出: 1
	解释: 
	结点的坡度 2 : 0
	结点的坡度 3 : 0
	结点的坡度 1 : |2-3| = 1
	树的坡度 : 0 + 0 + 1 = 1

注意:
	1. 任何子树的结点的和不会超过32位整数的范围。
	2. 坡度的值不会超过32位整数的范围。

----------------------------------------------------------------------------------------

Given a binary tree, return the tilt of the whole tree. The tilt of a tree node is defined as the absolute difference between the sum of all left subtree node values and the sum of all right subtree node values. 

Null node has tilt 0. The tilt of the whole tree is defined as the sum of all nodes' tilt.

Example:
	Input: 
		 1
	       /   \
	      2     3
	Output: 1
	Explanation: 
	Tilt of node 2 : 0
	Tilt of node 3 : 0
	Tilt of node 1 : |2-3| = 1
	Tilt of binary tree : 0 + 0 + 1 = 1

Note:
	1. The sum of node values in any subtree won't exceed the range of 32-bit integer.
	2. All the tilt values won't exceed the range of 32-bit integer.
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
    
    private int tilt = 0;
    
    public int findTilt(TreeNode root) {
        sumOfNode(root);
        return tilt;
    }
    
    private int sumOfNode(TreeNode node) {
        // 计算并返回当前节点的结点之和
        if (node == null)
            return 0;
        
        int left = sumOfNode(node.left);
        int right = sumOfNode(node.right);
        // 将当前节点的坡度计入整体坡度
        tilt += Math.abs(left - right);
        return node.val + left + right;
    }
}


