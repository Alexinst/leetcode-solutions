/*
给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。

示例 1：
	输入: "babad"
	输出: "bab"
	注意: "aba" 也是一个有效答案。

示例 2：
	输入: "cbbd"
	输出: "bb"
*/

class Solution1 {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }
}

class Solution2 {
    
    /**
     * 对子字符串利用动态规划法去搜索
     *
     * @param str
     * @return
     */
    public String longestPalindrome(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        int[] range = new int[2];
        char[] c = str.toCharArray();
        for(int i=0; i < str.length(); i++) {
            i = find(c, i, range);
        }
        return str.substring(range[0], range[1] + 1);
    }
    
    /**
     * 思路：动态规划法
     *
     * @param str
     * @return
     */
    private static int find(char[] c, int low, int[] range) {
        int max = c.length - 1;
        int high = low;
        // 确保查找的字符串的首尾是一样的
        while (high < max && c[high + 1] == c[low]) {
            high++;
        }
        int result = high;
        // 动态规划法
        while (low > 0 && high < max && c[low - 1] == c[high + 1]) {
            low--;
            high++;
        }
        // 保存字符串的最长回文字符串的位置索引值
        if (high - low >= range[1] - range[0]) {
            range[0] = low;
            range[1] = high;
        }
        return result;
    }
}
