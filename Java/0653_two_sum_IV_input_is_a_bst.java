/*
 * https://leetcode-cn.com/problems/two-sum-iv-input-is-a-bst/ 
 
给定一个二叉搜索树和一个目标结果，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。

案例 1:
	输入: 
	    5
	   / \
	  3   6
	 / \   \
	2   4   7

	Target = 9

	输出: True
	 
案例 2:
	输入: 
	    5
	   / \
	  3   6
	 / \   \
	2   4   7

	Target = 28

	输出: False

----------------------------------------------------------------------------------------------------

给定一个二叉搜索树和一个目标结果，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。

案例 1:
	输入: 
	    5
	   / \
	  3   6
	 / \   \
	2   4   7

	Target = 9

	输出: True
	 
案例 2:
	输入: 
	    5
	   / \
	  3   6
	 / \   \
	2   4   7

	Target = 28

	输出: False
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
    private Map<Integer, Integer> map = new HashMap<>();
    
    public boolean findTarget(TreeNode root, int k) {
        if (root == null)
            return false;

        if (findTarget(root.left, k)) return true;
        if (map.containsKey(k - root.val))
            return true;
        map.put(root.val, k - root.val);
        return findTarget(root.right, k);
    }
}

class Solution1 {
    private TreeNode root;

    public boolean findTarget(TreeNode root, int k) {
        this.root = root;
        return visitTree(root, k);
    }

    private boolean visitTree(TreeNode curRoot, int sum) {
        if (curRoot == null) return false;

        int other = sum - curRoot.val;
        if ((other != curRoot.val) && searchTree(this.root, other))
            return true;

        return (visitTree(curRoot.left, sum) || visitTree(curRoot.right, sum));
    }

    private boolean searchTree(TreeNode node, int val) {
        if (node == null) return false;
        if (node.val == val) return true;

        return (node.val > val) ? searchTree(node.left, val) : searchTree(node.right, val);
    }
}
