/*
 * https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/
 *
 * Reference: https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/solution/hua-dong-chuang-kou-tong-yong-si-xiang-jie-jue-zi-/
 
给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。

字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。

说明：字母异位词指字母相同，但排列不同的字符串。
不考虑答案输出的顺序。

示例 1:
	输入: s: "cbaebabacd" p: "abc"
	输出: [0, 6]
	解释:
	起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
	起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
	
示例 2:
	输入: s: "abab" p: "ab"
	输出: [0, 1, 2]
	解释:
	起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
	起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
	起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。

----------------------------------------------------------------------------------------------------

Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

The order of output does not matter.

Example 1:
	Input: s: "cbaebabacd" p: "abc"
	Output: [0, 6]
	Explanation:
	The substring with start index = 0 is "cba", which is an anagram of "abc".
	The substring with start index = 6 is "bac", which is an anagram of "abc".

Example 2:
	Input: s: "abab" p: "ab"
	Output: [0, 1, 2]
	Explanation:
	The substring with start index = 0 is "ab", which is an anagram of "ab".
	The substring with start index = 1 is "ba", which is an anagram of "ab".
	The substring with start index = 2 is "ab", which is an anagram of "ab".
*/

class MySolution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s.length() < p.length())
            return res;

        char[] charsS = s.toCharArray();
        int[] map = new int[26];
        for (char c : p.toCharArray())
            map[c - 'a'] += 1;

        for (int i = 0; i < s.length() - p.length() + 1; i++) {
            if (map[charsS[i] - 'a'] > 0) {
                if (p.length() == 1 ||
                    isAnagram(charsS, map.clone(), i, i + p.length() - 1)) {
                res.add(i);
                }
            }
        }

        return res;
    }

    private boolean isAnagram(char[] charsS, int[] map, int start, int end) {
        for (int i = start; i <= end; i++) {
            if (map[charsS[i] - 'a'] == 0) {
                return false;
            }
            map[charsS[i] - 'a'] -= 1;
        }
        
        return true;
    }
}

class Solution1 {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s == null || p == null || s.length() < p.length()) {
            return result;
        }

        int pLen = p.length(), sLen = s.length();
        int[] map = new int[26];
        for (int i = 0; i < pLen; i++) {
            map[p.charAt(i) - 'a']++;
        }

        int left = 0, right = 0;
        while (right < sLen) {
            if (map[s.charAt(right) - 'a'] > 0) {
                map[s.charAt(right) - 'a']--;
                right++;
            } else {
                map[s.charAt(left) - 'a']++;
                left++;
            }

            if (right - left == pLen) {
                result.add(left);
            }
        }

        return result;
    }
}

class Solution2 {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<Integer>();
        if (s == null || s.length() == 0)
            return list;
        
        int sLen = s.length();
        int pLen = p.length();
        int[] sArr = new int[26];
        int[] pArr = new int[26];
        for (int i = 0; i < pLen; i++) {
            pArr[p.charAt(i) - 'a']++;
        }
        
        for (int i = 0; i < sLen; i++) {
            sArr[s.charAt(i) - 'a']++;
            if (i >= pLen) {
                sArr[s.charAt(i - pLen) - 'a']--;
            }
            if (Arrays.equals(pArr, sArr)) {
                list.add(i - pLen + 1);
            }
        }
        
        return list;
    }
}
