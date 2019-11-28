/*
 * https://leetcode-cn.com/problems/hamming-distance/

两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。

给出两个整数 x 和 y，计算它们之间的汉明距离。

注意：0 ≤ x, y < 231.

示例:
	输入: x = 1, y = 4
	输出: 2
	解释:
	1   (0 0 0 1)
	4   (0 1 0 0)
		   ↑   ↑

	上面的箭头指出了对应二进制位不同的位置。

----------------------------------------------------------------------------------------------------

The Hamming distance between two integers is the number of positions at which the corresponding bits are different.

Given two integers x and y, calculate the Hamming distance.

Note: 0 ≤ x, y < 2^31.

Example:
	Input: x = 1, y = 4
	Output: 2
	Explanation:
	1   (0 0 0 1)
	4   (0 1 0 0)
		   ↑   ↑

	The above arrows point to positions where the corresponding bits are different.
*/

class MySolution {
    public int hammingDistance(int x, int y) {
        int tmp = x ^ y;
        int dis = 0;

        while (tmp > 0) {
            dis += (tmp % 2);
            tmp = tmp >> 1;
        }

        return dis;
    }
}

class Solution1 {
    public int hammingDistance(int x, int y) {
        int count = 0;
        int z = x ^ y;
        while (z > 0) {
            if (z % 2 == 1) {
                count++;
            }
            z = z / 2;
        }
        return count;
    }
}
