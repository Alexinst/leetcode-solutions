/*
给定一个正整数 num，编写一个函数，如果 num 是一个完全平方数，则返回 True，否则返回 False。

说明：不要使用任何内置的库函数，如  sqrt。

示例 1：
	输入：16
	输出：True

示例 2：
	输入：14
	输出：False
*/

class MySolution {
    public boolean isPerfectSquare(int num) {
        if (num == 0 || num == 1)
            return true;

        int left = 1, right = num / 2;
        while (left <= right)
        {
            double mid = left + (right - left) / 2;
            if ((int)num / mid == mid)
                return true;
            else if (num / mid > mid)
                left = (int)mid + 1;
            else
                right = (int)mid - 1;
        }
        return false;
    }
}

class Solution1 {
    public boolean isPerfectSquare(int num) {
        long a = num;
        while (a * a > num) {
            a = (a + num / a) >> 1;
        }
        return a * a == num;
    }
}
