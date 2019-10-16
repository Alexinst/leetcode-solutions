/*
 * https://leetcode-cn.com/problems/split-a-string-in-balanced-strings/
 
在一个「平衡字符串」中，'L' 和 'R' 字符的数量是相同的。

给出一个平衡字符串 s，请你将它分割成尽可能多的平衡字符串。

返回可以通过分割得到的平衡字符串的最大数量。
 

示例 1：
	输入：s = "RLRRLLRLRL"
	输出：4
	解释：s 可以分割为 "RL", "RRLL", "RL", "RL", 每个子字符串中都包含相同数量的 'L' 和 'R'。
	
示例 2：
	输入：s = "RLLLLRRRLR"
	输出：3
	解释：s 可以分割为 "RL", "LLLRRR", "LR", 每个子字符串中都包含相同数量的 'L' 和 'R'。

示例 3：
	输入：s = "LLLLRRRR"
	输出：1
	解释：s 只能保持原样 "LLLLRRRR".
	 

提示：
	1. 1 <= s.length <= 1000
	2. s[i] = 'L' 或 'R'

----------------------------------------------------------------------------------------------------

Balanced strings are those who have equal quantity of 'L' and 'R' characters.

Given a balanced string s split it in the maximum amount of balanced strings.

Return the maximum amount of splitted balanced strings.

Example 1:
	Input: s = "RLRRLLRLRL"
	Output: 4
	Explanation: s can be split into "RL", "RRLL", "RL", "RL", each substring contains same number of 'L' and 'R'.
	
Example 2:
	Input: s = "RLLLLRRRLR"
	Output: 3
	Explanation: s can be split into "RL", "LLLRRR", "LR", each substring contains same number of 'L' and 'R'.
	
Example 3:
	Input: s = "LLLLRRRR"
	Output: 1
	Explanation: s can be split into "LLLLRRRR".
	 

Constraints:
	1. 1 <= s.length <= 1000
	2. s[i] = 'L' or 'R'
*/

class MySolution {
    public int balancedStringSplit(String s) {
        int max = 0, balance = 0;
        for (char c : s.toCharArray()) {
            int code = c == 'L' ? 1 : -1;
            balance += code;
            
            if (balance == 0) 
                max++;
        }
        
        return max;
    }
}


