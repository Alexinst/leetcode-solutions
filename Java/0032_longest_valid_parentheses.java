/*
 * https://leetcode-cn.com/problems/longest-valid-parentheses/ 
 
给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。

示例 1:
	输入: "(()"
	输出: 2
	解释: 最长有效括号子串为 "()"
	
示例 2:
	输入: ")()())"
	输出: 4
	解释: 最长有效括号子串为 "()()"

----------------------------------------------------------------------------------------------------

Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

Example 1:
	Input: "(()"
	Output: 2
	Explanation: The longest valid parentheses substring is "()"
	
Example 2:
	Input: ")()())"
	Output: 4
	Explanation: The longest valid parentheses substring is "()()"
*/

class MySolution {
    public int longestValidParentheses(String s) {
        int max = 0;
        int len = 0;
        int balance = 0;

		// 从左到右
        for (int i = 0; i < s.length(); i++) {
            balance += s.charAt(i) == '(' ? 1 : -1;
            len++;
            if (balance == 0) {
                max = Math.max(max, len);
            } else if (balance < 0) {
                balance = 0;
                len = 0;
            }
        }
        
        if (max == s.length()) return max;
        
        len = 0;
        balance = 0;

		// 从右到左
        for (int i = s.length() - 1; i >= 0; i--) {
            balance += s.charAt(i) == ')' ? 1 : -1;
            len++;
            if (balance == 0) {
                max = Math.max(max, len);
            } else if (balance < 0){
                balance = 0;
                len = 0;
            }
        }

        return max;
    }
}

class Solution1 {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() < 2) return 0;

        char[] chars = s.toCharArray();
        int[] dp = new int[chars.length];
        int res = 0;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == ')') {
                if (chars[i - 1] == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;

                } else if (i - dp[i - 1] > 0 &&
                           chars[i - dp[i - 1] - 1] == '(') {

                    dp[i] = dp[i - 1] + ((i - dp[i - 1] >= 2) ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }

                res = Math.max(res, dp[i]);
            }
        }
        return res;
    }
}

class Solution2 {
    public int longestValidParentheses(String s) {
        int maxans = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.empty()) {
                    stack.push(i);
                } else {
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }
        return maxans;
    }
}

