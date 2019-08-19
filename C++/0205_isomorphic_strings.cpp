/*
给定两个字符串 s 和 t，判断它们是否是同构的。

如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。

所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。

示例 1:
	输入: s = "egg", t = "add"
	输出: true

示例 2:
	输入: s = "foo", t = "bar"
	输出: false

示例 3:
	输入: s = "paper", t = "title"
	输出: true

说明:
	你可以假设 s 和 t 具有相同的长度。
*/

class MySolution {
public:
    bool isIsomorphic(string s, string t) {
        int size=s.size();
        double a[128]={0};
        double b[128]={0};
        for(int i=0; i<size;i++)
        {
            a[s[i]] = a[s[i]] + 1 + i * i;
            b[t[i]] = b[t[i]] + 1 + i * i;
            if( a[s[i]] != b[t[i]])
                return false;
        }
        return true;
    }
};

class Solution1 {
public:
    bool isIsomorphic(string s, string t) {
        vector<int> a,b;
        for(int i=0;i<128;i++)
        {
            a.push_back(-1);
            b.push_back(-1);
        }
        int j=t.size();
        for(int i=0;i<j;i++)
            if((a[s[i]]==-1)&&(b[t[i]]==-1)) 
            {
                a[s[i]]=t[i];
                b[t[i]]=s[i];
            }
            else if((a[s[i]]!=t[i])&&(b[t[i]]!=s[i])) return false;
        return true;
    }
};
