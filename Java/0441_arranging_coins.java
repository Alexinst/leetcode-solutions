/*
 *

你总共有 n 枚硬币，你需要将它们摆成一个阶梯形状，第 k 行就必须正好有 k 枚硬币。
给定一个数字 n，找出可形成完整阶梯行的总行数。n 是一个非负整数，并且在32位有符号整型的范围内。

示例 1:
	n = 5

	硬币可排列成以下几行:
	¤
	¤ ¤
	¤ ¤
	因为第三行不完整，所以返回2.

示例 2:
	n = 8

	硬币可排列成以下几行:
	¤
	¤ ¤
	¤ ¤ ¤
	¤ ¤

	因为第四行不完整，所以返回3.

------------------------------------------------------------------------------------------------

You have a total of n coins that you want to form in a staircase shape, where every k-th row must have exactly k coins.

Given n, find the total number of full staircase rows that can be formed. n is a non-negative integer and fits within the range of a 32-bit signed integer.

Example 1:
	n = 5

	The coins can form the following rows:
	¤
	¤ ¤
	¤ ¤

	Because the 3rd row is incomplete, we return 2.
	
Example 2:
	n = 8

	The coins can form the following rows:
	¤
	¤ ¤
	¤ ¤ ¤
	¤ ¤

	Because the 4th row is incomplete, we return 3.
*/

/* n 最大值为 2^31 - 1, 所以层数小于 2^16 (65536)
 * 经过二分查找，最终 left + 1 = right
 * 即 （left * left + left) / 2 < n < (right * right + right) / 2
 * 所以返回 left
 */
class MySolution {
    public int arrangeCoins(int n) {
        int left = 0, right = 65536;  // 2 ^ 16

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            int needed = mid % 2 == 0 ? mid / 2 * (mid + 1) :
                                        (mid + 1) / 2 * mid;
            if (needed == n)
                return mid;
            else if (needed < n)
                left = mid;
            else
                right = mid;
        }

        return left;
    }
}

class Solution1 {
    public int arrangeCoins(int n) {
        return (int) ((Math.sqrt(1 + 8.0 * n) - 1) / 2);
    }
}
