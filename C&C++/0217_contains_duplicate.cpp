/*
给定一个整数数组，判断是否存在重复元素。
如果任何值在数组中出现至少两次，函数返回 true。如果数组中每个元素都不相同，则返回 false。

示例 1:
	输入: [1,2,3,1]
	输出: true

示例 2:
	输入: [1,2,3,4]
	输出: false

示例 3:
	输入: [1,1,1,3,3,4,3,2,4,2]
	输出: true
*/

class MySolution {
private:
	unordered_set<int> hashset;
public:
	bool containsDuplicate(vector<int>& nums) {
		for (auto num : nums)
		{
			if (hashset.count(num) > 0)
				return true;
			hashset.insert(num);
		}
		return false;
	}
};

class Solution1 {
public:
    bool containsDuplicate(vector<int>& nums) {
        sort(nums.begin(),nums.end());
        for(int i = 1; i < nums.size();i++){
            if(nums[i] == nums[i - 1])
                return true;
        }
        return false;
    }
        
};

static const auto kSpeedUp = []() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);
	return nullptr;
}();
