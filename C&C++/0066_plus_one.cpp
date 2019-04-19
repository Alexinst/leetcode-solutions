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
