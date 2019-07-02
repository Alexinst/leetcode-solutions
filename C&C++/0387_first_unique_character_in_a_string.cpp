/*
给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。

案例:
	s = "leetcode"
	返回 0.

	s = "loveleetcode",
	返回 2.
*/

#include <cstring>

class MySolution {
public:
	int firstUniqChar(string s) {
		string sRev(s);
		reverse(sRev.begin(), sRev.end());

		for (int i = 0; i < s.length(); i++)
		{
            if (s.find(s[i]) < i)
				continue;
			if (i + sRev.find(s[i]) == s.length() - 1)
				return i;
		}
		return -1;
	}
};

static const auto io_sync = []{
            std::ios::sync_with_stdio(false);
            std::cin.tie(nullptr);
            return nullptr;
        }();

class Solution1 {
public:
    int firstUniqChar(string s) {
        int one=0;
        vector<int> m(255,0);
        for(int i=0;i<s.size();i++){
            if(m[s[i]]) {
                m[s[i]]++;
                if(s[i]==s[one]) {
                    while(m[s[++one]]>1);
                }
            }
            else m[s[i]]++;
        }
        if(one>=s.size()) return -1;
        return one;
    }
};
