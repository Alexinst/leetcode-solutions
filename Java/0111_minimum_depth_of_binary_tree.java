/*
 * https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/

给定一个二叉树，找出其最小深度。

最小深度是从根节点到最近叶子节点的最短路径上的节点数量。

说明: 叶子节点是指没有子节点的节点。

示例:
	给定二叉树 [3,9,20,null,null,15,7],

	    3
	   / \
	  9  20
	    /  \
	   15   7
	返回它的最小深度  2.

-----------------------------------------------------------------------

Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

Note: A leaf is a node with no children.

Example:
	Given binary tree [3,9,20,null,null,15,7],

	    3
	   / \
	  9  20
	    /  \
	   15   7
	return its minimum depth = 2.
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
    private int min = Integer.MAX_VALUE;
    
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        
        minDepth(root, 0);
        return min;
    }
    
    private void minDepth(TreeNode root, int depth) {
        if (root == null)
            return ;
        
        depth++;
        
        if (root.left == null && root.right == null) {
            min = Math.min(min, depth);
            return;
        }
        
        minDepth(root.left, depth);
        minDepth(root.right, depth);
    }
}

class Solution1 {
    int min_depth = Integer.MAX_VALUE;
    public int minDepth(TreeNode root) {
        if(root != null){
            dfs(root, 1);
            return min_depth;
        }else{
            return 0;
        }
    }

    public void dfs(TreeNode root, int depth){
        if(depth >= min_depth) return;
        if(root.left == null && root.right == null){
            if(depth < min_depth) min_depth = depth;
            
        }else{
            if(root.left != null) dfs(root.left, depth + 1);
            if(root.right != null) dfs(root.right, depth + 1);
        }
    }
}
