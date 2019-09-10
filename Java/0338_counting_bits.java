/*
 * https://leetcode-cn.com/problems/counting-bits/ 
 
给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。

示例 1:
	输入: 2
	输出: [0,1,1]

示例 2:
	输入: 5
	输出: [0,1,1,2,1,2]

进阶:
	给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
	要求算法的空间复杂度为O(n)。
	你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount）来执行此操作。

------------------------------------------------------------------------------------------------------

Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.

Example 1:
	Input: 2
	Output: [0,1,1]

Example 2:
	Input: 5
	Output: [0,1,1,2,1,2]

Follow up:
	It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly in a single pass?
	Space complexity should be O(n).
	Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.
*/

/**
 * 思路：动态规划
 *       将 [0, num] 这个区间分为多个 blocks -> [0], [1, 1], [2, 3], [4, 7], [8, 15], [16, 31], ......
 *      
 * [ 1,  1]: 1
 * [ 2,  3]: 1 2
 * [ 4,  7]: 1 2 2 3
 * [ 8, 15]: 1 2 2 3 2 3 3 4
 * [16, 31]: 1 2 2 3 2 3 3 4 2 3 3 4 3 4 4 5
 */
class MySolution {
    public int[] countBits(int num) {
        if (num < 0) return new int[1];

        int[] dp = new int[num + 1];
        dp[0] = 0;
        if (num == 0) return dp;

        int sizeOfBlock = 1;

        for (int i = 1; i < dp.length; i++) {
            dp[i] = dp[i - sizeOfBlock] + 1;

            if (i == sizeOfBlock * 2 - 1)
                sizeOfBlock *= 2;
        }

        return dp;
    }
}

class Solution1 {
    public int[] countBits(int num) {
        int[] res = new int[num + 1];
        res[0] = 0;

        for (int i = 1; i <= num; i++) {
            res[i] = res[i & (i - 1)] + 1;
        }

        return res;
    }
}

