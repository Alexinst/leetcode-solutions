/*
 *
 
给定一个非空二叉树, 返回一个由每层节点平均值组成的数组.

示例 1:
	输入:
	    3
	   / \
	  9  20
	    /  \
	   15   7

	输出: [3, 14.5, 11]
	
	解释:
	第0层的平均值是 3,  第1层是 14.5, 第2层是 11. 因此返回 [3, 14.5, 11].
	
注意：节点值的范围在32位有符号整数范围内。

------------------------------------------------------------------------------

Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array.

Example 1:
	Input:
	    3
	   / \
	  9  20
	    /  \
	   15   7
	
	Output: [3, 14.5, 11]

	Explanation:
	The average value of nodes on level 0 is 3,  on level 1 is 14.5, and on level 2 is 11. Hence return [3, 14.5, 11].
	
Note: The range of node's value is in the range of 32-bit signed integer.
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
/**
 * 思路：
 * 进行广度优先搜索，统计每层节点之平均值以及每层节点个数
 */
class MySolution {
    // 每层节点的平均值
    private List<Double> avgs = new ArrayList<>();
    // 每层节点个数
    private List<Integer> nums = new ArrayList<>();

    public List<Double> averageOfLevels(TreeNode root) {
        bfs(root, 0);

        return avgs;
    }

    private void bfs(TreeNode node, int l) {
        if (node == null)
            return ;

        if (l + 1 <= avgs.size()) {
            avgs.set(l, (avgs.get(l) * nums.get(l) + node.val) / (nums.get(l) + 1));
            nums.set(l, nums.get(l) + 1);
        } else {
            avgs.add(l, (double) node.val);
            nums.add(l, 1);
        }
        bfs(node.left, l + 1);
        bfs(node.right, l + 1);
    }
}

class Solution1 {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            int size = stack.size();
            double temp = 0;
            for (int i = 0; i < size; i++) {
                TreeNode pop = stack.removeFirst();
                temp += pop.val;
                if (pop.left != null) {
                    stack.add(pop.left);
                }
                if (pop.right != null){
                    stack.add(pop.right);
                }
            }
            res.add(temp / size);
        }
        return res;
    }
}
