/*
 * https://leetcode-cn.com/problems/longest-univalue-path/ 
 
给定一个二叉树，找到最长的路径，这个路径中的每个节点具有相同值。 这条路径可以经过也可以不经过根节点。

注意：两个节点之间的路径长度由它们之间的边数表示。

示例 1:
	输入:

		      5
		     / \
		    4   5
		   / \   \
		  1   1   5
	输出: 2

示例 2:
	输入:

		      1
		     / \
		    4   5
		   / \   \
		  4   4   5
	输出: 2

注意: 给定的二叉树不超过10000个结点。 树的高度不超过1000。

--------------------------------------------------------------------------------

Given a binary tree, find the length of the longest path where each node in the path has the same value. This path may or may not pass through the root.

The length of path between two nodes is represented by the number of edges between them.

Example 1:
Input:
		      5
		     / \
		    4   5
		   / \   \
		  1   1   5
	Output: 2

Example 2:
	Input:
		      1
		     / \
		    4   5
		   / \   \
		  4   4   5
	Output: 2

Note: The given binary tree has not more than 10000 nodes. The height of the tree is not more than 1000.
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
class Solution1 {
    private int max = 0;
    
    public int longestUnivaluePath(TreeNode root) {
        longestPath(root);
        return max;
    }
    
    private int longestPath(TreeNode node) {
        if (node == null)
            return 0;
        
        int l = longestPath(node.left);
        int r = longestPath(node.right);
        int left = 0, right = 0;
        if (node.left != null && node.val == node.left.val)
            left += l + 1;
        if (node.right != null && node.val == node.right.val)
            right += r + 1;
        
        max = Math.max(max, left + right);
        return Math.max(left, right);
    }
}
