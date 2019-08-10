/*
 * https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/
 
给定一个所有节点为非负值的二叉搜索树，求树中任意两节点的差的绝对值的最小值。

示例 :
	输入:
	   1
	    \
	     3
	    /
	   2

	输出:
	1

	解释:
	最小绝对差为1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。

注意: 树中至少有2个节点。

---------------------------------------------------------------------------

Given a binary search tree with non-negative values, find the minimum absolute difference between values of any two nodes.

Example:
Input:
	   1
	    \
	     3
	    /
	   2

	Output:
	1

	Explanation:
	The minimum absolute difference is 1, which is the difference between 2 and 1 (or between 2 and 3).

Note: There are at least two nodes in this BST.
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
 * 最小绝对差肯定是某两个相邻数的差的绝对值，所以对BST进行中序遍历（迭代版）
 */
class MySolution {
    public int getMinimumDifference(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        long last = Long.MAX_VALUE;
        int min = Integer.MAX_VALUE;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (last != Long.MAX_VALUE)
                min = Math.min(min, root.val - (int) last);
            last = (long) root.val;
            root = root.right;
        }
        
        return min;
    }
}

class Solution1 {
    TreeNode pre = null;
    int val = Integer.MAX_VALUE;

    public int getMinimumDifference(TreeNode root) {
        helper(root);
        return val;
    }
    public void helper(TreeNode root){
        if(root == null){
            return;
        }
        helper(root.left);
        if(pre != null){
            val = Math.min(val,root.val-pre.val); 
        }
        pre = root;
        helper(root.right);
    }
}
