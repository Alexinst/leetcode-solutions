/*
 * https://leetcode-cn.com/problems/move-zeroes/
 
给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

示例:
	输入: [0,1,0,3,12]
	输出: [1,3,12,0,0]

说明:
	1. 必须在原数组上操作，不能拷贝额外的数组。
	2. 尽量减少操作次数。

----------------------------------------------------------------------------------------------------

Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Example:
	Input: [0,1,0,3,12]
	Output: [1,3,12,0,0]
	
Note:
	1. You must do this in-place without making a copy of the array.
	2. Minimize the total number of operations.
*/

/**
 * A holy shit solution wasting me one and a half hours.
 */
class MySolution {
    public void moveZeroes(int[] nums) {
        for (int i = 0, nonZero = 0; i < nums.length && nonZero < nums.length; i++) {
            while (nonZero < nums.length && nums[nonZero] == 0)
                nonZero++;

            if (nonZero < nums.length) {
                int tmp = nums[i];
                nums[i] = nums[nonZero];
                nums[nonZero] = tmp;
                nonZero++;
            }
        }
    }
}

class Solution1 {
    public void moveZeroes(int[] nums) {
        for (int i = 0, j = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
                j++;
            }
        }

    }
}
