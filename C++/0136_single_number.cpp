/*
给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。

说明：
	你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？

示例 1:
	输入: [2,2,1]
	输出: 1

示例 2:
	输入: [4,1,2,1,2]
	输出: 4
*/

class MySolution {
public:
    int singleNumber(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        for (int i = 0; i < nums.size()-1; i++)
        {
            if (i == nums.size()-1)
                break;
            if (nums[i] == nums[i+1])
                i++;
            else
                return nums[i];
        }
        return nums[nums.size()-1];
    }
};

class Solution1 {
public:
    int singleNumber(vector<int>& nums) {
        // XOR a ^ a = 0
        int a = nums[0];
        for (auto i = 1; i < nums.size(); i++) {
            a = a ^ nums[i];
        }
        return a;
    }
};
