/*
 * https://leetcode-cn.com/problems/remove-k-digits/
 
给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。

注意:
    1. num 的长度小于 10002 且 >= k。
    2. num 不会包含任何前导零。

示例 1 :
	输入: num = "1432219", k = 3
	输出: "1219"
	解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
	
示例 2 :
	输入: num = "10200", k = 1
	输出: "200"
	解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
	
示例 3 :
	输入: num = "10", k = 2
	输出: "0"
	解释: 从原数字移除所有的数字，剩余为空就是0。

----------------------------------------------------------------------------------------------------

Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.

Note:
	1. The length of num is less than 10002 and will be ≥ k.
	2. The given num does not contain any leading zero.

Example 1:
	Input: num = "1432219", k = 3
	Output: "1219"
	Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
	
Example 2:
	Input: num = "10200", k = 1
	Output: "200"
	Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
	
Example 3:
	Input: num = "10", k = 2
	Output: "0"
	Explanation: Remove all the digits from the number and it is left with nothing which is 0.
*/

// 递归法
class MySolution {
    private boolean[] deleted;  // 记录移除数的索引
    private char[] cNum;

    public String removeKdigits(String num, int k) {
        int len = num.length();
        if (len <= k) return "0";

        deleted = new boolean[len];
        cNum = num.toCharArray();
        helper(0, cNum.length - 1, k);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cNum.length; i++) {
            if (!deleted[i]) {
                if (cNum[i] == '0' && sb.length() == 0)
                    continue;

                sb.append(cNum[i]);
            }
        }

        return sb.length() == 0 ? "0" : sb.toString();
    }

    private void helper(int from, int to, int k) {
        if (from > to || k <= 0) return ;
        if (to - from + 1 <= k) {
            delete(from, to);
            return;
        }

        int minIdx = from;
        for (int i = from + 1; i <= to; i++) {
            if (cNum[minIdx] > cNum[i])
                minIdx = i;
        }

        helper(from, minIdx - 1, k);
        k -= minIdx - from;
        helper(minIdx + 1, to, k);
    }

    private void delete(int from, int to) {
        for (; from <= to; from++)
            deleted[from] = true;
    }
}

class Solution1 {
    public String removeKdigits(String num, int k) {
        int[] stack = new int[num.length()];
        stack[0] = 0;
        int top = 0;
        char[] cNum = num.toCharArray();
        for (int i = 1; i < cNum.length; i++) {
            while (k > 0 && top >= 0 && cNum[i] < cNum[stack[top]]) {
                top--;
                k--;
            }
            stack[++top] = i;
        }
        while (k-- > 0) top--;

        StringBuilder ans = new StringBuilder();
        int idx = 0;
        while (idx <= top && cNum[stack[idx]] == '0')
            idx++;
        while (idx <= top)
            ans.append(cNum[stack[idx++]]);

        return ans.length() == 0 ? "0" : ans.toString();
    }
}
