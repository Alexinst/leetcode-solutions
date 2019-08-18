/*
 * https://leetcode-cn.com/problems/binary-tree-right-side-view/
 
给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。

示例:
	输入: [1,2,3,null,5,null,4]
	输出: [1, 3, 4]
	解释:
	       1            <---
	     /   \
	    2     3         <---
	     \     \
  	      5     4       <---

---------------------------------------------------------------------------

Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

Example:
	Input: [1,2,3,null,5,null,4]
	Output: [1, 3, 4]
	Explanation:

		   1            <---
		 /   \
		2     3         <---
		 \     \
		  5     4       <---
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
    private List<Integer> list = new ArrayList<>();
    
    public List<Integer> rightSideView(TreeNode root) {
        traversal(root, 0);
        return list;
    }
    
    private void traversal(TreeNode node, int level) {
        if (node != null) {
            if (list.size() == level) 
                list.add(node.val);
            else 
                list.set(level, node.val);
            
            traversal(node.left, level + 1);
            traversal(node.right, level + 1);
        }
    }
}

class Solution1 {
    private List<Integer> ans = new ArrayList<>();
    public List<Integer> rightSideView(TreeNode root) {
        solve(root, 0);
        return ans;
    }
    
    public void solve(TreeNode root, int level) {
        if(root != null) {
            if(ans.size() == level) {
                ans.add(root.val);
            }
            solve(root.right, level + 1);
            solve(root.left, level + 1);
        }
    }
}
