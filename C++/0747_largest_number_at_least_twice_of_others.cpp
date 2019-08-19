/*
在一个给定的数组nums中，总是存在一个最大元素 。

查找数组中的最大元素是否至少是数组中每个其他数字的两倍。

如果是，则返回最大元素的索引，否则返回-1。

示例 1:
	输入: nums = [3, 6, 1, 0]
	输出: 1
	解释: 6是最大的整数, 对于数组中的其他整数,
	6大于数组中其他元素的两倍。6的索引是1, 所以我们返回1.

示例 2:
	输入: nums = [1, 2, 3, 4]
	输出: -1
	解释: 4没有超过3的两倍大, 所以我们返回 -1.

提示:
1. nums 的长度范围在[1, 50].
2. 每个 nums[i] 的整数范围在 [0, 99].
*/

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

class MySolution {
public:
    int dominantIndex(vector<int>& nums);
    int maxNum(vector<int> &nums);
};

int Solution::dominantIndex(vector<int> &nums)
{
    int maxN, maxIndex, i;
    int len = nums.size();
    maxN = maxNum(nums);
    cout << maxN << endl;
    vector<int>::iterator iter = find(nums.begin(), nums.end(), maxN);
    maxIndex = iter - nums.begin();
    nums.erase(iter);

    for (i=0; i<len-1; i++)
    {
        if (maxN < nums[i] * 2)
            break;
    }

    if (i < len-1)
        return -1;
    else
        return maxIndex;
}

int Solution::maxNum(vector<int> &nums)
{
    int maxNum = nums[0];
    int len = nums.size();
    for (int i=1; i<len; i++)
    {
        if (maxNum < nums[i])
            maxNum = nums[i];
    }
    return maxNum;
}

class Solution1 {
public:
    int dominantIndex(vector<int>& nums) {
        int len=nums.size();
        if(len==0)
            return -1;
        if(len==1)
            return 0;
        int max=nums[0];
        int max_index=0;
        for(int i=0;i<len;i++)
        {
            if(nums[i]>max)
            {
                max=nums[i];
                max_index=i;
            }
        }
        sort(nums.begin(),nums.end());
        for(int j=0;j<len-1;j++)
        {
            nums[j]=2*nums[j];
            if(max>=nums[j])
                continue;
            else
                return -1;
        }
        return max_index;
            
    }
};
