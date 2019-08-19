/*
给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。

示例:
	输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
	输出:
	[
  		["ate","eat","tea"],
  		["nat","tan"],
  		["bat"]
	]

说明：
	所有输入均为小写字母。
	不考虑答案输出的顺序。
*/

#include <iostream>
#include <vector>
#include <unordered_map>
#include <algorithm>

using std::vector;
using std::unordered_map;

class Solution {
public:
	vector<vector<string>> groupAnagrams(vector<string>& strs) {
		vector<string> sDup = strs;
		unordered_map<string, vector<string>> hashmap;
		for (int i = 0; i < strs.size(); i++)
		{
			std::sort(sDup[i].begin(), sDup[i].end());
			hashmap[sDup[i]].push_back(strs[i]);
		}
		vector<vector<string>> res;
		for (auto pair : hashmap)
		{
			res.push_back(pair.second);
		}
		return res;
	}
};
