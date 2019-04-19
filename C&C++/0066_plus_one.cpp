/*
给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
最高位数字存放在数组的首位， 数组中每个元素只存储一个数字。
你可以假设除了整数 0 之外，这个整数不会以零开头。

示例 1:
	输入: [1,2,3]
	输出: [1,2,4]
	解释: 输入数组表示数字 123。

示例 2:
	输入: [4,3,2,1]
	输出: [4,3,2,2]
	解释: 输入数组表示数字 4321。
*/

#include <iostream>
#include <vector>

using std::vector;

class Solution {
public:
    vector<int> plusOne(vector<int>& digits);
};

vector<int> Solution::plusOne(vector<int>& digits)
{
    int len = digits.size();
    int i;
    if (len==1 && digits[0] == 0)
        digits[0] += 1;
    else
    {
        for (i=len-1; i>-1; i--)
        {
            if (digits[i] + 1 == 10)
            {
                digits[i] = 0;
            }
            else
            {
                digits[i] += 1;
                break;
            }
        }
    }
    if (i == -1)
        digits.insert(digits.begin(), 1);

    vector<int>::iterator iter = digits.begin();
    for (i=0; iter<digits.end(); i++)
        cout << *iter++ << '\t';

    return digits;
}
