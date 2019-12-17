/*
 * https://leetcode-cn.com/problems/integer-break/
 
给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。

示例 1:
	输入: 2
	输出: 1
	解释: 2 = 1 + 1, 1 × 1 = 1。
	
示例 2:
	输入: 10
	输出: 36
	解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
	说明: 你可以假设 n 不小于 2 且不大于 58。

----------------------------------------------------------------------------------------------------

Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of those integers. Return the maximum product you can get.

Example 1:
	Input: 2
	Output: 1
	Explanation: 2 = 1 + 1, 1 × 1 = 1.
	
Example 2:
	Input: 10
	Output: 36
	Explanation: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36.
	Note: You may assume that n is not less than 2 and not larger than 58.
*/

class MySolution {
    public int integerBreak(int n) {
        if (n < 2) return 0;
        if (n <= 3) return n - 1;
        if (n == 4) return n;

        int timesOf3 = n / 3;
        int left = n - timesOf3 * 3;
        if (left == 1) {
            timesOf3--;
            left = 4;
        } else if (left == 0) {
            left = 1;
        }

        return (int) Math.pow(3, timesOf3) * left;
    }
}

class Solution1 {
    public int integerBreak(int n) {
        if(n <= 3) return n - 1;
        int a = n / 3, b = n % 3;
        if(b == 0) return (int)Math.pow(3, a);
        if(b == 1) return (int)Math.pow(3, a - 1) * 4;
        return (int)Math.pow(3, a) * 2;
    }
}
