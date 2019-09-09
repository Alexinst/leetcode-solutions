/*
 * https://leetcode-cn.com/problems/bitwise-and-of-numbers-range/  
 
给定范围 [m, n]，其中 0 <= m <= n <= 2147483647，返回此范围内所有数字的按位与（包含 m, n 两端点）。

示例 1: 
	输入: [5,7]
	输出: 4

示例 2:
	输入: [0,1]
	输出: 0

--------------------------------------------------------------------------------------

Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.

Example 1:
	Input: [5,7]
	Output: 4

Example 2:
	Input: [0,1]
	Output: 0
*/

/**
 * 思路：如果 m 和 n 的二进制形式的长度不相同，则按位与的结果为 0。
 *
 * 假设 m 为 7（0111），n 为 9（1001），长度不同（3 和 4）。
 *
 * 低3位的按位与结果为 000，因为从 7 到 9，有发生 向高位进位（即 7（0111）到 8（1001））
 *    所以必然有某个数的低3位全为 0，故而低3位的按位与结果为 0；
 *
 * 高1位的按位与结果为 0，因为 7 和 9 二进制形式的长度不相同，意味着 7 的高位全为 0。
 *
 * 如果数字区间按位与的结果不为 0，则 m 和 n的二进制形式的长度必然相同。
 */
class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        int t = 0;
        while (m != n) {
            m = m >> 1;
            n = n >> 1;
            t++;
            
            if (m == 0 || n == 0) 
                return 0;
        }
        
        return n << t;
    }
}
