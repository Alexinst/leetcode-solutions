/*
 * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/ 
 *
 * 思路：https://mp.weixin.qq.com/s/O2QB-n_Pp5c7YOgM3ro10w
 
根据一棵树的前序遍历与中序遍历构造二叉树。

注意: 你可以假设树中没有重复的元素。

例如，给出
	前序遍历 preorder = [3,9,20,15,7]
	中序遍历 inorder = [9,3,15,20,7]
	返回如下的二叉树：

	    3
	   / \
	  9  20
	    /  \
	   15   7

------------------------------------------------------------------------------

Given preorder and inorder traversal of a tree, construct the binary tree.

Note: You may assume that duplicates do not exist in the tree.

For example, given
	preorder = [3,9,20,15,7]
	inorder = [9,3,15,20,7]
	Return the following binary tree:

	    3
	   / \
	  9  20
	    /  \
	   15   7
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
class Solution1 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inPos = new HashMap<>();
        for (int i = 0; i < inorder.length; i++)
            inPos.put(inorder[i], i);
        
        return buildTree(preorder, 0, preorder.length - 1, 0, inPos);
    }
    
    private TreeNode buildTree(int[] pre, int preStart, int preEnd, int inStart, 
                               Map<Integer, Integer> inPos) {
        if (preStart > preEnd) return null;
        
        TreeNode root = new TreeNode(pre[preStart]);
        int rootIdx = inPos.get(pre[preStart]);
        int leftLen = rootIdx - inStart;
        root.left = buildTree(pre, preStart + 1, preStart + leftLen, inStart, inPos);
        root.right = buildTree(pre, preStart + leftLen + 1, preEnd, rootIdx + 1, inPos);
        
        return root;
    }
}

class Solution2 {
    private int pre = 0;
    private int in = 0;
    
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTreeHelper(preorder, inorder, (long) Integer.MAX_VALUE + 1);
    }

    private TreeNode buildTreeHelper(int[] preorder, int[] inorder, long stop) {
        //到达末尾返回 null
        if (pre == preorder.length) return null;
        
        //到达停止点返回 null
        //当前停止点已经用了，in 后移
        if (inorder[in] == stop) {
            in++;
            return null;
        }
        int root_val = preorder[pre++];
        TreeNode root = new TreeNode(root_val);
        //左子树的停止点是当前的根节点
        root.left = buildTreeHelper(preorder, inorder, root_val);
        //右子树的停止点是当前树的停止点
        root.right = buildTreeHelper(preorder, inorder, stop);
        
        return root;
    }
}
