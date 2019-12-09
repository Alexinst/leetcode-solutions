/*
 * https://leetcode-cn.com/problems/house-robber-iii/
 *
 * Reference: https://leetcode-cn.com/problems/house-robber-iii/solution/san-chong-fang-fa-jie-jue-shu-xing-dong-tai-gui-hu/

在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。

计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。

示例 1:
	输入: [3,2,3,null,3,null,1]

		 3
		/ \
	   2   3
		\   \ 
		 3   1

	输出: 7 
	解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.

示例 2:
	输入: [3,4,5,1,3,null,1]

	     3
		/ \
	   4   5
	  / \   \ 
	 1   3   1

	输出: 9
	解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.

----------------------------------------------------------------------------------------------------

The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.

Determine the maximum amount of money the thief can rob tonight without alerting the police.

Example 1:
	Input: [3,2,3,null,3,null,1]

		 3
		/ \
	   2   3
		\   \ 
		 3   1

	Output: 7 
	Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
	
Example 2:
	Input: [3,4,5,1,3,null,1]

	     3
		/ \
	   4   5
	  / \   \ 
	 1   3   1

	Output: 9
	Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
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
    public int rob(TreeNode root) {
        int[] money = helper(root);

        return Math.max(money[0], money[1]);
    }

    private int[] helper(TreeNode node) {
        if (node == null) return new int[2];

        int[] money = new int[2];   // 0: 不偷当前节点；1: 偷当前节点 
        int[] left = helper(node.left);
        int[] right = helper(node.right);

        money[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        money[1] = node.val + left[0] + right[0];

        return money;
    }
}

