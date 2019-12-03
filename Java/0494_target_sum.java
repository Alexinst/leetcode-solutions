/*
 * https://leetcode-cn.com/problems/target-sum/
 *
 * Reference:
 
给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。

返回可以使最终数组和为目标数 S 的所有添加符号的方法数。

示例 1:
	输入: nums: [1, 1, 1, 1, 1], S: 3
	输出: 5
	
	解释: 
	-1+1+1+1+1 = 3
	+1-1+1+1+1 = 3
	+1+1-1+1+1 = 3
	+1+1+1-1+1 = 3
	+1+1+1+1-1 = 3

	一共有5种方法让最终目标和为3。
	
注意:
	1. 数组非空，且长度不会超过20。
	2. 初始的数组的和不会超过1000。
	3. 保证返回的最终结果能被32位整数存下。

----------------------------------------------------------------------------------------------------

You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.

Find out how many ways to assign symbols to make sum of integers equal to target S.

Example 1:
	Input: nums is [1, 1, 1, 1, 1], S is 3. 
	Output: 5

	Explanation: 
	-1+1+1+1+1 = 3
	+1-1+1+1+1 = 3
	+1+1-1+1+1 = 3
	+1+1+1-1+1 = 3
	+1+1+1+1-1 = 3

	There are 5 ways to assign symbols to make the sum of nums be target 3.
	
Note:
	1. The length of the given array is positive and will not exceed 20.
	2. The sum of elements in the given array will not exceed 1000.
	3. Your output answer is guaranteed to be fitted in a 32-bit integer.
*/

// 深度优先搜索
class MySolution {
    public int findTargetSumWays(int[] nums, int S) {
        if (nums.length == 1)
            return nums[0] == S || nums[0] == -S ? 1 : 0;

        return helper(nums, 0, S);
    }

    private int helper(int[] nums, int idx, int S) {
        if (idx == nums.length) {
            return S == 0 ? 1 : 0;
        }

        return helper(nums, idx + 1, S - nums[idx]) +
               helper(nums, idx + 1, S + nums[idx]);
    }
}

// 动态规划
class Solution1 {
    public int findTargetSumWays(int[] nums, int S) {
        int[] dp = new int[2001];
        dp[nums[0] + 1000] = 1;
        dp[-nums[0] + 1000] += 1;
        for (int i = 1; i < nums.length; i++) {
            int[] next = new int[2001];
            for (int sum = -1000; sum <= 1000; sum++) {
                if (dp[sum + 1000] > 0) {
                    next[sum + nums[i] + 1000] += dp[sum + 1000];
                    next[sum - nums[i] + 1000] += dp[sum + 1000];
                }
            }
            dp = next;
        }
        return S > 1000 ? 0 : dp[S + 1000];
    }
}

class Solution2 {
    public int findTargetSumWays(int[] nums, int S) {
        int sum = compuateSum(nums);
        if (sum < S || (sum + S) % 2 != 0) return 0;
        int v = (sum + S) / 2;
        int[] dp = new int[v + 1];
        dp[0] = 1;

        for (int num : nums) {
            for (int j = v; j >= num; j--) {
                dp[j] = dp[j] + dp[j - num];
            }
        }
        return dp[v];
    }

    private int compuateSum(int[] nums) {
        int sum = 0;
        for (int num : nums)
            sum += num;
        return sum;
    }
}
