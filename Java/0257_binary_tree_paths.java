/*
 * https://leetcode-cn.com/problems/binary-tree-paths/

给定一个二叉树，返回所有从根节点到叶子节点的路径。
说明: 叶子节点是指没有子节点的节点。

示例:
	输入:
	  1
	 / \
	2   3
	 \
	  5

	输出: ["1->2->5", "1->3"]

	解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3

--------------------------------------------------------------------------------------------------

Given a binary tree, return all root-to-leaf paths.
Note: A leaf is a node with no children.

Example:
	Input:
	  1
	 / \
	2   3
	 \
	  5

	Output: ["1->2->5", "1->3"]

	Explanation: All root-to-leaf paths are: 1->2->5, 1->3
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
    private List<String> paths = new ArrayList<>();

    public List<String> binaryTreePaths(TreeNode root) {
        dig(root, new StringBuilder());
        return paths;
    }

    private void dig(TreeNode node, StringBuilder path) {
        if (node == null)
            return ;
        if (node.left == null && node.right == null) {
            path.append(node.val);
            paths.add(path.toString());
            return ;
        }
        
        path.append(node.val);
        path.append("->");
        dig(node.left, new StringBuilder(path));
        dig(node.right, new StringBuilder(path));
    }
}


class Solution1 {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        helper(res, root, sb);
        return res;
    }

    private void helper(List<String> res, TreeNode root, StringBuilder sb) {
        if (root == null) return;
        int len = sb.length();
        sb.append(root.val);
        if (root.left == null && root.right == null) {
            res.add(sb.toString());
        } else {
            sb.append("->");
            helper(res, root.left, sb);
            helper(res, root.right, sb);
        }
        sb.setLength(len);
    }
}
