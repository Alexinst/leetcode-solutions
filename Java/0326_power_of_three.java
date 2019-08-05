/*
 * https://leetcode-cn.com/problems/power-of-three/

给定一个整数，写一个函数来判断它是否是 3 的幂次方。

示例 1:
	输入: 27
	输出: true

示例 2:
	输入: 0
	输出: false

示例 3:
	输入: 9
	输出: true

示例 4:
	输入: 45
	输出: false

进阶：
	你能不使用循环或者递归来完成本题吗？

----------------------------------------------------------------------

Given an integer, write a function to determine if it is a power of three.

Example 1:
	Input: 27
	Output: true

Example 2:
	Input: 0
	Output: false

Example 3:
	Input: 9
	Output: true

Example 4:
	Input: 45
	Output: false

Follow up:
Could you do it without using any loop / recursion?
*/

class MySolution {
    public boolean isPowerOfThree(int n) {
        if (n < 3) {
            if (n == 1)
                return true;
            else
                return false;
        }
        
        double quotient = n / 3.0;
        if (quotient > Math.floor(quotient))
            return false;
        
        return isPowerOfThree((int) quotient);
    }
}
