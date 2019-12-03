/*
 * https://leetcode-cn.com/problems/subarray-sum-equals-k/
 *
 * Reference: https://leetcode-cn.com/problems/subarray-sum-equals-k/solution/he-wei-kde-zi-shu-zu-by-leetcode/
 
给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。

示例 1 :
	输入:nums = [1,1,1], k = 2
	输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
	
说明 :
	1. 数组的长度为 [1, 20,000]。
	2. 数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。

----------------------------------------------------------------------------------------------------

Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

Example 1:
	Input:nums = [1,1,1], k = 2
	Output: 2
	
Note:
	1. The length of the array is in range [1, 20,000].
	2. The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
*/

class MySolution {
    public int subarraySum(int[] nums, int k) {
        int[] sums = new int[nums.length];
        int count = 0;
        
        sums[0] = nums[0];
        if (sums[0] == k) count++;
        
        for (int i = 1; i < nums.length; i++) {
            sums[i] = sums[i - 1] + nums[i];
            
            if (sums[i] == k) count++;
            
            for (int j = 0; j < i; j++) {
                if (sums[i] - sums[j] == k)
                    count++;
            }
        }
        
        return count;
    }
}

class Solution1 {
    public int subarraySum(int[] nums, int k) {
        int len = nums.length;
        int[] sum = new int[len + 1];
        Map<Integer, Integer> map = new HashMap<>(len * 2);
        
        for (int i = 0; i < len; i++) {
            sum[i + 1] = nums[i] + sum[i];
        }
        
        int res = 0;
        for (int i = 0; i <= len; i++) {
            int sub = sum[i] - k;
            res += map.getOrDefault(sub, 0);
            map.put(sum[i], map.getOrDefault(sum[i], 0) + 1);
        }
        
        return res;
    }
}
