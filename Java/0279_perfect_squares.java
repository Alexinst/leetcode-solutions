/*
 * https://leetcode-cn.com/problems/perfect-squares/ 
 *
 * Reference: https://leetcode-cn.com/problems/perfect-squares/solution/hua-jie-suan-fa-279-wan-quan-ping-fang-shu-by-guan/
 
给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。

示例 1:
	输入: n = 12
	输出: 3 
	解释: 12 = 4 + 4 + 4.
	
示例 2:
	输入: n = 13
	输出: 2
	解释: 13 = 4 + 9.

----------------------------------------------------------------------------------------------------

Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

Example 1:
	Input: n = 12
	Output: 3 
	Explanation: 12 = 4 + 4 + 4.

Example 2:
	Input: n = 13
	Output: 2
	Explanation: 13 = 4 + 9.
*/

class Solution1 {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];

        for (int i = 1; i < dp.length; i++) {
            dp[i] = i;
            for (int j = 0; i - j * j >= 0; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }

        return dp[n];
    }
}


class Solution2 {
    public int numSquares(int n) {
        if (square1(n)) {
            return 1;
        } else if (square2(n)) {
            return 2;
        } else if (square3(n)) {
            return 3;
        } else {
            return 4;
        }
    }

    private boolean square3(int n) {
        for (int i = 1; i * i < n; i++) {
            if (square2(n - i * i)) {
                return true;
            }
        }
        return false;
    }

    private boolean square2(int n) {
        for (int i = 1; i * i < n; i++) {
            if (square1(n - i * i)) {
                return true;
            }
        }
        return false;
    }

    private boolean square1(int n) {
        int x = (int) Math.sqrt(n);
        return x * x == n;
    }
}

class Solution3 {
    public int numSquares(int n) {
        while (n % 4 == 0) {
            n /= 4;
        }

        if (n % 8 == 7)  return 4;

        for (int a = 0; a * a <= n; a++) {
            int b = (int) Math.sqrt(n - a * a);
            if (a * a + b * b == n) {
                int p = a > 0 ? 1 : 0;
                int q = b > 0 ? 1 : 0;
                return p + q;
            }
        }

        return 3;
    }
}
