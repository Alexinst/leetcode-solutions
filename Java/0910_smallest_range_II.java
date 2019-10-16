/*
 * https://leetcode-cn.com/problems/smallest-range-ii/
 
给定一个整数数组 A，对于每个整数 A[i]，我们可以选择 x = -K 或是 x = K，并将 x 加到 A[i] 中。

在此过程之后，我们得到一些数组 B。

返回 B 的最大值和 B 的最小值之间可能存在的最小差值。
 

示例 1：
	输入：A = [1], K = 0
	输出：0
	解释：B = [1]

示例 2：
	输入：A = [0,10], K = 2
	输出：6
	解释：B = [2,8]

示例 3：
	输入：A = [1,3,6], K = 3
	输出：3
	解释：B = [4,6,3]

提示：
    1. 1 <= A.length <= 10000
    2. 0 <= A[i] <= 10000
    3. 0 <= K <= 10000

----------------------------------------------------------------------------------------------------

Given an array A of integers, for each integer A[i] we need to choose either x = -K or x = K, and add x to A[i] (only once).

After this process, we have some array B.

Return the smallest possible difference between the maximum value of B and the minimum value of B.
 

Example 1:
	Input: A = [1], K = 0
	Output: 0
	Explanation: B = [1]

Example 2:
	Input: A = [0,10], K = 2
	Output: 6
	Explanation: B = [2,8]

Example 3:
	Input: A = [1,3,6], K = 3
	Output: 3
	Explanation: B = [4,6,3]

Note:
    1. 1 <= A.length <= 10000
    2. 0 <= A[i] <= 10000
    3. 0 <= K <= 10000
*/


/**
 * 思路：缩小 A 的区间
 *       记 A 的长度为 len
 *       A 的前一部分 (0 <= i <= len) 同加 K，后一部分 (0 <= j <= len) 同减 K (i = j or i + 1 = j) 。
 */
class Solution1 {
	public int smallestRangeII(int[] A, int K) {
		int len = A.length;
		if (len == 1) return 0;

		Arrays.sort(A);
		int ans = A[len - 1] - A[0];
		for (int i = 0; i < len - 1; i++) {
			int min = Math.min(A[0] + K, a[i + 1] - K);
			int max = Math.max(A[len - 1] - K, A[i] + K);
			ans = Math.min(ans, max - min);
		}

		return ans;
	}
}
