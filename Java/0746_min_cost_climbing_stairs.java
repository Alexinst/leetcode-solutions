/*
数组的每个索引做为一个阶梯，第 i个阶梯对应着一个非负数的体力花费值 cost[i](索引从0开始)。

每当你爬上一个阶梯你都要花费对应的体力花费值，然后你可以选择继续爬一个阶梯或者爬两个阶梯。

您需要找到达到楼层顶部的最低花费。在开始时，你可以选择从索引为 0 或 1 的元素作为初始阶梯。

示例 1:
	输入: cost = [10, 15, 20]
	输出: 15
	解释: 最低花费是从cost[1]开始，然后走两步即可到阶梯顶，一共花费15。

 示例 2:
	输入: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
	输出: 6
	解释: 最低花费方式是从cost[0]开始，逐个经过那些1，跳过cost[3]，一共花费6。

注意：
1. cost 的长度将会在 [2, 1000]。
2. 每一个 cost[i] 将会是一个Integer类型，范围为 [0, 999]。
*/

class MySolution {
    public int minCostClimbingStairs(int[] cost) {
        int steps = cost.length;
        int[] dp = new int[steps];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < steps; i++) {
            dp[i] = Math.min(dp[i - 2], dp[i - 1]) + cost[i];
        }
        
        return Math.min(dp[steps - 2], dp[steps - 1]);
    }
}

class Solution1 {
    public int minCostClimbingStairs(int[] cost) {
        if(cost.length < 2) return cost[0];
        int a = 0, b = 0;
        for(int i = 0; i < cost.length; i++) {
            int tmp = a;
            a = cost[i] + Math.min(a, b);
            b = tmp;
        }
        return Math.min(a, b);
    }
}
