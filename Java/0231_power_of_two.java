/*
 * https://leetcode-cn.com/problems/power-of-two/ 
 
给定一个整数，编写一个函数来判断它是否是 2 的幂次方。

示例 1:
	输入: 1
	输出: true
	解释: 2^0 = 1

示例 2:
	输入: 16
	输出: true
	解释: 2^4 = 16

示例 3:
	输入: 218
	输出: false

---------------------------------------------------------------------------------

Given an integer, write a function to determine if it is a power of two.

Example 1:
	Input: 1
	Output: true 
	Explanation: 2^0 = 1

Example 2:
	Input: 16
	Output: true
	Explanation: 2^4 = 16

Example 3:
	Input: 218
	Output: false
*/

class MySolution {
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) return false;
        
        while (n >= 2) {
            if (n % 2 != 0)
                return false;
            n = n / 2;
        }
        
        return true;
    }
}

class Solution1 {
    public boolean isPowerOfTwo(int n) {
        if (n == Integer.MIN_VALUE) return false;
        return n == 0 ? false : (n & (-n)) == n;
    }
}

class Solution2 {
    public boolean isPowerOfTwo(int n) {
        return n > 0 && ((n & (n - 1)) == 0);
    }
}
