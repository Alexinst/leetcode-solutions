/*
 * https://leetcode-cn.com/problems/path-sum-iii/submissions/

给定一个二叉树，它的每个结点都存放着一个整数值。找出路径和等于给定数值的路径总数。

路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。

二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。

示例：
	root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

	      10
	     /  \
	    5   -3
	   / \    \
	  3   2   11
	 / \   \
	3  -2   1

	返回 3。和等于 8 的路径有:

	1.  5 -> 3
	2.  5 -> 2 -> 1
	3.  -3 -> 11

-------------------------------------------------------------------------------------

You are given a binary tree in which each node contains an integer value. Find the number of paths that sum to a given value.

The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

Example:
	root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

	      10
	     /  \
	    5   -3
	   / \    \
	  3   2   11
	 / \   \
	3  -2   1

	Return 3. The paths that sum to 8 are:

	1.  5 -> 3
	2.  5 -> 2 -> 1
	3. -3 -> 11
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
    private int num = 0, sum;
    
    public int pathSum(TreeNode root, int sum) {
        if (root == null)
            return 0;
        
        dig(root, sum);
        pathSum(root.left, sum);
        pathSum(root.right, sum);
        
        return num;
    }
    
    private void dig(TreeNode root, int sum) {
        if (root == null)
            return ;
        
        if (root.val == sum) num++;

        dig(root.left, sum - root.val);
        dig(root.right, sum - root.val);
    }
}

class Solution1 {
    public int pathSum(TreeNode root, int sum) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, 1);
        return helper(root, map, sum, 0);
    }
    
    int helper(TreeNode root, HashMap<Integer, Integer> map, int sum, int pathSum){
        int res = 0;
        if(root == null) return 0;
        
        pathSum += root.val;
        res += map.getOrDefault(pathSum - sum, 0);
        map.put(pathSum, map.getOrDefault(pathSum, 0) + 1);
        res = helper(root.left, map, sum, pathSum) + helper(root.right, map, sum, pathSum) + res;
        map.put(pathSum, map.get(pathSum) - 1);
        return res;
    }
}
