/*
给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。

有效字符串需满足：
	1.左括号必须用相同类型的右括号闭合。
	2.左括号必须以正确的顺序闭合。

注意空字符串可被认为是有效字符串。

示例 1:
	输入: "()"
	输出: true

示例 2:
	输入: "()[]{}"
	输出: true

示例 3:
	输入: "(]"
	输出: false

示例 4:
	输入: "([)]"
	输出: false

示例 5:
	输入: "{[]}"
	输出: true
*/

#include <iostream>
#include <string>

using std::string;

class Solution {
public:
    bool isValid(string s) {
        int len = s.length();
        if (len % 2 != 0) return false;
        int tail = -1;
        for (int i = 0; i < len; i++) {
            if (isLeftPart(s[i])) {
                leftBrackets.push(s[i]);
                tail++;
            }
            else {
                if (tail == -1) {
                    return false;
                }
                int pair = s[i] - leftBrackets.top();
                if (pair == 2 || pair == 1) {
                    tail--;
                    leftBrackets.pop();
                }
                else {
                    return false;
                }
            }
        }
        if (tail > -1)
            return false;
        else
            return true;
    }
    bool isLeftPart(char bracket) {
        return bracket=='{' || bracket == '(' || bracket == '[';
    }
private:
    stack<char> leftBrackets;
};
