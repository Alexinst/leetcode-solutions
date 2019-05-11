/*
实现 int sqrt(int x) 函数。
计算并返回 x 的平方根，其中 x 是非负整数。
由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。

示例 1:
    输入: 4
    输出: 2
示例 2:
    输入: 8
    输出: 2
    说明: 8 的平方根是 2.82842...,
         由于返回类型是整数，小数部分将被舍去。
*/

#include <iostream>

class Solution {
public:
    int mySqrt(int x) {
        if (x == 0 || x == 1)
            return x;

        int left = 1, int right = x;
        while (true)
        {
            int middle = left + (right - left) / 2;
            if (x / middle == middle)
                return middle;

            else if (x / middle > middle)           // Do if square of middle is smaller than x.
            {
                if (x / (middle+1) < (middle+1))    // Do if square of middle is the closest integer to x.
                    return middle;
                else
                    left = middle + 1;
            }

            else                                    // Do if square of middle is bigger than x.
                right = middle - 1;
        }
    }
};

class Solution {
public:
    int mySqrt(int x) {
        if (x == 0)
            return 0;

        double cur = x;
        double last = 0;
        while (abs(cur - last) >= 1) {
            last = cur;
            cur = (cur * cur + x) / (2 * cur);
        }
        return floor(cur);
    }

};

