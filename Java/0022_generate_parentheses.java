/*
 * https://leetcode-cn.com/problems/generate-parentheses/

给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。

例如，给出 n = 3，生成结果为：
	[
	  "((()))",
	  "(()())",
	  "(())()",
	  "()(())",
	  "()()()"
	]

---------------------------------------------------------------------------------------------------

Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:
	[
	  "((()))",
	  "(()())",
	  "(())()",
	  "()(())",
	  "()()()"
	]
*/

class Solution {
    private List<String> combinations = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        if (n <= 0) return combinations;

        StringBuilder com = new StringBuilder();
        backtrack(com, n, n );
        
        return combinations;
    }

    /**
     *
     * @param left 左括号的剩余个数
     * @param right 右括号的剩余个数
     */
    private void backtrack(StringBuilder com, int left, int right) {
        if (left == 0 && right == 0) {
            combinations.add(com.toString());
            return ;
        }

        if (left > 0) {
            com.append('(');
            backtrack(com, left - 1, right);
            com.deleteCharAt(com.length() - 1);
        }

        if (left < right) {
            com.append(')');
            backtrack(com, left, right - 1);
            com.deleteCharAt(com.length() - 1);
        }
    }
}

class Solution1 {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList();
        help(res, "", 0, 0, n);
        return res;
    }

    public void help(List<String> list, String cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            list.add(cur);
            return;
        }

        if (open < max)
            help(list, cur + "(", open + 1, close, max);
        if (open > close)
            help(list, cur + ")", open, close + 1, max);
    }
}
