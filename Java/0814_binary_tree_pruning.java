/*
 * https://leetcode-cn.com/problems/binary-tree-pruning/

给定二叉树根结点 root ，此外树的每个结点的值要么是 0，要么是 1。

返回移除了所有不包含 1 的子树的原二叉树。( 节点 X 的子树为 X 本身，以及所有 X 的后代。)

示例1:
	https://s3-lc-upload.s3.amazonaws.com/uploads/2018/04/06/1028_2.png
	输入: [1,null,0,0,1]
	输出: [1,null,0,null,1]
	 
	解释: 
	只有红色节点满足条件“所有不包含 1 的子树”。
	右图为返回的答案。

示例2:
	https://s3-lc-upload.s3.amazonaws.com/uploads/2018/04/06/1028_1.png
	输入: [1,0,1,0,0,0,1]
	输出: [1,null,1,null,1]

示例3:
	https://s3-lc-upload.s3.amazonaws.com/uploads/2018/04/05/1028.png
	输入: [1,1,0,1,1,0,1,0]
	输出: [1,1,0,1,1,null,1]

说明:
    1. 给定的二叉树最多有 100 个节点。
    2. 每个节点的值只会为 0 或 1 。

-----------------------------------------------------------------------------------------

We are given the head node root of a binary tree, where additionally every node's value is either a 0 or a 1.

Return the same tree where every subtree (of the given tree) not containing a 1 has been removed. (Recall that the subtree of a node X is X, plus every node that is a descendant of X.)

Example 1:
	https://s3-lc-upload.s3.amazonaws.com/uploads/2018/04/06/1028_2.png
	Input: [1,null,0,0,1]
	Output: [1,null,0,null,1]
 
	Explanation: Only the red nodes satisfy the property "every subtree not containing a 1".
	             The diagram on the right represents the answer.

Example 2:
	https://s3-lc-upload.s3.amazonaws.com/uploads/2018/04/06/1028_1.png
	Input: [1,0,1,0,0,0,1]
	Output: [1,null,1,null,1]

Example 3:
	https://s3-lc-upload.s3.amazonaws.com/uploads/2018/04/05/1028.png
	Input: [1,1,0,1,1,0,1,0]
	Output: [1,1,0,1,1,null,1]

Note:
    1. The binary tree will have at most 100 nodes.
    2. The value of each node will only be 0 or 1.
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
    public TreeNode pruneTree(TreeNode root) {
        hasOne(root);
        return root;
    }
    
    private boolean pruning(TreeNode node) {
        if (node == null)
            return false;
        
        if (!pruning(node.left)) node.left = null;
        if (!pruning(node.right)) node.right = null;
        
        return node.val == 1 || pruning(node.left) || pruning(node.right);
    }
}

class Solution1 {
    public TreeNode pruneTree(TreeNode root) {
        if(pruneTreeCore(root)==0)
            return null;
        else
            return root;
    }

    private int pruneTreeCore(TreeNode root){
        if(root==null){
            return 0;
        }
        int leftCount = pruneTreeCore(root.left);
        int rightCount = pruneTreeCore(root.right);
        if(leftCount==0){
            root.left=null;
        }
        if(rightCount==0){
            root.right=null;
        }
        return root.val==0?(leftCount+rightCount):(leftCount+rightCount+1);
    }
}
