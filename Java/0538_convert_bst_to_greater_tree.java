/*
 * 

给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。

例如：
	输入: 二叉搜索树:
		      5
		    /   \
		   2     13

	输出: 转换为累加树:
		     18
		    /   \
		  20     13

---------------------------------------------------------------------------------------------------

Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus sum of all keys greater than the original key in BST.

Example:
	Input: The root of a Binary Search Tree like this:
		      5
		    /   \
		   2     13

	Output: The root of a Greater Tree like this:
		     18
		    /   \
		  20     13
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
 * 逆中序遍历
 */
class MySolution {
    
    private int sum = 0;
    
    public TreeNode convertBST(TreeNode root) {
        toGT(root);
        return root;
    }

    private void toGT(TreeNode node) {
        if (node == null)
            return ;
        
        toGT(node.right);
        node.val += sum;
        sum = node.val;
        toGT(node.left);
    }
}

class Solution1 {
    public TreeNode convertBST(TreeNode root) {
        toGT(root.right, 0);
        return root;
    }

    public int toGT(TreeNode root, int left) {
        if (root == null)
            return left;

        root.val += toGT(root.right, left);
        return toGT(root.left, root.val);
    }
}
