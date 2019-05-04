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
