/*
 * https://leetcode-cn.com/problems/is-subsequence/

给定字符串 s 和 t ，判断 s 是否为 t 的子序列。

你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），而 s 是个短字符串（长度 <=100）。

字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。

示例 1:
	s = "abc", t = "ahbgdc"
	返回 true.

示例 2:
	s = "axc", t = "ahbgdc"
	返回 false.

后续挑战: 如果有大量输入的 S，称作S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？

----------------------------------------------------------------------------------------------------

Given a string s and a string t, check if s is subsequence of t.

You may assume that there is only lower case English letters in both s and t. t is potentially a very long (length ~= 500,000) string, and s is a short string (<=100).

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ace" is a subsequence of "abcde" while "aec" is not).

Example 1:
	s = "abc", t = "ahbgdc"
	Return true.

Example 2:
	s = "axc", t = "ahbgdc"
	Return false.

Follow up: If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you want to check one by one to see if T has its subsequence. In this scenario, how would you change your code?
*/

class MySolution {
    public boolean isSubsequence(String s, String t) {
        if (s.length() > t.length()) return false;
        if (s.length() == 0) return true;

        char[] sChar = s.toCharArray(), tChar = t.toCharArray();
        for (int j = 0; j < t.length(); j++) {
            if (t.charAt(j) == sChar[0]) 
                return isSub(sChar, tChar, j);
        }

        return false;
    }

    private boolean isSub(char[] s, char[] t, int j) {
        int sLen = s.length, tLen = t.length;
        if (sLen > tLen - j) return false;

        int i = 0;
        for (; i < sLen && j < tLen; j++) {
            if (sLen - i > tLen - j) return false;
            if (s[i] == t[j]) i++;
        }

        return i == sLen;
    }
}

class Solution1 {
    public boolean isSubsequence(String s, String t) {
        int index = -1;
        for (char ch : s.toCharArray()) {
            index = t.indexOf(ch, index + 1);
            if (index == -1) return false;
        }
        return true;
    }
}
