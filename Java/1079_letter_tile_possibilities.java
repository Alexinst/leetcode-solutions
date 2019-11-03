/*
 * https://leetcode-cn.com/problems/letter-tile-possibilities/
 
你有一套活字字模 tiles，其中每个字模上都刻有一个字母 tiles[i]。返回你可以印出的非空字母序列的数目。

示例 1：
	输入："AAB"
	输出：8
	解释：可能的序列为 "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA"。
	
示例 2：
	输入："AAABBC"
	输出：188
	 
提示：
	1. 1 <= tiles.length <= 7
	2. tiles 由大写英文字母组成

----------------------------------------------------------------------------------------------------

You have a set of tiles, where each tile has one letter tiles[i] printed on it.  Return the number of possible non-empty sequences of letters you can make.

Example 1:
	Input: "AAB"
	Output: 8
	Explanation: The possible sequences are "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA".

Example 2:
	Input: "AAABBC"
	Output: 188
	 
Note:
	1. 1 <= tiles.length <= 7
	2. tiles consists of uppercase English letters.
*/

class MySolution {
    private int n = 0;

    public int numTilePossibilities(String titles) {
        backtrack(titles.toCharArray(), 0);

        return n;
    }

    private void backtrack(char[] titles, int i) {
        if (i == titles.length) return;

        n++;
        backtrack(titles, i + 1);

        for (int j = i + 1; j < titles.length; j++) {
            if (canSwap(titles, i, j)) {
                swap(titles, i, j);
                n++;

                backtrack(titles, i + 1);
                swap(titles, i, j);
            }
        }
    }
    
    private boolean canSwap(char[] titles, int i, int j) {
        for (; i < j; i++) {
            if (titles[i] == titles[j])
                return false;
        }
        return true;
    }

    private void swap(char[] titles, int i, int j) {
        char tmp = titles[i];
        titles[i] = titles[j];
        titles[j] = tmp;
    }
}

class Solution1 {
    public int numTilePossibilities(String tiles) {
        char[] chars = tiles.toCharArray();
        Arrays.sort(chars);
        boolean[] visited = new boolean[chars.length];
        return help(0, chars, visited);
    }

    int help(int i, char[] chars, boolean[] visited) {  // 填第i个格子 0<=i<=n-1
        int res = 0;

        if (i == chars.length) return res;
        for (int k = 0; k < chars.length; k++) {
            if (!visited[k]) {
                if (k > 0 && chars[k] == chars[k - 1] && !visited[k - 1])
                    continue;

                res++;
                visited[k] = true;
                res += help(i + 1, chars, visited);
                visited[k] = false;
            }
        }
        
        return res;
    }
}
