/*
 * https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/

给定一个二叉树，原地将它展开为链表。

例如，给定二叉树
		1
	   / \
	  2   5
	 / \   \
	3   4   6

将其展开为：
	1
	 \
	  2
	   \
		3
		 \
		  4
		   \
			5
         	 \
          	  6

-------------------------------------------------------------------------------------------------

Given a binary tree, flatten it to a linked list in-place.

For example, given the following tree:
		1
	   / \
	  2   5
	 / \   \
	3   4   6

The flattened tree should look like:
	1
	 \
	  2
	   \
		3
		 \
		  4
		   \
			5
			 \
			  6
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
    private TreeNode pre = null;
    public void flatten(TreeNode root) {
        if (root != null) {

            if (pre != null) {
                pre.left = null;
                pre.right = root;
            }
            pre = root;
            TreeNode left = root.left, right = root.right;
            
            flatten(left);
            flatten(right);
        }
    }
}
