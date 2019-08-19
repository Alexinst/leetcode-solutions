/*
无重复字符的最长子串
给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
示例 1:
	输入: "abcabcbb"
	输出: 3
	解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
示例 2:
	输入: "bbbbb"
	输出: 1
	解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
示例 3:
	输入: "pwwkew"
	输出: 3
	解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。

请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
*/

#include <iostream>
#include <vector>
#include <queue>
#include <unordered_map>

using std::unordered_map;
using std::endl;
using std::cout;
using std::string;
using std::vector;
using std::queue;

class Solution {
public:
	int lengthOfLongestSubstring(string s) {
		int ans = 0;
		vector<bool> f(256, false);
		for (int i = 0, j = 0;; i++) {
			while (j < s.size() && !f[s[j]]) {
				f[s[j++]] = true;
			}
			ans = std::max(ans, j - i);
			if (j >= s.size()) {
				break;
			}
			while (s[i] != s[j]) {
				f[s[i++]] = false;
			}
			f[s[i]] = false;
		}
		return ans;
	}
};

class Solution2 {
public:
	int lengthOfLongestSubstring(string s)
	{
		vector<int>v(128, 0);
		int t = 0; 
		int ans = 0;
		for (int i = 0; i < s.length(); i++)
		{
			t = std::max(t, v[s[i]]);
			ans = std::max(ans, i - t + 1);
			v[s[i]] = i + 1;
		}
		return ans;
	}
};

class MySolution
{
public:
	int lengthOfLongestSubstring(string s)
	{
		unordered_map<char, int> uMap;
		queue<char> queChar;
		int len = 0;			// 无重复字符串的最大长度
		int startIndex = 0;		// 每个无重复字符串的起点索引
		for (int i = 0; i < s.length(); i++)
		{
			if (uMap.count(s[i]) && uMap[s[i]] >= startIndex)
			{
				// 遇到重复字符且该字符大于起点索引时，对queChar进行pop
				for (int j = startIndex; j <= uMap[s[i]]; j++)
					queChar.pop();

				// 重新赋值起点索引
				startIndex = uMap[s[i]] + 1;
			}

			queChar.push(s[i]);
			uMap[s[i]] = i;
			len = std::max(len, (int)queChar.size());
		}
		return len;
	}
};
