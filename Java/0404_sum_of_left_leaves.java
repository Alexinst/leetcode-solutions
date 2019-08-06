/*
 * https://leetcode-cn.com/problems/sum-of-left-leaves/

计算给定二叉树的所有左叶子之和。

示例：
	    3
	   / \
	  9  20
	    /  \
	   15   7

	在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24

---------------------------------------------------------------------------

Find the sum of all left leaves in a given binary tree.

Example:
	    3
	   / \
	  9  20
	    /  \
	   15   7

	There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
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
    // 0 : 根节点
    // 1 : 左叶子
    // 2 : 右叶子
    private int sum = 0;
    
    public int sumOfLeftLeaves(TreeNode root) {
        sumOfLeftLeaves(root, 0);
        
        return sum;
    }
    
    private void sumOfLeftLeaves(TreeNode node, int side) {
        if (node == null) return ;
        
        if (node.left == null && node.right == null)
            sum += side % 2 * node.val;
        
        sumOfLeftLeaves(node.left, 1);
        sumOfLeftLeaves(node.right, 2);
    }
}

class Solution1 {
    public int sumOfLeftLeaves(TreeNode root) {
        int sum = 0;
        if (root == null) {
            return 0;
        }
        if(root.left != null){
            if(root.left.left == null && root.left.right == null) {
                sum += root.left.val;
            } else {
                sum += sumOfLeftLeaves(root.left);
            }
        }
        sum += sumOfLeftLeaves(root.right);
        return sum;
    }
}


