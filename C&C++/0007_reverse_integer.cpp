/*
给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。

示例 1:
	输入: 123
	输出: 321

示例 2:
	输入: -123
	输出: -321

示例 3:
	输入: 120
	输出: 21

注意:
	假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
*/

#include <iostream>
#include <vector>
#include <cmath>

using std::vector;

class MySolution {
private:
    vector<int> digits;
public:
    int reverse(int x) {
        if (x == 0 || x == -2147483648)
            return 0;
        
        long reverseX = 0;
        getDigits(x);
        for (int i = 0; i < digits.size(); i++)
        {
            reverseX += digits[i] * (long)pow(10, i);
        }
        
        if (reverseX > 2147483647 or reverseX < -2147483648)
            return 0;
        else
            return (int)reverseX;
    }
    
    void getDigits(int n) {
        int digit = n % 10;
        digits.insert(digits.begin(), digit);
        if (abs(n) >= 10) {
            getDigits(n/10);
        }
    }
};
