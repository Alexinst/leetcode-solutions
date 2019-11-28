/*
 * https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray/ 
 
给定一个整数数组，你需要寻找一个连续的子数组，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。

你找到的子数组应是最短的，请输出它的长度。

示例 1:
	输入: [2, 6, 4, 8, 10, 9, 15]
	输出: 5
	解释: 你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
	
说明:
	1. 输入的数组长度范围在 [1, 10,000]。
	2. 输入的数组可能包含重复元素 ，所以升序的意思是<=。

----------------------------------------------------------------------------------------------------

Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order, too.

You need to find the shortest such subarray and output its length.

Example 1:
	Input: [2, 6, 4, 8, 10, 9, 15]
	Output: 5
	Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
	
Note:
	1. Then length of the input array is in range [1, 10,000].
	2. The input array may contain duplicates, so ascending order here means <=.
*/

class MySolution {
    public int findUnsortedSubarray(int[] nums) {
        int len = nums.length;
        if (len == 1) return 0;

        int i = 1, left = 0;

        // 找到左起的顺序子数组的最高索引
        while (i < len && nums[i - 1] <= nums[i]) {
            left = i;
            i++;
        }
		
		// 如果数组为顺序，则返回 0
        if (left == len - 1) return 0;

        // 继续迭代，保证 nums[left] <= 其右侧所有数
        for (; i < len && left >= 0; i++) {
            while (left >= 0 && nums[left] > nums[i])
                left--;
        }

        int right = len - 1;
        i = len - 1;

        // 找到右起的顺序子数组的最低索引
        while (i > 0 && nums[i - 1] <= nums[i]) {
            right = i - 1;
            i--;
        }

        // 继续迭代，保证 nums[right] >= 其左侧所有数
        for (; i >= 0 && right < len; i--) {
            while (right < len && nums[right] < nums[i])
                right++;
        }

        return right - left - 1;
    }
}

class Solution1 {
    public int findUnsortedSubarray(@NotNull int[] nums) {
        int len = nums.length;
        int max = nums[0];
        int min = nums[len - 1];
        int l = 0, r = -1;
        for (int i = 0; i < len; i++) {
            if (max > nums[i]) {
                r = i;
            } else {
                max = nums[i];
            }
            if (min < nums[len - i - 1]) {
                l = len - i - 1;
            } else {
                min = nums[len - i - 1];
            }
        }
        return r - l + 1;

    }
}

class Solution2 {
    public int findUnsortedSubarray(int[] nums) {
        if (nums.length < 2) return 0;
        int max = nums[0];
        int min = nums[nums.length-1];
        int l = nums.length - 1;
        int r = 0;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[nums.length-1-i]);
            if (nums[i] < max) r = i;
            if (nums[nums.length-1-i] > min) l = nums.length - 1 - i;
        }
        return l > r ? 0 : r - l + 1;
    }
}
