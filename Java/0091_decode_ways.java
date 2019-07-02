/*
一条包含字母 A-Z 的消息通过以下方式进行了编码：
	'A' -> 1
	'B' -> 2
	...
	'Z' -> 26
给定一个只包含数字的非空字符串，请计算解码方法的总数。

示例 1:
	输入: "12"
	输出: 2
	解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。

示例 2:
	输入: "226"
	输出: 3
	解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
*/

class MySolution {
    public int numDecodings(String s) {
        if (s.charAt(0) == '0') return 0;

        int len = s.length(); // s 非空
        char[] chars = s.toCharArray();
        int[] dp = new int[len];

        dp[0] = possOneChar(chars[0]);
        if (len == 1) return dp[0];
        
        dp[1] = dp[0] * possOneChar(chars[1]) + 
                possTwoChars(chars[0], chars[1]);
        if (len == 2) return dp[1];
        
        for (int i = 2; i < len; i++) {
            dp[i] = possTwoChars(chars[i-1], chars[i]) * dp[i-2];
            dp[i] += possOneChar(chars[i]) * dp[i-1];
        }
        
        return dp[len - 1];
    }
    
    private int possTwoChars(char c1, char c2) {
        return c1 == '1' || (c1 == '2' && c2 <= '6') ? 1 : 0;
    }
    
    private int possOneChar(char c) {
        return c != '0' ? 1 : 0;
    }
}

class Solution1 {
    public static int numDecodings(String s) {
        if(s.length() == 0) {
            return 0;
        }
        
        if(s.length() == 1) {
            return s.charAt(0) == '0'? 0 : 1;
        }
        
        int[] dp = new int[s.length()];
        dp[0] = s.charAt(0) == '0'?0:1;
        
        int k = s.charAt(0) > '0' && s.charAt(1) > '0'? 1 : 0;
        dp[1] = k + (s.charAt(0) == '1' || 
                     s.charAt(0) == '2' && s.charAt(1) <= '6' ? 1 : 0);
        
        for (int i = 2; i < dp.length; i++) {
            if (s.charAt(i) != '0') {
                dp[i] += dp[i-1];
            }
            if (s.charAt(i-1) == '1' ||
                s.charAt(i-1) == '2' && s.charAt(i) <= '6'){
                dp[i] += dp[i-2];
            }
        }
        return dp[s.length()-1];
    }
}
