/*
 * https://leetcode-cn.com/problems/diameter-of-binary-tree/

给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过根结点。

示例 :
	给定二叉树

		  1
		 / \
		2   3
	       / \     
	      4   5    
	返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。

	注意：两结点之间的路径长度是以它们之间边的数目表示。

------------------------------------------------------------------------------------

Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

Example:
	Given a binary tree 
		  1
		 / \
		2   3
	       / \     
	      4   5    
	Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].

	Note: The length of path between two nodes is represented by the number of edges between them.
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

    private int max = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null)
            return 0;

        maxDepth(root);
        return max;
    }

    private int maxDepth(TreeNode node) {
        if (node == null)
            return 0;
    
        int left = maxDepth(node.left) + 1;
        int right = maxDepth(node.right) + 1;
        max = Math.max(max, left + right - 2);
        return Math.max(left, right);
    }
}

class Solution1 {
    //设置一个类变量，用于记录最大直径
    private int max = 0;
    
    public int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return max;
    }
    
    private int depth(TreeNode root){
        if(root == null){
            return 0;
        }
        int leftDepth = depth(root.left);
        int rightDepth = depth(root.right);
        //max记录当前的最大直径
        max = Math.max(leftDepth + rightDepth, max);
        //由于我计算的直径是左树高度+右树高度，所以这里返回当前树的高度，以供使用
        return Math.max(leftDepth, rightDepth) + 1;
    }
}
