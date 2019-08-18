/*
 * https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/

给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。

例如：
	给定二叉树 [3,9,20,null,null,15,7],

		3
	   / \
	  9  20
		/  \
	   15   7

	返回锯齿形层次遍历如下：

	[
	  [3],
	  [20,9],
	  [15,7]
	]

-------------------------------------------------------------------------------------------------------

Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
	Given binary tree [3,9,20,null,null,15,7],

		3
	   / \
	  9  20
		/  \
	   15   7

	return its zigzag level order traversal as:

	[
	  [3],
	  [20,9],
  	  [15,7]
	]
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
    private List<List<Integer>> list;
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        list = new ArrayList<>();
        traversal(root, 0);
        return list;
    }
    
    private void traversal(TreeNode node, int level) {
        if (node != null) {
            if (list.size() == level) 
                list.add(new ArrayList<>());
            
            if (level % 2 == 0)
                list.get(level).add(node.val);
            else
                list.get(level).add(0, node.val);
            
            traversal(node.left, level + 1);
            traversal(node.right, level + 1);
        }
    }
}


