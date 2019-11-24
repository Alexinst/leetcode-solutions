/*
 * https://leetcode-cn.com/problems/word-break/
 *
 * https://leetcode-cn.com/problems/word-break/solution/dan-ci-chai-fen-by-leetcode/ 
 
给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。

说明：
	1. 拆分时可以重复使用字典中的单词。
	2. 你可以假设字典中没有重复的单词。

示例 1：
	输入: s = "leetcode", wordDict = ["leet", "code"]
	输出: true
	解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。

示例 2：
	输入: s = "applepenapple", wordDict = ["apple", "pen"]
	输出: true
	解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
	     注意你可以重复使用字典中的单词。
	
示例 3：
	输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
	输出: false

----------------------------------------------------------------------------------------------------

Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

Note:
	1. The same word in the dictionary may be reused multiple times in the segmentation.
	2. You may assume the dictionary does not contain duplicate words.

Example 1:
	Input: s = "leetcode", wordDict = ["leet", "code"]
	Output: true
	Explanation: Return true because "leetcode" can be segmented as "leet code".

Example 2:
	Input: s = "applepenapple", wordDict = ["apple", "pen"]
	Output: true
	Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
	             Note that you are allowed to reuse a dictionary word.
Example 3:
	Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
	Output: false
*/

class Solution1 {
    public boolean wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        Set<String> wordDictSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;

        for (int i = 1; i <= len; ++i) {
            for (int j = 0; j < i; ++j) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[len];
    }
}

class Solution2 {
    public boolean wordBreak(String s, List<String> wordDict) {
        int maxWordSize = 0;
        int minWordSize = Integer.MAX_VALUE;
        Set<String> wordsSet = new HashSet<>();
        for (String word : wordDict) {
            wordsSet.add(word);
            maxWordSize = Math.max(maxWordSize, word.length());
            minWordSize = Math.min(minWordSize, word.length());
        }
        
        if (wordsSet.contains(s)) return true;

        char[] source = s.toCharArray();
        boolean[] status = new boolean[source.length];
        for (int i = 0; i < source.length; i++) {
            int start = i - maxWordSize + 1;
            int end = i - minWordSize + 1;
            if (start < 0) {
                start = 0;
            }
            for (int k = start; k <= end; k++) {
                if (k > 0 && !status[k - 1]) {
                    continue;
                }
                String curWord = new String(source, k, i - k + 1);
                if (wordsSet.contains(curWord)) {
                    status[i] = true;
                    break;
                }

            }
        }
        return status[source.length - 1];
    }
}
