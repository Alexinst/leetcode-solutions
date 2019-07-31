/*
 * https://leetcode-cn.com/problems/decode-string/

给定一个经过编码的字符串，返回它解码后的字符串。
编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。

你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。

示例:
	s = "3[a]2[bc]", 返回 "aaabcbc".
	s = "3[a2[c]]", 返回 "accaccacc".
	s = "2[abc]3[cd]ef", 返回 "abcabccdcdcdef".

------------------------------------------------------------------------------------------------------

Given an encoded string, return its decoded string.
The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

Examples:
	s = "3[a]2[bc]", return "aaabcbc".
	s = "3[a2[c]]", return "accaccacc".
	s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
*/

class MySolution {
    public String decodeString(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c != ']') {
                stack.push(c);
            } else {
                StringBuilder num = new StringBuilder();
                StringBuilder str = new StringBuilder();
                while (!stack.isEmpty()) {
                    char pop = stack.pop();
                    if (isAlphabet(pop))
                        str.insert(0, pop);
                    
                    if (isNumeric(pop))
                        num.insert(0, pop);

                    if (stack.isEmpty() ||
                            !isNumeric(stack.peek()) && isNumeric(pop))
                        break;
                }
                String tmp = str.toString();
                int time = Integer.valueOf(num.toString());
                while (time-- > 1) {
                    str.append(tmp);
                }
                for (char ch : str.toString().toCharArray())
                    stack.push(ch);

            }
        }

        StringBuilder sb = new StringBuilder();
        for (char c : stack)
            sb.append(c);

        return sb.toString();
    }

    private boolean isNumeric(char c) {
        return c >= '0' && c <= '9';
    }

    private boolean isAlphabet(char c) {
        return c >= 'a' && c <= 'z' ||
               c >= 'A' && c <= 'Z';
    }
}

class Solution1 {
    public String decodeString(String s) {
        return helper(s.toCharArray(), 0);
    }

    private String helper(char[] s, int i) {
        int rate = 0;
        StringBuilder res = new StringBuilder();
        while (i < s.length && s[i] != ']') {
            while (s[i] >= '0' && s[i] <= '9') {
                rate *= 10;
                rate += s[i] - '0';
                i++;
            }
            if (rate == 0) rate = 1;
            if (s[i] == '[') {
                i++;
                String cur = helper(s, i);
                while (rate-- > 0)
                    res.append(cur);
            } else {
                res.append(s[i]);
            }
            rate = 0;
            i++;
        }
        return res.toString();
    }
}
