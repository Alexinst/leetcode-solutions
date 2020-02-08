/*
 * https://leetcode-cn.com/problems/palindromic-substrings/

给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。

具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被计为是不同的子串。

示例 1:
	输入: "abc"
	输出: 3
	解释: 三个回文子串: "a", "b", "c".

示例 2:
	输入: "aaa"
	输出: 6
	说明: 6个回文子串: "a", "a", "a", "aa", "aa", "aaa".

注意: 输入的字符串长度不会超过1000。

----------------------------------------------------------------------------------------------------

Given a string, your task is to count how many palindromic substrings in this string.

The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

Example 1:
	Input: "abc"
	Output: 3
	Explanation: Three palindromic strings: "a", "b", "c".

Example 2:
	Input: "aaa"
	Output: 6
	Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 
Note: The input string length won't exceed 1000.
*/

class Solution1 {
    public int countSubstrings(String S) {
        int ans = 0;
        int len = S.length();

        for (int center = 0; center < len * 2; ++center) {
			// center为偶数，回文字符串的中心为一个字符
			// center为奇数，回文字符串的中心为两个字符
            int left = center / 2;
            int right = left + center % 2;

            while (left >= 0 && right < len && S.charAt(left) == S.charAt(right)) {
                ans++;
                left--;
                right++;
            }
        }

        return ans;
    }
}


class Solution2 {
    public int countSubstrings(String S) {
        // 插入#，使得回文字符串的中心变成一个字符 '#'
        // 例如，'abba' 就变成 '@#a#b#b#a#$'，回文中心从 'bb' 变成 '#'
        char[] enlarged = new char[S.length() * 2 + 3];
        enlarged[0] = '@';
        enlarged[1] = '#';
        enlarged[enlarged.length - 1] = '$';
        int idx = 2;
        for (char c : S.toCharArray()) {
            enlarged[idx++] = c;
            enlarged[idx++] = '#';
        }

        int[] dp = new int[S.length()];  // for i in 0 ~ S.length(), 以S.char(i)为中心的最长回文字符串长度
        int center = 0, right = 0;
        for (int i = 1; i < S.length() - 1; i++) {
            if (idx < right)
                dp[i] = Math.min(dp[2 * center - i], right - i);  // center - (i - center) = 2 * center - i

            while (enlarged[i - dp[i] + 1] == enlarged[i + dp[i] + 1])
                dp[i]++;

            if (i + dp[i] > right) {
                center = i;
                right = i + dp[i];
            }
        }

        int ans = 0;
        for (int n : dp) ans += (n + 1) / 2;

        return ans; 
    }
}

