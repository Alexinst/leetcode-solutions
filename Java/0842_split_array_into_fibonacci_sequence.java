/*
 * https://leetcode-cn.com/problems/split-array-into-fibonacci-sequence/

给定一个数字字符串 S，比如 S = "123456579"，我们可以将它分成斐波那契式的序列 [123, 456, 579]。

形式上，斐波那契式序列是一个非负整数列表 F，且满足：
    1. 0 <= F[i] <= 2^31 - 1，（也就是说，每个整数都符合 32 位有符号整数类型）；
    2. F.length >= 3；
    3. 对于所有的0 <= i < F.length - 2，都有 F[i] + F[i+1] = F[i+2] 成立。

另外，请注意，将字符串拆分成小块时，每个块的数字一定不要以零开头，除非这个块是数字 0 本身。

返回从 S 拆分出来的所有斐波那契式的序列块，如果不能拆分则返回 []。

示例 1：
	输入："123456579"
	输出：[123,456,579]

示例 2：
	输入: "11235813"
	输出: [1,1,2,3,5,8,13]

示例 3：
	输入: "112358130"
	输出: []
	解释: 这项任务无法完成。

示例 4：
	输入："0123"
	输出：[]
	解释：每个块的数字不能以零开头，因此 "01"，"2"，"3" 不是有效答案。

示例 5：
	输入: "1101111"
	输出: [110, 1, 111]
	解释: 输出 [11,0,11,11] 也同样被接受。

提示：
    1. 1 <= S.length <= 200
    2. 字符串 S 中只含有数字。

---------------------------------------------------------------------------------------------------

Given a string S of digits, such as S = "123456579", we can split it into a Fibonacci-like sequence [123, 456, 579].

Formally, a Fibonacci-like sequence is a list F of non-negative integers such that:
    1. 0 <= F[i] <= 2^31 - 1, (that is, each integer fits a 32-bit signed integer type);
    2. F.length >= 3;
    3. and F[i] + F[i+1] = F[i+2] for all 0 <= i < F.length - 2.

Also, note that when splitting the string into pieces, each piece must not have extra leading zeroes, except if the piece is the number 0 itself.

Return any Fibonacci-like sequence split from S, or return [] if it cannot be done.

Example 1:
	Input: "123456579"
	Output: [123,456,579]

Example 2:
	Input: "11235813"
	Output: [1,1,2,3,5,8,13]

Example 3:
	Input: "112358130"
	Output: []
	Explanation: The task is impossible.

Example 4:
	Input: "0123"
	Output: []
	Explanation: Leading zeroes are not allowed, so "01", "2", "3" is not valid.

Example 5:
	Input: "1101111"
	Output: [110, 1, 111]
	Explanation: The output [11, 0, 11, 11] would also be accepted.

Note:
    1. 1 <= S.length <= 200
    2. S contains only digits.
*/

class MySolution {
    private List<Integer> ans = null;

    public List<Integer> splitIntoFibonacci(String S) {

        LinkedList<Integer> seq = new LinkedList<>();
        backtrack(S, seq, 0);

        if (ans == null) return new LinkedList<>();
        return ans;
    }

    /**
     * 
     * @param S
     * @param seq 储存 Fibonacci 数列
     * @param i 对字符串 S 的索引
     */
    private void backtrack(String S, LinkedList<Integer> seq, int i) {
        if (i >= S.length()) {
            if (seq.size() > 2)
                ans = new LinkedList<>(seq);
            return;
        }

        for (int j = i + 1; j <= S.length() && j - i <= 10; j++) {
            // 剪枝：以 0 开头的非零数
            if (S.charAt(i) == '0' && j - i > 1) return;

            // 剪枝：从第二个数起，前一个数比后一个数大
            if (seq.size() > 0) {
                int b = seq.get(seq.size() - 1);
                int maxLen = Math.max(j - i, S.length() - i);
                if ((int) Math.ceil(Math.log10(b)) > maxLen)
                    return;
            }

            // 剪枝：n 超过 int 范围
            String nStr = S.substring(i, j);
            if (nStr.length() >= 10 && nStr.compareTo(String.valueOf(Integer.MAX_VALUE)) >= 0)
                return;
            int n = Integer.valueOf(nStr);

            // 剪枝：F[i] + F[i+1] != F[i+2]
            if (seq.size() >= 2) {
                int a = seq.get(seq.size() - 2), b = seq.get(seq.size() - 1);
                if (a + b < n)
                    return;
                else if (a + b > n)
                    continue;
            }

            seq.add(n);
            backtrack(S, seq, j);
            seq.pollLast();
        }
    }
}

class Solution1 {
    public List<Integer> splitIntoFibonacci(String S) {
        ArrayList<Integer> ans = new ArrayList<>();
        int len = S.length();

        for (int i = 1; i <= len / 2; i++) {
            if (S.charAt(0) == '0' && i > 1) break;
            long tmp1 = Long.parseLong(S.substring(0, i));
            if (tmp1 > Integer.MAX_VALUE) break;
            ans.add((int) tmp1);
            for (int j = 1; Math.max(i, j) <= len - i - j; j++) {
                if (S.charAt(i) == '0' && j > 1) break;
                long tmp2 = Long.parseLong(S.substring(i, i + j));
                if (tmp2 > Integer.MAX_VALUE) break;
                int num1 = (int) tmp1;
                int num2 = (int) tmp2;
                ans.add(num2);
                String sum;
                int start;
                for (start = i + j; start < len; start += sum.length()) {
                    num2 = num1 + num2;
                    num1 = num2 - num1;
                    sum = String.valueOf(num2);
                    if (!S.startsWith(sum, start)) {
                        break;
                    }
                    ans.add(num2);
                }
                if (len == start) return ans;
                for (int idx = ans.size() - 1; idx >= 1; idx--) ans.remove(idx);
            }
            ans.remove(0);
        }

        return ans;
    }
}
