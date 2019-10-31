/*
 * https://leetcode-cn.com/problems/string-without-aaa-or-bbb/
 
给定两个整数 A 和 B，返回任意字符串 S，要求满足：
	1. S 的长度为 A + B，且正好包含 A 个 'a' 字母与 B 个 'b' 字母；
	2. 子串 'aaa' 没有出现在 S 中；
	3. 子串 'bbb' 没有出现在 S 中。

示例 1：
	输入：A = 1, B = 2
	输出："abb"
	解释："abb", "bab" 和 "bba" 都是正确答案。

示例 2：
	输入：A = 4, B = 1
	输出："aabaa"
	 
提示：
	1. 0 <= A <= 100
	2. 0 <= B <= 100
	3. 对于给定的 A 和 B，保证存在满足要求的 S。

----------------------------------------------------------------------------------------------------

Given two integers A and B, return any string S such that:
	1. S has length A + B and contains exactly A 'a' letters, and exactly B 'b' letters;
	2. The substring 'aaa' does not occur in S;
	3. The substring 'bbb' does not occur in S.

Example 1:
	Input: A = 1, B = 2
	Output: "abb"
	Explanation: "abb", "bab" and "bba" are all correct answers.
	
Example 2:
	Input: A = 4, B = 1
	Output: "aabaa"
	 
Note:
	1. 0 <= A <= 100
	2. 0 <= B <= 100
	3. It is guaranteed such an S exists for the given A and B.
*/

class MySolution {
    public String strWithout3a3b(int A, int B) {
        StringBuilder sb = new StringBuilder();
        int len = A + B;
        int counterA = 0, counterB = 0;

        for (int i = 0; i < len; ++i) {
            if (counterA != 2 && counterB != 2) {
                if (A > B) {
                    sb.append('a');
                    counterA++;
                    A--;
                } else {
                    sb.append('b');
                    counterB++;
                    B--;
                }
            } else {
                if (counterA == 2) {
                    sb.append('b');
                    counterA = 0;
                    counterB++;
                    B--;
                } else {
                    sb.append('a');
                    counterB = 0;
                    counterA++;
                    A--;
                }
            }
        }

        return sb.toString();
    }
}

class Solution1 {
    public String strWithout3a3b(int A, int B) {
        StringBuilder stringBuilder = new StringBuilder();
        while (A > 0 && B > 0) {
            if (A > B) {
                stringBuilder.append("aab");
                A -= 2;
                B -= 1;
            } else if (A == B) {
                for (int i = 0; i < A; i++) {
                    stringBuilder.append("ab");
                }
                A = 0;
                B = 0;
            } else {
                stringBuilder.append("bba");
                A -= 1;
                B -= 2;
            }
        }
        if (A == 0) {
            for (int i = 0; i < B; i++) {
                stringBuilder.append("b");
            }
        } else {
            for (int i = 0; i < A; i++) {
                stringBuilder.append("a");
            }
        }
        return stringBuilder.toString();
    }
}
