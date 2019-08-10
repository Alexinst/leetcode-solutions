/*
 * https://leetcode-cn.com/problems/subtree-of-another-tree/

给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看做它自身的一棵子树。

示例 1:
	给定的树 s:

	     3
	    / \
	   4   5
	  / \
	 1   2
	给定的树 t：

	   4 
	  / \
	 1   2
	返回 true，因为 t 与 s 的一个子树拥有相同的结构和节点值。

示例 2:
	给定的树 s：

	     3
	    / \
	   4   5
	  / \
	 1   2
	    /
	   0
	给定的树 t：

	   4
	  / \
	 1   2
	返回 false。

----------------------------------------------------------------------------------------------

Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.

Example 1:
	Given tree s:
	     3
	    / \
	   4   5
	  / \
	 1   2
	Given tree t:
	   4 
	  / \
	 1   2
	Return true, because t has the same structure and node values with a subtree of s.

Example 2:
	Given tree s:

	     3
	    / \
	   4   5
	  / \
	 1   2
	    /
	   0
	Given tree t:
	   4
	  / \
	 1   2
	Return false.
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
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null)
            return false;
        
        // 如果确认有与 t 相同的子树，则直接返回
        // 否则将继续寻找，直至 s 的底部。
        if (s.val == t.val && isSame(s, t))
            return true;
        
        return isSubtree(s.left, t) || isSubtree(s.right, t);
    }
    
    private boolean isSame(TreeNode s, TreeNode t) {
        // 验证两棵树是否完全相同
        if (s == null && t == null)
            return true;
        else if (s == null || t == null)
            return false;
        
        return s.val == t.val && isSame(s.left, t.left) && isSame(s.right, t.right);
    }
}

class Solution1 {
    private boolean flag = false;

    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (null == s && null == t) {
            return true;
        } else if (null == s || null == t) {
            return false;
        }
        if (s.val == t.val) {
            flag = true;
            if (isSubtree(s.left, t.left)) {
                if (isSubtree(s.right, t.right)) {
                    return true;
                } else {
                    flag = false;
                }
            } else {
                flag = false;
            }
        }
        if (!flag) {
            if (isSubtree(s.left, t)) {
                return true;
            } else if (isSubtree(s.right, t)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
