/*
给定一个非空的整数数组，返回其中出现频率前 k 高的元素。

示例 1:
	输入: nums = [1,1,1,2,2,3], k = 2
	输出: [1,2]

示例 2:
	输入: nums = [1], k = 1
	输出: [1]

说明：
1. 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
2. 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
*/

typedef pair<int, int> PAIR;
struct CmpByValue {
    bool operator() (const PAIR& lhs, const PAIR& rhs)
    {
        return lhs.second > rhs.second;
    }
};

class MySolution {
public:
    vector<int> topKFrequent(vector<int>& nums, int k) {
        map<int, int> hashmap;
        vector<int> res;
        
        for (int n : nums)
            hashmap[n]++;
        
        vector<PAIR> pairVec(hashmap.begin(), hashmap.end());
        sort(pairVec.begin(), pairVec.end(), CmpByValue());
        for(int i = 0; i < k; i++)
        {
            res.push_back(pairVec[i].first);
        }
        
        return res;
    }
};

class Solution1 {
public:
    vector<int> topKFrequent(vector<int>& nums, int k) {
        unordered_map<int,int> m;
        for(auto i:nums){
            m[i]++;
        }
        multimap<int,int,greater<int>> s;
        for(auto i:m){
            s.insert(make_pair(i.second,i.first));
        }
        vector<int> res;
        for(auto i:s){
            if(res.size()==k) break;
            res.push_back(i.second);
        }
        return res;
    }
};
