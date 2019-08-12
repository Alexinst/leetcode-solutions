/*
 * https://leetcode-cn.com/problems/univalued-binary-tree/
 
如果二叉树每个节点都具有相同的值，那么该二叉树就是单值二叉树。

只有给定的树是单值二叉树时，才返回 true；否则返回 false。

 

示例 1：
	输入：[1,1,1,1,1,null,1]
				1
			   / \
			  1   1
			 / \   \
			1   1   1
	输出：true

示例 2：
	输入：[2,2,2,5,2]
		        2
			   / \
			  2   2
			 / \
			5   2
	输出：false

提示：
	1. 给定树的节点数范围是 [1, 100]。
	2. 每个节点的值都是整数，范围为 [0, 99] 。

-------------------------------------------------------------------------------------------

A binary tree is univalued if every node in the tree has the same value.

Return true if and only if the given tree is univalued.
 

Example 1:
	Input: [1,1,1,1,1,null,1]
				1
			   / \
			  1   1
			 / \   \
			1   1   1
	Output: true

Example 2:
	Input: [2,2,2,5,2]
		        2
			   / \
			  2   2
			 / \
			5   2
	Output: false

Note:
	1. The number of nodes in the given tree will be in the range [1, 100].
	2. Each node's value will be an integer in the range [0, 99].
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
    public boolean isUnivalTree(TreeNode root) {
        if (root == null || root.left == null && root.right == null)
            return true;
        else if (root.left == null)
            return root.val == root.right.val && isUnivalTree(root.right);
        else if (root.right == null)
            return root.val == root.left.val && isUnivalTree(root.left);
        else
            return root.val == root.left.val && root.val == root.right.val &&
                   isUnivalTree(root.left) && isUnivalTree(root.right);
    }
}

class Solution1 {
    public boolean isUnivalTree(TreeNode root) {
        return root == null || (root.left == null || root.left.val == root.val && isUnivalTree(root.left))
                && (root.right == null || root.right.val == root.val && isUnivalTree(root.right));
    }
}
