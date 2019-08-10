/*
 * https://leetcode-cn.com/problems/invert-binary-tree/

翻转一棵二叉树。

示例：
	输入：
	     4
	   /   \
	  2     7
	 / \   / \
	1   3 6   9
	
	输出：
	     4
	   /   \
	  7     2
	 / \   / \
	9   6 3   1

--------------------------------------------------------------------------------

Invert a binary tree.

Example:
	Input:
	     4
	   /   \
	  2     7
	 / \   / \
	1   3 6   9
	
	Output:
	     4
	   /   \
	  7     2
	 / \   / \
	9   6 3   1
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
    public TreeNode invertTree(TreeNode root) {
        invert(root);
        return root;
    }
    
    private void invert(TreeNode node) {
        if (node == null)
            return ;
        
        TreeNode tmp = node.left;
        node.left = node.right;
        node.right = tmp;
        invert(node.left);
        invert(node.right);
    }
}


