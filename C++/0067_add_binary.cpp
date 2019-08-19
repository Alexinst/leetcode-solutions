/*
给定两个二进制字符串，返回他们的和（用二进制表示）。
输入为非空字符串且只包含数字 1 和 0。

示例 1:
	输入: a = "11", b = "1"
	输出: "100"

示例 2:
	输入: a = "1010", b = "1011"
	输出: "10101"
*/

class MySolution {
public:
	string addBinary(string a, string b);
};

string MySolution::addBinary(string a, string b)
{
	string tmp;
	int aLen = a.length();
	int bLen = b.length();
	if (aLen == 0) return b;
	if (bLen == 0) return a;
	int lenDiff = abs(aLen-bLen);
	int biggerLen = aLen;
	if (aLen <= bLen)
	{
		tmp = a;
		a = b;
		b = tmp;
		biggerLen = bLen;
	}
	for (int i = 0; i < lenDiff; i++)
		b.insert(0, "0");

	string sumBinary;		//a与b相加结果的字符串形式
	int carry = 0;			//进位数
	for (int j = biggerLen - 1; j >= 0; j--)
	{
		int iADigit = int(a[j]) - '0';
		int iBDigit = int(b[j]) - 48;		//int('0') = 48
		int digitSum = iADigit + iBDigit + carry;

		carry = digitSum / 2;
		digitSum = digitSum % 2;
		sumBinary.push_back('0' + digitSum);
	}
	if (carry)
		sumBinary.push_back('0' + carry);
	reverse(sumBinary.begin(), sumBinary.end());

	return sumBinary;
}


class Solution1 {
public:
    string addBinary(string a, string b) {
        string sa(a.rbegin(),a.rend());
        string sb(b.rbegin(),b.rend());
        const char* as = sa.c_str();
        const char* bs = sb.c_str();
        size_t lena = a.length();
        size_t lenb = b.length();
        char* sums = new char[max(lena, lenb)+1];
        int up = 0;
        for(size_t i = 0; i < lena || i < lenb; i++){
            int ax = (i<lena)?(as[i]-'0'):0;
            int bx = (i<lenb)?(bs[i]-'0'):0;
            sums[i] = ax + bx + up;
            up = (sums[i] > 1);
            sums[i] = sums[i] % 2 + '0';
        }
        sums[max(lena, lenb)] = '\0';
        string s = sums;
        if(up)
            s += '1';
        string r(s.rbegin(),s.rend());
        return r;
    }
};
