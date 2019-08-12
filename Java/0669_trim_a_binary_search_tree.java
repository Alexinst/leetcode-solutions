/*
 * https://leetcode-cn.com/problems/trim-a-binary-search-tree/
 
给定一个二叉搜索树，同时给定最小边界L 和最大边界 R。通过修剪二叉搜索树，使得所有节点的值在[L, R]中 (R>=L) 。你可能需要改变树的根节点，所以结果应当返回修剪好的二叉搜索树的新的根节点。

示例 1:
	输入: 
	    1
	   / \
	  0   2

	  L = 1
	  R = 2
	输出: 
	    1
	      \
	       2

示例 2:
	输入: 
	    3
	   / \
	  0   4
	   \
	    2
	   /
	  1

	  L = 1
	  R = 3
	输出: 
	      3
	     / 
	   2   
	  /
	 1

---------------------------------------------------------------------------------------------

Given a binary search tree and the lowest and highest boundaries as L and R, trim the tree so that all its elements lies in [L, R] (R >= L). You might need to change the root of the tree, so the result should return the new root of the trimmed binary search tree.

Example 1:
	Input: 
	    1
	   / \
	  0   2

	  L = 1
	  R = 2

	Output: 
	    1
	      \
	       2

Example 2:
	Input: 
	    3
	   / \
	  0   4
	   \
	    2
	   /
	  1

	  L = 1
	  R = 3

	Output: 
	      3
	     / 
	   2   
	  /
	 1
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
class Solution {
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) return null;
        else if (root.val > R) return trimBST(root.left, L, R);
        else if (root.val < L) return trimBST(root.right, L, R);
        
        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);
        return root;
    }
}
