/*
 * https://leetcode-cn.com/problems/find-bottom-left-tree-value/
 
给定一个二叉树，在树的最后一行找到最左边的值。

示例 1:
	输入:
	    2
	   / \
	  1   3

	输出: 1

示例 2:
	输入:
		1
	       / \
	      2   3
	     /   / \
	    4   5   6
	       /
	      7

	输出: 7

注意: 您可以假设树（即给定的根节点）不为 NULL。

-------------------------------------------------------------------------------------

Given a binary tree, find the leftmost value in the last row of the tree.

Example 1:
	Input:
	    2
	   / \
	  1   3

	Output: 1

Example 2: 
	Input:
		1
	       / \
	      2   3
	     /   / \
	    4   5   6
	       /
	      7

	Output: 7
	
Note: You may assume the tree (i.e., the given root node) is not NULL.
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
/**
 * 思路：前序遍历。对每一行的第一次遍历，则是最左值。
 */
class MySolution {
    private int depth, blVal;
    public int findBottomLeftValue(TreeNode root) {
        depth = 0;
        blVal = 0;
        findBottomLeftValue(root, 1);
        return blVal;
    }

    private void findBottomLeftValue(TreeNode node, int level) {
        if (node == null) return ;

        if (node.left == null && node.right == null && level > depth) {
            blVal = node.val;
            depth = level;
        }
        findBottomLeftValue(node.left, level + 1);
        findBottomLeftValue(node.right, level + 1);
    }
}

class Solution1 {
    private int ans = 0, maxDepth = 0;

    public int findBottomLeftValue(TreeNode root) {
        dfs(root, 1);
        return ans;
    }

    private void dfs(TreeNode root, int depth) {
        if (root != null) {
            if (root.left == null && root.right == null) {
                if (depth > maxDepth) {
                    maxDepth = depth;
                    ans = root.val;
                }
            } else {
                dfs(root.left, 1 + depth);
                dfs(root.right, 1 + depth);
            }
        }
    }
}
