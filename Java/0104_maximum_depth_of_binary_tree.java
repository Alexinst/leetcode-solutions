/*
给定一个二叉树，找出其最大深度。
二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。

说明: 叶子节点是指没有子节点的节点。

示例：
	给定二叉树 [3,9,20,null,null,15,7]，
    	3
	   / \
  	  9  20
        /  \
   	   15   7
	返回它的最大深度 3 。
*/

// Definition for a binary tree node.
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class MySolution {
    private int answer = 0;
    public int maxDepth(TreeNode root) {
        maximum(root, 1);
        return answer;
    }
    
    private void maximum(TreeNode node, int depth)
    {
        if (node == null)
            return;
        
        if (node.left == null && node.right == null)
            answer = answer > depth ? answer : depth;
        maximum(node.left, depth + 1);
        maximum(node.right, depth + 1);
    }
}

class Solution1 {
    public int maxDepth(TreeNode root) {
        if(root==null){
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
