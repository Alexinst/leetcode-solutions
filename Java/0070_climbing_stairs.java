/*
 * https://leetcode-cn.com/problems/climbing-stairs/ 

假设你正在爬楼梯。需要 n 阶你才能到达楼顶。

每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？

注意：给定 n 是一个正整数。

示例 1：
	输入： 2
	输出： 2
	解释： 有两种方法可以爬到楼顶。
	1.  1 阶 + 1 阶
	2.  2 阶

示例 2：
	输入： 3
	输出： 3
	解释： 有三种方法可以爬到楼顶。
	1.  1 阶 + 1 阶 + 1 阶
	2.  1 阶 + 2 阶
	3.  2 阶 + 1 阶

-------------------------------------------------------------------------------------

You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Note: Given n will be a positive integer.

Example 1:
	Input: 2
	Output: 2
	Explanation: There are two ways to climb to the top.
	1. 1 step + 1 step
	2. 2 steps

Example 2:
	Input: 3
	Output: 3
	Explanation: There are three ways to climb to the top.
	1. 1 step + 1 step + 1 step
	2. 1 step + 2 steps
	3. 2 steps + 1 step
*/


// 斐波那契数列
class Solution1 {
    public int climbStairs(int n) {
        if (n == 0) return 1;
        if (n == 1 || n == 2) return n;
        
        int first = 1;
        int second = 2;
        int num = 0;
        for (int i = 3; i <= n; i++) {
            num = first + second;
            first = second;
            second = num;
        }
        
        return num;
    }
}

// 动态规划
class Solution2 {
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}

// 记忆化递归
class Solution3 {
    public int climbStairs(int n) {
        int memo[] = new int[n + 1];
        return climb_Stairs(0, n, memo);
    }
    public int climb_Stairs(int i, int n, int memo[]) {
        if (i > n) return 0;
        if (i == n) return 1;
        if (memo[i] > 0) return memo[i];
        
	memo[i] = climb_Stairs(i + 1, n, memo) + climb_Stairs(i + 2, n, memo);
        return memo[i];
    }
}
