/*
 * https://leetcode-cn.com/problems/first-missing-positive/
 
给定一个未排序的整数数组，找出其中没有出现的最小的正整数。

示例 1:
	输入: [1,2,0]
	输出: 3

示例 2:
	输入: [3,4,-1,1]
	输出: 2

示例 3:
	输入: [7,8,9,11,12]
	输出: 1

说明: 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的空间。

-------------------------------------------------------------------------------------------

Given an unsorted integer array, find the smallest missing positive integer.

Example 1:
	Input: [1,2,0]
	Output: 3

Example 2:
	Input: [3,4,-1,1]
	Output: 2

Example 3:
	Input: [7,8,9,11,12]
	Output: 1
	
Note: Your algorithm should run in O(n) time and uses constant extra space.
*/

/**
 * 思路：https://mp.weixin.qq.com/s/b5g8NZq66iRuaIdVFDa1GQ
 */
class Solution1 {
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0)
            return 1;
        
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            // nums[nums[i] - 1] == nums[i] 这个条件指代两种情况：
            // 1) nums[i] == i + 1
            // 2) nums[j] == j + 1 && j < i && nums[i] == nums[j]           
			if (nums[i] <= 0 || nums[i] > nums.length ||
                    nums[nums[i] - 1] == nums[i]) {
                i++;
                continue;
            }

            swap(nums, nums[i] - 1, i);
        }

        for (int i = 0; i < n; ++i) {
            if (nums[i] != i + 1)
                return i + 1;
        }

        return n + 1;
    }


    public void swap(int[] nums, int a, int b) {
        int t = nums[a];
        nums[a] = nums[b];
        nums[b] = t;
    }
}
