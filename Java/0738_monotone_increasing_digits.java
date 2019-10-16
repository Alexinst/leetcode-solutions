/*
 * https://leetcode-cn.com/problems/monotone-increasing-digits/ 

给定一个非负整数 N，找出小于或等于 N 的最大的整数，同时这个整数需要满足其各个位数上的数字是单调递增。

（当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。）

示例 1:
	输入: N = 10
	输出: 9

示例 2:
	输入: N = 1234
	输出: 1234

示例 3:
	输入: N = 332
	输出: 299

说明: N 是在 [0, 10^9] 范围内的一个整数。

----------------------------------------------------------------------------------------------------

Given a non-negative integer N, find the largest number that is less than or equal to N with monotone increasing digits.

(Recall that an integer has monotone increasing digits if and only if each pair of adjacent digits x and y satisfy x <= y.)

Example 1:
	Input: N = 10
	Output: 9

Example 2:
	Input: N = 1234
	Output: 1234

Example 3:
	Input: N = 332
	Output: 299

	Note: N is an integer in the range [0, 10^9]. 
*/

class MySolution {
    public int monotoneIncreasingDigits(int N) {
        int dup = N, division = 0, maxL = 0;

        int time = (int) Math.pow(10, 9);
        while (dup < time) time /= 10;

        while (time > 0) {
            int n = dup / time;

            if (maxL < n) {
                division = time;
                maxL = n;
            } else if (maxL > n) {
                break;
            }

            dup -= n * time;
            time /= 10;
        }

        if (division > 1)
            N = N / division * division - 1;

        return N;
    }
}

class Solution1 {
    public static int monotoneIncreasingDigits(int n) {
        String s = String.valueOf(n);
        int length = s.length();
        char[] chars = s.toCharArray();
        int flag = length;
        for (int i = length - 1; i >= 1; i--) {
            if (chars[i] < chars[i - 1]) {
                flag = i;
                chars[i - 1]--;
            }
        }

        for (int i = flag; i < length; i++) {
            chars[i] = '9';
        }
        return Integer.parseInt(new String(chars));

    }
}
