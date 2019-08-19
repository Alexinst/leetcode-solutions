/*
给定长度为 2n 的数组, 你的任务是将这些数分成 n 对, 例如 (a1, b1), (a2, b2), ..., (an, bn) ，使得从1 到 n 的 min(ai, bi) 总和最大。

示例 1:
	输入: [1,4,3,2]
	输出: 4
	解释: n 等于 2, 最大总和为 4 = min(1, 2) + min(3, 4).

提示:
1. n 是正整数,范围在 [1, 10000].
2. 数组中的元素范围在 [-10000, 10000].
*/

class MySolution {
public:
    int arrayPairSum(vector<int>& nums);
};

int Solution::arrayPairSum(vector<int>& nums)
{
    sort(nums.begin(), nums.end());
    vector<int>::iterator iter = nums.begin();
    long sum = 0;
    for(int i=0; iter<nums.end(); i++)
    {
        sum += *iter;
        iter += 2;
    }
    
    return sum;
}

class Solution1 {
public:
    int arrayPairSum(vector<int>& nums) {
        vector<int> v(20001, 0);
        for (int i=0; i<nums.size(); ++i) {
            int num = nums[i];
            v[num+10000]++;
        }
        int res = 0;
        int match = true;
        for (int key=0; key<v.size(); ++key) {
            int count = v[key];
            if (count == 0)
                continue;
            if (!match) {
                count--;
                match = true;
            }
            res += (key - 10000) * (count / 2);
            if (count % 2 == 1) {
                res += (key - 10000);
                match = false;
            }
        }
        return res;
    }
};
