/*
 * https://leetcode-cn.com/problems/path-sum-ii/

给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。

说明: 叶子节点是指没有子节点的节点。

示例:
	给定如下二叉树，以及目标和 sum = 22，

				  5
				 / \
				4   8
			   /   / \
			  11  13  4
			 /  \    / \
			7    2  5   1

	返回:
	[
	   [5,4,11,2],
	   [5,8,4,5]
	]

-------------------------------------------------------------------------------------------------------------------------

Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

Note: A leaf is a node with no children.

Example:
	Given the below binary tree and sum = 22,

		  5
		 / \
		4   8
	   /   / \
	  11  13  4
	 /  \    / \
	7    2  5   1

	Return:
	[
	   [5,4,11,2],
	   [5,8,4,5]
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
    private List<List<Integer>> paths;
    private List<Integer> path;
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        paths = new ArrayList<>();
        path = new ArrayList<>();
        helper(root, path, sum);
        return paths;
    }
    
    private void helper(TreeNode node, List<Integer> path, int rem) {
        if (node == null) return ;
        
        path.add(node.val);
        if (rem == node.val && node.left == null && node.right == null) {
            paths.add(path);
        }
        else {
            helper(node.left, new ArrayList<>(path), rem - node.val);
            helper(node.right, new ArrayList<>(path), rem - node.val);
        }
    }
}

class Solution1 {
    private List<List<Integer>> result;

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        result = new LinkedList<>();
        List<Integer> path = new ArrayList<>();
        getPath(root, sum, path);
        return result;
    }

    private void getPath(TreeNode root, int sum, List<Integer> path) {
        if (root == null) {
            return;
        }
        path.add(root.val);
        if (root.left == null && root.right == null && root.val - sum == 0) {
            List<Integer> copy = new ArrayList<>(path);
            result.add(copy);
        }
        getPath(root.left, sum - root.val, path);
        getPath(root.right, sum - root.val, path);
        path.remove(path.size() - 1);
    }
}
