/*
给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。

示例 1:
	输入: nums = [-1,0,3,5,9,12], target = 9
	输出: 4
	解释: 9 出现在 nums 中并且下标为 4

示例 2:
	输入: nums = [-1,0,3,5,9,12], target = 2
	输出: -1
	解释: 2 不存在 nums 中因此返回 -1

提示：
	你可以假设 nums 中的所有元素是不重复的。
	n 将在 [1, 10000]之间。
	nums 的每个元素都将在 [-9999, 9999]之间。
*/

#include <iostream>
#include <vector>

using std::vector;

class MySolution {
public:
    int search(vector<int>& nums, int target) {
        if (nums.empty())
            return -1;

        int leftIndex = 0;
        int rightIndex = nums.size() - 1;
        while (leftIndex <= rightIndex)
        {
            int middleIndex = (leftIndex + rightIndex) / 2;
            if (target == nums[middleIndex])
                return middleIndex;
            else if (target < nums[middleIndex])
                rightIndex = middleIndex - 1;
            else
                leftIndex = middleIndex + 1;
        }
        return -1;
    }
};

class Solution1 {
// A solution using recursion.
public:
    int bin_search(vector<int>& nums, int target, int left, int right)
    {
        if(nums.empty())
            return -1;

        int mid = (left + right) / 2;
        if(target == nums[mid])
            return mid;
        if(right > left)
        {
            if(target>nums[mid])
            {
                left = mid + 1;
                return bin_search(nums, target, left, right);
            }
            else if(mid == left)
                return -1;
            else
            {
                right = mid - 1;
                return bin_search(nums, target, left, right);
            }
	}
        return -1;
    }

    int search(vector<int>& nums, int target) {
        return bin_search(nums, target, 0, nums.size()-1);
    }
};
