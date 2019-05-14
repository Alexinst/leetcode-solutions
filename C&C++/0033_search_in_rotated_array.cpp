/*
假设按照升序排序的数组在预先未知的某个点上进行了旋转。
( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。

你可以假设数组中不存在重复的元素。
你的算法时间复杂度必须是 O(log n) 级别。

示例 1:
    输入: nums = [4,5,6,7,0,1,2], target = 0
    输出: 4

示例 2:
    输入: nums = [4,5,6,7,0,1,2], target = 3
    输出: -1
*/

#include <iostream>
#include <vector>

using std::vector;

class MySolution {
public:
	int search(vector<int>& nums, int target)
	{
		if (nums.size() == 0)
			return -1;
		
		int left = 0, right = nums.size() - 1;
		int index = -1;
		bool isRotated = false;
		if (nums[left] > nums[right])
			isRotated = true;

		if (!isRotated)
		{
			if (nums[left] <= target && target <= nums[right])
				index = binarySearch(nums, left, right, target);
			else
				return -1;
		}

		else
		{	
			// rotated: 旋转点左段的最大值的索引
			int rotated = findRotated(nums);
			if (nums[left] <= target && target <= nums[rotated])
				index = binarySearch(nums, left, rotated, target);
			else if (nums[rotated + 1] <= target && target <= nums[right])
				index = binarySearch(nums, rotated + 1, right, target);
			else
				return -1;
		}
		return index;
	}

	int binarySearch(vector<int>& nums, int left, int right, int target)
	{
		while (left <= right)
		{
			if (target < nums[left] || target > nums[right])
				return -1;

			int middle = left + (right - left) / 2;
			if (target == nums[middle])
				return middle;
			else if (target < nums[middle])
				right = middle - 1;
			else
				left = middle + 1;
		}
		return -1;
	}

	int findRotated(vector<int>& nums)
	{

		int left = 0, right = nums.size() - 1;
		while (true)
		{
			int middle = left + (right - left) / 2;
			if (left == middle)
				return left;

			if (nums[left] <= nums[middle])
				left = middle;
			else
				right = middle;
		}
	}
};

class Solution1 {
public:
	int search(vector<int>& nums, int target) {
		int len = nums.size();
		int lo = 0;
		int hi = len - 1;
		while (lo <= hi) {
			int mid = lo + ((hi - lo) >> 1);
			if (nums[mid] == target)
				return mid;
			if (nums[lo] <= nums[mid]) {
				if (nums[lo] <= target && target <= nums[mid])
					hi = mid - 1;
				else
					lo = mid + 1;
			}
			else {
				if (nums[mid] <= target && target <= nums[hi])
					lo = mid + 1;
				else
					hi = mid - 1;
			}
		}

		return -1;
	}
};
