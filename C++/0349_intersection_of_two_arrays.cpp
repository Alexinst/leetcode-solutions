/*
给定两个数组，编写一个函数来计算它们的交集。

示例 1:
	输入: nums1 = [1,2,2,1], nums2 = [2,2]
	输出: [2]

示例 2:
	输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
	输出: [9,4]

说明:
1. 输出结果中的每个元素一定是唯一的。
2. 我们可以不考虑输出结果的顺序。
*/

class MySolution {
private:
	unordered_set<int> intersetion;
public:
	vector<int> intersection(vector<int>& nums1, vector<int>& nums2) {
		for (int num1 : nums2)
		{
			for (int num2 : nums1)
			{
				if (num1 == num2 && intersetion.count(num1) == 0)
					intersetion.insert(num1);
			}
		}

		vector<int> result;
		result.assign(intersetion.begin(), intersetion.end());
		return result;
	}
};

class Solution1 {
public:
    vector<int> intersection(vector<int>& nums1, vector<int>& nums2) {
        if (nums1.size() > nums2.size()) return intersection(nums2, nums1);
        
        vector<int> ret;
        ret.reserve(nums1.size());
        unordered_set<int> mp{nums1.begin(), nums1.end()};
        for (auto iter : nums2) {
            if (mp.erase(iter) == 1) ret.push_back(iter);
        }
        return ret;
    }
};
