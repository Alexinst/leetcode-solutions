/*
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/

给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）

例如：
	给定二叉树 [3,9,20,null,null,15,7],
	    3
	   / \
	  9  20
	    /  \
	   15   7

	返回其自底向上的层次遍历为：
	[
	  [15,7],
	  [9,20],
	  [3]
	]

-----------------------------------------------------------------------------------------------------

Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).

For example:
	Given binary tree [3,9,20,null,null,15,7],
	    3
	   / \
	  9  20
	    /  \
	   15   7

	return its bottom-up level order traversal as:
	[
	  [15,7],
	  [9,20],
	  [3]
	]
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

    private List<List<Integer>> tree = new ArrayList<>();

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        // 广度优先搜索
        search(root, 0);

        // 颠倒二叉树层级
        for (int i = 0, j = tree.size() - 1; i < j; i++) {
            tree.add(i, tree.remove(j));
        }
        // or
        // Collections.reverse(tree);
        
        return tree;
    }

    private void search(TreeNode node, int level) {
        if (node == null)
            return ;
        
        if (tree.size() < level + 1)
            tree.add(new ArrayList<>());
        tree.get(level).add(node.val);
        
        search(node.left, level+1);
        search(node.right, level+1);
    }
}

class Solution1 {
    LinkedList<List<Integer>> lists = new LinkedList<List<Integer>>();
        
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        helper(root, 0);
        return lists;
    }
    
    private void helper(TreeNode p, int level) {
        if (p == null) return;
        if (lists.size() == level)
            lists.addFirst(new ArrayList<Integer>());
        lists.get(lists.size() - 1 - level).add(p.val);
        helper(p.left, level + 1);
        helper(p.right, level + 1);
    }
}
