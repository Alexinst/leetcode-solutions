/*
 * https://leetcode-cn.com/problems/leaf-similar-trees/
 
请考虑一颗二叉树上所有的叶子，这些叶子的值按从左到右的顺序排列形成一个 叶值序列 。

	https://s3-lc-upload.s3.amazonaws.com/uploads/2018/07/16/tree.png	

举个例子，如上图所示，给定一颗叶值序列为 (6, 7, 4, 9, 8) 的树。如果有两颗二叉树的叶值序列是相同，那么我们就认为它们是 叶相似 的。

如果给定的两个头结点分别为 root1 和 root2 的树是叶相似的，则返回 true；否则返回 false 。

提示：
	给定的两颗树可能会有 1 到 100 个结点。

-------------------------------------------------------------------------------------

Consider all the leaves of a binary tree.  From left to right order, the values of those leaves form a leaf value sequence.

	https://s3-lc-upload.s3.amazonaws.com/uploads/2018/07/16/tree.png

For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8). Two binary trees are considered leaf-similar if their leaf value sequence is the same.

Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.

Note:
	Both of the given trees will have between 1 and 100 nodes.
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
    
    private List<Integer> leaves1 = new ArrayList<>();
    private List<Integer> leaves2 = new ArrayList<>();
    
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        preorderTraversal(root1, 1);
        preorderTraversal(root2, 2);
        
        if (leaves1.size() != leaves2.size())
            return false;
        
        for (int i = 0; i < leaves1.size(); i++) {
            if (leaves1.get(i) != leaves2.get(i))
                return false;
        }
        
        return true;
    }
    
    private void preorderTraversal(TreeNode node, int i) {
        if (node == null)
            return;
        if (node.left == null && node.right == null) {
            if (i == 1) 
                leaves1.add(node.val);
            else 
                leaves2.add(node.val);
        }
        
        preorderTraversal(node.left, i);
        preorderTraversal(node.right, i);
    }
}

class Solution1 {
    private StringBuilder sb1 = new StringBuilder();
    private StringBuilder sb2 = new StringBuilder();
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        helper(root1,sb1);
        helper(root2,sb2);
       
        return sb1.toString().equals(sb2.toString()); 
    }
    void helper(TreeNode root,StringBuilder sb){
        if(root ==null) return;
        if(root.left == null && root.right==null){
            sb.append(root.val);
        }
        helper(root.left,sb);
        helper(root.right,sb);
    }
}
