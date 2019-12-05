/*
 * https://leetcode-cn.com/problems/coin-change/ 
 *
 * Reference: https://leetcode-cn.com/problems/coin-change/solution/ling-qian-dui-huan-by-leetcode/
 
给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。

示例 1:
	输入: coins = [1, 2, 5], amount = 11
	输出: 3 
	解释: 11 = 5 + 5 + 1
	
示例 2:
	输入: coins = [2], amount = 3
	输出: -1
	
说明: 你可以认为每种硬币的数量是无限的。

----------------------------------------------------------------------------------------------------

You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:
	Input: coins = [1, 2, 5], amount = 11
	Output: 3 
	Explanation: 11 = 5 + 5 + 1
	
Example 2:
	Input: coins = [2], amount = 3
	Output: -1
	
Note: You may assume that you have an infinite number of each kind of coin.
*/

class MySolution {
    public int coinChange(int[] coins, int amount) {
        if (amount < 1) return 0;

        int[] dp = new int[amount + 1];
        for (int i = 0; i < dp.length; i++) {
            int min = 0;
            
            for (int coin : coins) {
                if (i - coin == 0 || i - coin > 0 && dp[i - coin] > 0) {
                    if (min == 0)
                        min = dp[i - coin] + 1;
                    else
                        min = Math.min(min, dp[i - coin] + 1);
                }
            }
            
            dp[i] = min;
        }

        return dp[amount] > 0 ? dp[amount] : -1;
    }
}

class Solution1 {
    private int res = Integer.MAX_VALUE;

    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        helper(coins, coins.length - 1, 0, amount);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private void helper(int[] coins, int idx, int cur, int amount) {
        if (idx < 0) return;

        if (amount % coins[idx] == 0) {
            res = Math.min(res, cur + amount / coins[idx]);
            return;
        }

        for (int i = amount / coins[idx]; i >= 0; i--) {
            if (cur + i >= res - 1)
                break;

            helper(coins, idx - 1, cur + i, amount - i * coins[idx]);
        }
    }
}
