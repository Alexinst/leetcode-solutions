/*
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/ 
 
给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格。

设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:

你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。

示例:
	输入: [1,2,3,0,2]
	输出: 3 
	解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]

----------------------------------------------------------------------------------------------------

Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:

You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)

Example:
	Input: [1,2,3,0,2]
	Output: 3 
	Explanation: transactions = [buy, sell, cooldown, buy, sell]
*/

class Solution1 {
    public int maxProfit(int[] prices) {
        if (prices.length < 2) return 0;

        int[][] dp = new int[prices.length][2];  // dp[i][0] 是买入，dp[i][1] 是卖出
        dp[0][0] = -prices[0];
        dp[1][0] = Math.max(dp[0][0], dp[0][1] - prices[1]);
        dp[1][1] = Math.max(dp[0][1], dp[0][0] + prices[1]);

        for (int i = 2; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 2][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        }

        return dp[prices.length - 1][1];
    }
}

class Solution2 {
    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
            int dpi_0 = 0, dpi_1 = Integer.MIN_VALUE, dpi2_0 = 0;
            for (int price : prices) {
                int tmp = dpi_0;
                dpi_0 = Math.max(dpi_0, dpi_1 + price);
                dpi_1 = Math.max(dpi_1, dpi2_0 - price);
                dpi2_0 = tmp;
            }
            return dpi_0;
    }
}
