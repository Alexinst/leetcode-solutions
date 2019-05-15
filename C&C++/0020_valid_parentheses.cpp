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