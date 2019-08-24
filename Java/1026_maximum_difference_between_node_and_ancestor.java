/*
 * https://leetcode-cn.com/problems/maximum-difference-between-node-and-ancestor/ 
 
给定二叉树的根节点 root，找出存在于不同节点 A 和 B 之间的最大值 V，其中 V = |A.val - B.val|，且 A 是 B 的祖先。

（如果 A 的任何子节点之一为 B，或者 A 的任何子节点是 B 的祖先，那么我们认为 A 是 B 的祖先）
 

示例：
	https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/04/12/2whqcep.jpg

	输入：[8,3,10,1,6,null,14,null,null,4,7,13]
	输出：7
	解释： 
	我们有大量的节点与其祖先的差值，其中一些如下：
	|8 - 3| = 5
	|3 - 7| = 4
	|8 - 1| = 7
	|10 - 13| = 3
	在所有可能的差值中，最大值 7 由 |8 - 1| = 7 得出。
	 
提示：
	1. 树中的节点数在 2 到 5000 之间。
	2. 每个节点的值介于 0 到 100000 之间。

-----------------------------------------------------------------------------------------

Given the root of a binary tree, find the maximum value V for which there exists different nodes A and B where V = |A.val - B.val| and A is an ancestor of B.

(A node A is an ancestor of B if either: any child of A is equal to B, or any child of A is an ancestor of B.)
 

Example 1:
	https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/04/12/2whqcep.jpg

	Input: [8,3,10,1,6,null,14,null,null,4,7,13]
	Output: 7
	Explanation: 
	We have various ancestor-node differences, some of which are given below :
	|8 - 3| = 5
	|3 - 7| = 4
	|8 - 1| = 7
	|10 - 13| = 3
	Among all possible differences, the maximum value of 7 is obtained by |8 - 1| = 7.

Note:
	1. The number of nodes in the tree is between 2 and 5000.
	2. Each node will have value between 0 and 100000.
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
    public int maxAncestorDiff(TreeNode root) {
        if (root == null) return 0;
        
        int left = maxDiff(root.left, root.left, 0);
        int right = maxDiff(root.right, root.right, 0);
        int max = Math.max(maxDiff(root, root, 0), Math.max(left, right));
        return Math.max(max, Math.max(maxAncestorDiff(root.left),
                                      maxAncestorDiff(root.right)));
    }
    
    private int maxDiff(TreeNode node, TreeNode root, int max) {
        if (node == null || root == null) return max;
        
        max = Math.max(max, Math.abs(root.val - node.val));
        return Math.max(maxDiff(node.left, root, max),
                        maxDiff(node.right, root, max));
    }
}

class Solution1 {
    int res = 0;

    public int maxAncestorDiff(TreeNode root) {
        res = 0;
        help(root, 0, Integer.MAX_VALUE);
        return res;
    }

    public void help(TreeNode root, int max, int min) {
        if (null == root) {
            return;
        }
        max = Math.max(max, root.val);
        min = Math.min(min, root.val);
        if ((null == root.left) && (null == root.right)) {
            res = Math.max(res, Math.abs(max - min));
            return;
        }
        help(root.left, max, min);
        help(root.right, max, min);
    }
}

class Solution2 {
    int res = 0;

    public int maxAncestorDiff(TreeNode root) {
        dfs(root);
        return res;
    }

    private int[] dfs(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.left == null && root.right == null) {
            return new int[]{root.val, root.val};
        }

        int[] left = dfs(root.left);
        int[] right = dfs(root.right);

        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        if (left != null) {
            min = Math.min(left[0], min);
            max = Math.max(left[1], max);
        }

        if (right != null) {
            min = Math.min(right[0], min);
            max = Math.max(right[1], max);
        }

        res = Math.max(res, Math.max(Math.abs(max - root.val), Math.abs(min - root.val)));

        return new int[]{Math.min(min, root.val), Math.max(max, root.val)};
    }
}
