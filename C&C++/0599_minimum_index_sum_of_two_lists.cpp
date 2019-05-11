/*
两个列表的最小索引总和
	假设Andy和Doris想在晚餐时选择一家餐厅，并且他们都有一个表示最喜爱餐厅的列表，每个餐厅的名字用字符串表示。
	你需要帮助他们用最少的索引和找出他们共同喜爱的餐厅。 如果答案不止一个，则输出所有答案并且不考虑顺序。 你
	可以假设总是存在一个答案。
示例 1:
	输入:
		["Shogun", "Tapioca Express", "Burger King", "KFC"]
		["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
	输出: ["Shogun"]
	解释: 他们唯一共同喜爱的餐厅是“Shogun”。
示例 2:
	输入:
		["Shogun", "Tapioca Express", "Burger King", "KFC"]
		["KFC", "Shogun", "Burger King"]
	输出: ["Shogun"]
	解释: 他们共同喜爱且具有最小索引和的餐厅是“Shogun”，它有最小的索引和1(0+1)。
提示:
	两个列表的长度范围都在 [1, 1000]内。
	两个列表中的字符串的长度将在[1，30]的范围内。
	下标从0开始，到列表的长度减1。
	两个列表都没有重复的元素。
*/
#include <iostream>
#include <vector>
#include <unordered_map>
#include <algorithm>

using std::vector;
using std::string;

class Solution {
public:
	vector<string> findRestaurant(vector<string>& list1, vector<string>& list2) {
		vector<string> retv;
		if (list1.size() == 0 || list2.size() == 0)
			return retv;
		std::unordered_map<string, int> hash_map;
		for (int i = 0; i < list1.size(); i++)
		{
			hash_map[list1[i]] = i + 1;
		}
		vector<std::pair<string, int>> ele;
		for (int j = 0; j < list2.size(); j++)
		{
			if (hash_map.count(list2[j]))
			{
				ele.push_back(std::pair<string, int>(list2[j], hash_map[list2[j]] + j));
			}
		}
		std::sort(ele.begin(), ele.end(), cmp);
		int min = ele[0].second;
		for (auto e : ele)
		{
			if (min == e.second)
			{
				retv.push_back(e.first);
			}
		}
		return retv;

	}

	bool cmp(std::pair<string, int> & a, std::pair<string, int> & b)
	{
		return a.second < b.second;
	}
};

class MySolution {
public:
	vector<string> findRestaurant(vector<string>& list1, vector<string>& list2) {
		std::unordered_map<string, int> hashmap;
		vector<string> commonRestaurants;
		int minIndexSum = list1.size() + list2.size();

		for (int i = 0; i < list1.size(); i++)
			hashmap[list1[i]] = i;
		
		for (int j = 0; j < list2.size(); j++)
		{
			if (hashmap.find(list2[j]) != hashmap.end())
			// if (hashmap.count(list2[i]))
			{
				int sum = hashmap[list2[j]] + j;
				if (sum < minIndexSum)
				{
					minIndexSum = sum;
					commonRestaurants = { list2[j] };
				}
				else if (sum == minIndexSum)
					commonRestaurants.push_back(list2[j]);
			}
		}
		return commonRestaurants;
	}
};


