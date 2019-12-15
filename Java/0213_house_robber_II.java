/*
 * https://leetcode-cn.com/problems/house-robber-ii/ 
 
你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。

给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。

示例 1:
	输入: [2,3,2]
	输出: 3
	解释: 你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
	
示例 2:
	输入: [1,2,3,1]
	输出: 4
	解释: 你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
	     偷窃到的最高金额 = 1 + 3 = 4 。

----------------------------------------------------------------------------------------------------

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

Example 1:
	Input: [2,3,2]
	Output: 3
	Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
	             because they are adjacent houses.
	
Example 2:
	Input: [1,2,3,1]
	Output: 4
	Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
	             Total amount you can rob = 1 + 3 = 4.

Tips: Since House[1] and House[n] are adjacent, they cannot be robbed together. Therefore, the problem becomes to rob either House[1]-House[n-1] or House[2]-House[n], depending on which choice offers more money. Now the problem has degenerated to the House Robber, which is already been solved.
*/


class MySolution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        if (nums.length == 3) return Math.max(nums[0], Math.max(nums[1], nums[2]));
        
        int len = nums.length - 1;
        int[] dp = new int[len];
        dp[0] = nums[0];
        dp[1] = nums[1];
        dp[2] = dp[0] + nums[2];
        for (int i = 3; i < len; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 3] + nums[i]);
        }

        int tmp = Math.max(dp[len - 2], dp[len - 1]);
        dp[0] = nums[1];
        dp[1] = nums[2];
        dp[2] = dp[0] + nums[3];
        for (int i = 3; i < len; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i + 1], dp[i - 3] + nums[i + 1]);
        }

        return Math.max(tmp, Math.max(dp[len - 2], dp[len - 1]));
    }
}

class Solution1 {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        // 不偷最后一间
        int p1 = rob2(Arrays.copyOfRange(nums, 0, nums.length - 1));
        // 不偷第一间
        int p2 = rob2(Arrays.copyOfRange(nums, 1, nums.length));
        
        return Math.max(p1, p2);
    }

    private int rob2(int[] num) {
        int prevMax = 0;
        int currMax = 0;
        for (int x : num) {
            int temp = currMax;
            currMax = Math.max(prevMax + x, currMax);
            prevMax = temp;
        }

        return currMax;
    }
}
