/*
 * https://leetcode-cn.com/problems/edit-distance/
 *
 * Reference: https://leetcode-cn.com/problems/edit-distance/solution/bian-ji-ju-chi-by-leetcode-solution/

给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。

你可以对一个单词进行如下三种操作：
1. 插入一个字符
2. 删除一个字符
3. 替换一个字符

示例 1：
	输入：word1 = "horse", word2 = "ros"
	输出：3
	解释：
		horse -> rorse (将 'h' 替换为 'r')
		rorse -> rose (删除 'r')
		rose -> ros (删除 'e')

示例 2：
	输入：word1 = "intention", word2 = "execution"
	输出：5
	解释：
		intention -> inention (删除 't')
		inention -> enention (将 'i' 替换为 'e')
		enention -> exention (将 'n' 替换为 'x')
		exention -> exection (将 'n' 替换为 'c')
		exection -> execution (插入 'u')

----------------------------------------------------------------------------------------------------

Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.

You have the following 3 operations permitted on a word:
1. Insert a character
2. Delete a character
3. Replace a character
		
Example 1:
	Input: word1 = "horse", word2 = "ros"
	Output: 3
	Explanation: 
		horse -> rorse (replace 'h' with 'r')
		rorse -> rose (remove 'r')
		rose -> ros (remove 'e')
		
Example 2:
	Input: word1 = "intention", word2 = "execution"
	Output: 5
	Explanation: 
		intention -> inention (remove 't')
		inention -> enention (replace 'i' with 'e')
		enention -> exention (replace 'n' with 'x')
		exention -> exection (replace 'n' with 'c')
		exection -> execution (insert 'u')
*/

class Solution01 {
	public int minDistance(String word1, String word2) {
        int n = word1 == null ? 0 : word1.length();
        int m = word2 == null ? 0 : word2.length();
        if (m * n == 0) return n + m;

        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i < n + 1; i++)
            dp[i][0] = i;  // delete: from word1 to word2
        for (int j = 0; j < m + 1; j++)
            dp[0][j] = j;  // insert: from word1 to word2

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = 1 +
                            Math.min(dp[i - 1][j],
                            Math.min(dp[i][j - 1], dp[i - 1][j - 1]));
            }
        }

        return dp[n][m];
    }
}

class Solution02 {
    public static int minDistance(String word1, String word2) {
        if (word1.length() == 0 || word2.length() == 0) {
            if (word1.length() == 0 && word2.length() == 0) {
                return 0;
            }
            return 1;
        }
        char[] shorts = word1.length() >= word2.length() ? word2.toCharArray() : word1.toCharArray();
        char[] longs = word1.length() < word2.length() ? word2.toCharArray() : word1.toCharArray();
        int dp[] = new int[shorts.length + 1];

        for (int i = 1; i < dp.length; i++) {
            dp[i] = i;
        }

        for (int i = 1; i < longs.length + 1; i++) {
            int last = dp[0];
            dp[0] = i;
            for (int j = 1; j < shorts.length + 1; j++) {
                int last1 = dp[j];
                if (shorts[j - 1] == longs[i - 1]) {
                    dp[j] = last;
                } else {
                    dp[j] = Math.min(Math.min(last1 + 1, dp[j - 1] + 1), last + 1);
                }
                last = last1;
            }
        }

        return dp[dp.length - 1];
    }
}
