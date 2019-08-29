/*
 * https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/ 
 
根据一棵树的中序遍历与后序遍历构造二叉树。

注意: 你可以假设树中没有重复的元素。

例如，给出
	中序遍历 inorder = [9,3,15,20,7]
	后序遍历 postorder = [9,15,7,20,3]
	返回如下的二叉树：

	    3
	   / \
	  9  20
	    /  \
	   15   7

---------------------------------------------------------------------------------------

Given inorder and postorder traversal of a tree, construct the binary tree.

Note: You may assume that duplicates do not exist in the tree.

For example, given
	inorder = [9,3,15,20,7]
	postorder = [9,15,7,20,3]
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
class MySolution {
    private int in, post;
    
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        in = inorder.length - 1;
        post = postorder.length - 1;
        return buildTree(inorder, postorder, (long) Integer.MIN_VALUE - 1);
    }
    
    private TreeNode buildTree(int[] inorder, int[] postorder, long stopVal) {
        // 中断条件：到达二叉树的最左节点
        if (post == -1) return null;
        
        // 中断条件（对二叉树的无子的节点，除最左节点）：
        //     对于右节点：stopVal 为 父节点   的值
        //     对于左节点：stopVal 为 祖父节点 的值
        if (inorder[in] == stopVal) {
            in--;
            return null;
        }
        
        int rootVal = postorder[post--];
        TreeNode root = new TreeNode(rootVal);
        root.right = buildTree(inorder, postorder, rootVal);
        root.left = buildTree(inorder, postorder, stopVal);
        
        return root;
    }
}

class Solution1 {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null)
            return null;
        HashMap<Integer, Integer> map = new HashMap();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return helper(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1, map);
    }

    public TreeNode helper(int[] inorder, int instart, int inend, int[] posorder, int posstart, int posend, HashMap<Integer, Integer> map) {
        if (instart > inend || posstart > posend)
            return null;
        TreeNode root = new TreeNode(posorder[posend]);
        int inIndex = map.get(posorder[posend]);
        root.left = helper(inorder, instart, inIndex - 1, posorder, posstart, posstart + inIndex - instart - 1, map);
        root.right = helper(inorder, inIndex + 1, inend, posorder, posstart + inIndex - instart, posend - 1, map);

        return root;
    }
}


class Solution2 {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return build(inorder, postorder, 0, postorder.length - 1, postorder.length);
    }

    public static TreeNode build(int[] inorder, int[] postorder, int inStart, int postEnd, int length) {
        if (length == 0) return null;
        int root = postorder[postEnd];
        TreeNode treeNode = new TreeNode(root);
        if (length == 1) return treeNode;
        for (int i = length - 1; i >= 0; i--) {
            if (root == inorder[inStart + i]) {
                treeNode.left = build(inorder, postorder, inStart, postEnd - length + i, i);
		// 注意这里的postEnd-length+i不等于i,数组长度变为子数组长度，i的下标已经改变，i为左子树长度，在postorder中为左子树于右子树的分界点
                treeNode.right = build(inorder, postorder, inStart + i + 1, postEnd - 1, length - 1 - i);
                return treeNode;
            }
        }
        return null;
    }
}
