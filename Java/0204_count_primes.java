/*
 * https://leetcode-cn.com/problems/count-primes/ 
 * 思路：https://leetcode-cn.com/problems/count-primes/solution/ji-shu-zhi-shu-bao-li-fa-ji-you-hua-shai-fa-ji-you/
 
统计所有小于非负整数 n 的质数的数量。

示例:
	输入: 10
	输出: 4
	解释: 小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。

-------------------------------------------------------------------------

Count the number of prime numbers less than a non-negative number, n.

Example:
	Input: 10
	Output: 4
	Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
*/

/**
 * 埃拉托斯特尼筛法（sieve of Eratosthenes）
 * https://zh.wikipedia.org/wiki/%E5%9F%83%E6%8B%89%E6%89%98%E6%96%AF%E7%89%B9%E5%B0%BC%E7%AD%9B%E6%B3%95
 */
class Solution {
    public int countPrimes(int n) {
        // notPrime: 质数记 false; 非质数记 true;
        boolean[] notPrime = new boolean[n];
        int count = 0;

        for (int num = 2; num < n; num++) {
            if (!notPrime[num]) {
                count++;
                
                int times = num * 2;
                while (times < n) {
                    notPrime[times] = true;
                    times += num;
                }
            }
        }

        return count;
    }
}


