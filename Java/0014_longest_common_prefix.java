/*
编写一个函数来查找字符串数组中的最长公共前缀。
如果不存在公共前缀，返回空字符串 ""。

示例 1:
	输入: ["flower","flow","flight"]
	输出: "fl"

示例 2:
	输入: ["dog","racecar","car"]
	输出: ""
	解释: 输入不存在公共前缀。

说明:
	所有输入只包含小写字母 a-z 。
*/


class MySolution { 
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return new String();
        
        int longest = strs[0].length();
        for (int i = 1; i < strs.length; i++) {
            int len = commonPrefix(strs[0], strs[i]);
            if (len < longest) longest = len;
        }
        
        return strs[0].substring(0, longest);
    }
    
    private int commonPrefix(String str1, String str2) {
        int minLen = Math.min(str1.length(), str2.length());
        for (int i = 0; i < minLen; i++) {
            if (str1.charAt(i) != str2.charAt(i))
                return i;
        }
        return minLen;
    }
}


class Solution1 {
    public String longestCommonPrefix(String[] strs) {
         if (strs == null || strs.length == 0) return "";

        StringBuilder sb = new StringBuilder(strs[0]);
        if (strs.length == 1) return sb.toString();

        int i = sb.length() - 1, j = 1;
        while (i >= 0 && j < strs.length){
            if (strs[j].indexOf(sb.toString()) != 0) {
                sb.deleteCharAt(i);
                i--;
            } else
                j++;
        }

        return sb.toString();
    }
}
