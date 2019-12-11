/*
 * https://leetcode-cn.com/problems/sliding-window-maximum/ 
 
给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。

返回滑动窗口中的最大值。

示例:
	输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
	输出: [3,3,5,5,6,7] 
	解释: 

	  滑动窗口的位置                最大值
	---------------               -----
	[1  3  -1] -3  5  3  6  7       3
	 1 [3  -1  -3] 5  3  6  7       3
	 1  3 [-1  -3  5] 3  6  7       5
	 1  3  -1 [-3  5  3] 6  7       5
	 1  3  -1  -3 [5  3  6] 7       6
	 1  3  -1  -3  5 [3  6  7]      7
	 

提示：你可以假设 k 总是有效的，在输入数组不为空的情况下，1 <= k <= 输入数组的大小。

进阶：你能在线性时间复杂度内解决此题吗？

----------------------------------------------------------------------------------------------------

Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.

Example:
	Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
	Output: [3,3,5,5,6,7] 
	Explanation: 

	Window position                Max
	---------------               -----
	[1  3  -1] -3  5  3  6  7       3
	 1 [3  -1  -3] 5  3  6  7       3
	 1  3 [-1  -3  5] 3  6  7       5
	 1  3  -1 [-3  5  3] 6  7       5
	 1  3  -1  -3 [5  3  6] 7       6
	 1  3  -1  -3  5 [3  6  7]      7
	
Note: You may assume k is always valid, 1 <= k <= input array's size for non-empty array.

Follow up: Could you solve it in linear time?
*/

class MySolution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[]{};
        
        int[] vals = new int[nums.length - k + 1];
        for (int i = 0; i <= nums.length - k; i++) {
            vals[i] = max(nums, i, i + k);
        }
        
        return vals;
    }
    
    private int max(int[] nums, int left, int right) {
        int max = Integer.MIN_VALUE;
        while (left < right) {
            max = Math.max(max, nums[left]);
            left++;
        }
        
        return max;
    }
}

class Solution1 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) return new int[0];
        int res[] = new int[nums.length - k + 1];
        int max = Integer.MIN_VALUE;
        int maxIndex = -1;
        for (int i = 0; i <= nums.length - k; i++) {
            if (i <= maxIndex) {

                if (nums[i + k - 1] >= max) {  // 只与新来的比
                    max = nums[i + k - 1];
                    maxIndex = i + k - 1;
                }

                res[i] = max;

            } else {  // 重新寻找
                max = Integer.MIN_VALUE;
                for (int j = i; j < i + k; j++) {
                    if (nums[j] > max) {
                        max = nums[j];
                        maxIndex = j;
                    }
                }
                res[i] = max;
            }
        }

        return res;
    }
}
