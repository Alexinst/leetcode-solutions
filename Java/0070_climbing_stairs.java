/*

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
