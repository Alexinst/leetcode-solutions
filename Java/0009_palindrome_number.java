/*判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。

示例 1:
	输入: 121
	输出: true

示例 2:
	输入: -121
	输出: false
	解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。

示例 3:
	输入: 10
	输出: false
	解释: 从右向左读, 为 01 。因此它不是一个回文数。

进阶:
	你能不将整数转为字符串来解决这个问题吗？
*/

class MySolution {
    public boolean isPalindrome(int x) {
		if (x < 0) return false;

        Stack<Character> stack = new Stack<>();
        String xStr = Integer.toString(x);
        int halfOfLen = xStr.length() / 2;
        int middle = xStr.length() % 2 == 1 ? 1 : 0;
        for (int i = 0; i < halfOfLen; i++) {
            stack.push(xStr.charAt(i));
        }
        
        for (int i = halfOfLen + middle; i < xStr.length() ; i++) {
            if (stack.isEmpty()) break;

            if (stack.pop() == xStr.charAt(i))
                continue;
            else
                return false;
        }
        return true;
    }
}

class Solution1 {
    public boolean isPalindrome(int x) {
        if(x < 0) return false;
        return reverse(x) == x;
    }

    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }
}
