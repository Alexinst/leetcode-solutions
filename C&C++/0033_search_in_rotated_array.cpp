

#include <iostream>
#include <vector>

using std::vector;

class Solution {
public:
    int search(vector<int>& nums, int target) {
        if (nums.size() < 1)
            return -1;

        int left = 0, right = nums.size() - 1;
        
        while(true)
        {
            int middle = left + (right - left) / 2;
            if (target == nums[middle])
                return middle;
            
            if (left == right)
                return -1;
            
            if (target < nums[middle])             // 寻找值小于中间值
            {
                if (nums[left] < nums[middle])      // 旋转点不在左边
                {
                    if (nums[left] < target)
                        right = middle - 1;
                    else if (nums[left] > target)
                        left = middle + 1;
                    else
                        return left;
                }
                else                                // 旋转点在左边
                    right = middle - 1;
            }
            
            else                                        // 中间值小于寻找值
            {
                if (nums[middle] < nums[right])         // 旋转点不在右边
                {
                    if (target < nums[right])
                        left = middle + 1;
                    else if (nums[right] < target)
                        right = middle - 1;
                    else
                        return right;
                }
                else                                    // 旋转点在右边
                    left = middle + 1;
            }
        }
    }
};

