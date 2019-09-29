/*
 * https://leetcode-cn.com/problems/letter-case-permutation/
 *
 * Reference: https://leetcode-cn.com/problems/letter-case-permutation/solution/shen-du-you-xian-bian-li-hui-su-suan-fa-python-dai/ 
 
给定一个字符串S，通过将字符串S中的每个字母转变大小写，我们可以获得一个新的字符串。返回所有可能得到的字符串集合。

示例:
	输入: S = "a1b2"
	输出: ["a1b2", "a1B2", "A1b2", "A1B2"]

	输入: S = "3z4"
	输出: ["3z4", "3Z4"]

	输入: S = "12345"
	输出: ["12345"]

注意：
	1. S 的长度不超过12。
	2. S 仅由数字和字母组成。

---------------------------------------------------------------------------------------------------

Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.  Return a list of all possible strings we could create.

Examples:
	Input: S = "a1b2"
	Output: ["a1b2", "a1B2", "A1b2", "A1B2"]

	Input: S = "3z4"
	Output: ["3z4", "3Z4"]

	Input: S = "12345"
	Output: ["12345"]

Note:
	1. S will be a string with length between 1 and 12.
	2. S will consist only of letters or digits.
*/

class MySolution {
    public List<String> letterCasePermutation(String S) {
        List<String> ans = new ArrayList<>();
        if (S == null || S.length() == 0) return ans;

        dfs(ans, S.toCharArray(), 0);
        return ans;
    }

    private void dfs(List<String> ans, char[] chars, int i) {
        if (i >= chars.length) {
            ans.add(new String(chars));
        } else {
            // 当 chars[i] 为数字时，则跳过
            // 为避免溢出，限定 i < chars.length
            while (i < chars.length && isNumeric(chars[i])) i++;

            dfs(ans, chars, i + 1);
            if (i < chars.length) {
                // 若chars[i] > 'Z'，则 chars[i] 为小写字母，反之为大写字母
                chars[i] = chars[i] > 'Z' ? Character.toUpperCase(chars[i]) :
                                            Character.toLowerCase(chars[i]);
                dfs(ans, chars, i + 1);
            }
        }
    }

    private boolean isNumeric(char c) {
        return c >= '0' && c <='9';
    }
}

class Solution1 {
    List<String> res = new ArrayList<>();

    public List<String> letterCasePermutation(String S) {
        int len = S.length();
        if (len == 0)
            return res;
        char[] c = S.toCharArray();
        dfs(0, len, c);
        return res;
    }

    private void dfs(int index, int len, char[] c) {
        if (len == index) {
            res.add(new String(c));
            return;
        }

        dfs(index + 1, len, c);
        if (Character.isLetter(c[index])) {  // c[index]是否为字母
            c[index] ^= 1 << 5;  // c[index]与 32(100000b) 异或
            dfs(index + 1, len, c);
        }
    }
}


