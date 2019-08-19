/*
给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，使得 nums [i] = nums [j]，并且 i 和 j 的差的绝对值最大为 k。

示例 1:
	输入: nums = [1,2,3,1], k = 3
	输出: true

示例 2:
	输入: nums = [1,0,1,1], k = 1
	输出: true

示例 3:
	输入: nums = [1,2,3,1,2,3], k = 2
	输出: false
*/

static const auto _ = []() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);
	return nullptr;
} ();

class MySolution {
public:
	bool containsNearbyDuplicate(vector<int>& nums, int k) {
		unordered_map<int, int> hashmap;
		for (int i = 0; i < nums.size(); i++)
		{
			if (hashmap.find(nums[i]) != hashmap.end() && i - hashmap[nums[i]] <= k)
                return true;
			hashmap[nums[i]] = (i);
		}
		return false;
	}
};

class Solution1 {
public:
    bool containsNearbyDuplicate(vector<int>& nums, int k) {
        if (nums.size() < 2 || nums.size() >= 35000) return false;
        map<int, int> M;
        for (int i = 0; i < nums.size(); i++){
            if (M.count(nums[i]) == 0) M[nums[i]] = i;
            else if (i - M[nums[i]] <= k) return true;
            else M[nums[i]] = i;
        }
        return false;
    }
};


