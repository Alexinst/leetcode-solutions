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

class MySolution1 {
    public boolean isPowerOfThree(int n) {
        while (n > 3) {
            int quotient = n / 3;
            if (quotient * 3 != n)
                return false;    
            n = (int) quotient;
        }
        
        if (n == 1 || n == 3)
            return true;
        else
            return false;
    }
}

class MySolution2 {
    public boolean isPowerOfThree(int n) {
        if (n <= 3) {
            if (n == 1 || n == 3)
                return true;
            else
                return false;
        }

        int quotient = n / 3;
        if (quotient * 3 != n)
            return false;

        return isPowerOfThree(quotient);
    }
}

class Solution1 {
    public boolean isPowerOfThree(int n) {
        return n > 0 && 1162261467 % n == 0;
    }
}

class Solution2 {
    public boolean isPowerOfThree(int n) {
        if (n <= 0){
            return false;
        }
        if(n == 1){
            return true;
        }
        while(n > 1){
            if (n % 3 == 0){
                n /= 3;
            }else{
                return false;
            }
        }
        return true;
    }
}
