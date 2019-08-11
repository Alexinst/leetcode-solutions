/*
 * https://leetcode-cn.com/problems/range-sum-of-bst/

给定二叉搜索树的根结点 root，返回 L 和 R（含）之间的所有结点的值的和。

二叉搜索树保证具有唯一的值。

示例 1：
	输入：root = [10,5,15,3,7,null,18], L = 7, R = 15
	输出：32

示例 2：
	输入：root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
	输出：23

提示：
    1. 树中的结点数量最多为 10000 个。
    2. 最终的答案保证小于 2^31。

-----------------------------------------------------------------------------------------------

Given the root node of a binary search tree, return the sum of values of all nodes with value between L and R (inclusive).

The binary search tree is guaranteed to have unique values.

Example 1:
	Input: root = [10,5,15,3,7,null,18], L = 7, R = 15
	Output: 32

Example 2:
	Input: root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
	Output: 23

Note:
    1. The number of nodes in the tree is at most 10000.
    2. The final answer is guaranteed to be less than 2^31.
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
    public int rangeSumBST(TreeNode root, int L, int R) {
        return rangeSumBST(root, L, R, 0);
    }
    
    public int rangeSumBST(TreeNode root, int L, int R, int sum) {
        if (root == null)
            return sum;
        
        sum = rangeSumBST(root.left, L, R, sum);
        if (root.val >= L && root.val <= R)
            sum += root.val;
        if (root.val > R)
            return sum;
        
        return rangeSumBST(root.right, L, R, sum);
    }
}

class Solution1 {
    public int rangeSumBST(TreeNode root, int L, int R) {
        return getResult(root,L,R);
    }
        
    public int getResult(TreeNode root, int L, int R){
        if (root == null) 
           return 0;
        else if (root.val < L)
            return getResult( root.right, L, R);
        else if (root.val > R)
            return  getResult( root.left, L, R);
        else
            return  root.val +  getResult( root.right, L, R)  + getResult( root.left, L, R);
    }
}
