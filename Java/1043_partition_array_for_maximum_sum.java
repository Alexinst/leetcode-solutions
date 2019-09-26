/*
 * https://leetcode-cn.com/problems/partition-array-for-maximum-sum/
 *
 * Reference: https://www.zhangjc.site/archives/356.html

给出整数数组 A，将该数组分隔为长度最多为 K 的几个（连续）子数组。分隔完成后，每个子数组的中的值都会变为该子数组中的最大值。
返回给定数组完成分隔后的最大和。

示例：
	输入：A = [1,15,7,9,2,5,10], K = 3
	输出：84
	解释：A 变为 [15,15,15,9,10,10,10]

提示：
	1. 1 <= K <= A.length <= 500
	2. 0 <= A[i] <= 10^6

----------------------------------------------------------------------------------------------------

Given an integer array A, you partition the array into (contiguous) subarrays of length at most K.  After partitioning, each subarray has their values changed to become the maximum value of that subarray.

Return the largest sum of the given array after partitioning.

Example 1:
	Input: A = [1,15,7,9,2,5,10], K = 3
	Output: 84
	Explanation: A becomes [15,15,15,9,10,10,10]

Note:
	1. 1 <= K <= A.length <= 500
	2. 0 <= A[i] <= 10^6
*/

class MySolution {
    public int maxSumAfterPartitioning(int[] A, int K) {
        int len = A.length;
		// 如果数组长度小于等于 K，最大和就是数组长度 len × 数组最大值
        if (len <= K) return len * searchMax(A, 0, len);

        int[] dp = new int[len];
        dp[0] = A[0];
        int i = 1;
        // 1 <= i < K
        while (i < K) {
            dp[i] = (i + 1) * Math.max(A[i], dp[i - 1] / i);
            i++;
        }

        // K <= i < len
        // dp[i] = max{ dp[i-j] + max{ A[i-j+1], A[i] } * j }
        for (; i < len; i++){
            int maxVal = A[i];
            for (int j = 1; j <= K; j++) {
                maxVal = Math.max(maxVal, A[i - j + 1]);
                dp[i] = Math.max(dp[i], dp[i - j] + maxVal * j);
            }
        }

        return dp[len - 1];
    }

    private int searchMax(int[] arr, int start, int end) {
        // 搜索数组 arr 在 [start, end) 区间的最大值
        int max = Integer.MIN_VALUE;
        for (int i = start; i < end; i++) {
            max = Math.max(max, arr[i]);
        }

        return max;
    }
}

class Solution1 {
    public int maxSumAfterPartitioning(int[] A, int K) {
        int dp[] = new int[A.length + 1];
        for (int i = 1; i <= A.length; i++) {
            int maxValue = A[i - 1];
            for (int j = 1; j <= K; j++) {
                if (i - j >= 0) {
                    maxValue = Math.max(maxValue, A[i - j]);
                    dp[i] = Math.max(dp[i], dp[i - j] + maxValue * j);
                }
            }
        }
        return dp[A.length];
    }
}
