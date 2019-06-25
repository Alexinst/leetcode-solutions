/*
给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
返回被除数 dividend 除以除数 divisor 得到的商。

示例 1:
	输入: dividend = 10, divisor = 3
	输出: 3

示例 2:
	输入: dividend = 7, divisor = -3
	输出: -2

说明:
	1. 被除数和除数均为 32 位有符号整数。
	2. 除数不为 0。
	3. 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。
*/

class MySolution {
    private long quotient = 0;

    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1)
            return Integer.MAX_VALUE;

        boolean isNegative = (dividend ^ divisor) < 0;

        dividing(Math.abs((long)dividend), Math.abs((long)divisor));
        if (isNegative)
            quotient *= -1;
        if (quotient > Integer.MAX_VALUE)
            quotient = Integer.MAX_VALUE;

        return (int)quotient;
    }

    private void dividing(long dividend, long divisor) {
        if (dividend < divisor)
            return;

        int time = 0;
        long tmp1 = divisor;
        while (true) {  // 2^30
            long twice = tmp1 << 1;
            if (twice <= dividend) {
                tmp1 = twice;
                time++;
            }
            else
                break;
        }

        long tmp2 = (long)1 << time;
        quotient += tmp2;

        dividend -= tmp1;
        dividing(dividend, divisor);
    }
}


class Solution1 {
    public int divide(int dividend, int divisor) {
        if (dividend == divisor)
            return 1;
        int origin = Math.abs(dividend);
        int cut = Math.abs(divisor);
        int result = 0;
        if (dividend == Integer.MIN_VALUE) {
            origin = Integer.MIN_VALUE;
            if (divisor == -1)
                return Integer.MAX_VALUE;
            else
                result = -1;
            while (Integer.MAX_VALUE - cut > cut && origin + cut + cut <= 0) {
                cut += cut;
                result += result;
            }
            if (origin + cut < 0) {
                result -= divide(-origin - cut, Math.abs(divisor));
            }
        } else if (divisor == Integer.MIN_VALUE) {
            return 0;
        } else {
            if (origin < cut)
                return 0;
            else
                result = 1;
            while (Integer.MAX_VALUE - cut > cut && cut + cut <= origin) {
                cut += cut;
                result += result;
            }
            if (cut < origin) {
                result += divide(origin - cut, Math.abs(divisor));
            }
        }
        if ((result > 0 && ((dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0))) || (result < 0 && divisor < 0)) {
            return -result;
        } else {
            return result;
        }

    }
}


class Solution {
    public int divide(int dividend, int divisor) {
        if (divisor==0) return -1;
        if (dividend==0) return 0;

        // -2147483648, -1 这个测试样例的确没想到，结果翻车了*/
        if (dividend==Integer.MIN_VALUE && divisor==-1)
            return Integer.MAX_VALUE;

        // 符号位的处理参考了大佬的异或处理方法*/
        boolean negetive= (dividend^ divisor)<0;
        // div_count 是当前divisor_tmp相对于divisor的倍数 */
        int res=0, div_count=1;
        // 因为值溢出之后边界问题处理太繁琐了，直接将数值转为long省去麻烦 */
        long dividend_tmp= Math.abs((long)dividend);
        long divisor_tmp= Math.abs((long)divisor);

        // 按标准的二分查找代码模板写的 */
        while (dividend_tmp>= divisor_tmp) {
            dividend_tmp-= divisor_tmp;
            res+= div_count;

            if (dividend_tmp< Math.abs(divisor))
                break;

            // divisor_tmp无法倍增时，就将其初始化为divisor绝对值，重新开始下一轮倍增*/
            if (dividend_tmp- divisor_tmp< divisor_tmp) {
                divisor_tmp= Math.abs(divisor);
                div_count=1;
                continue;
            }

            // 不断倍增divisor_tmp直到和dividend_tmp一样大*/
            divisor_tmp+= divisor_tmp;
            div_count+= div_count;
        }
        return negetive? 0-res: res;
    }
}
