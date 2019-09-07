/*
 * https://leetcode-cn.com/problems/reverse-vowels-of-a-string/ 

编写一个函数，以字符串作为输入，反转该字符串中的元音字母。

示例 1:
	输入: "hello"
	输出: "holle"

示例 2:
	输入: "leetcode"
	输出: "leotcede"

说明: 元音字母不包含字母"y"。

-----------------------------------------------------------------------------

Write a function that takes a string as input and reverse only the vowels of a string.

Example 1:
	Input: "hello"
	Output: "holle"

Example 2:
	Input: "leetcode"
	Output: "leotcede"
	
Note: The vowels does not include the letter "y".
*/


class MySolution {
    public String reverseVowels(String s) {
        if (s == null || s.length() < 2) return s;
        
        char[] chars = s.toCharArray();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (isVowel(chars[i]))
                list.add(i);
        }

        int size = list.size();
        if (size < 2) return s;
        
        for (int i = 0; i < size / 2; i++) {
            int left = list.get(i);
            int right = list.get(size - 1 - i);
            char tmp = chars[right];
            chars[right] = chars[left];
            chars[left] = tmp;
        }

        return new String(chars);
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' ||
               c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }
}

class Solution1 {
    public String reverseVowels(String s) {
        if (s == null) return s;
        
        char[] arr = s.toCharArray();
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            while (left < right && !isVowel(arr[left])) left++;
            
            while (left < right && !isVowel(arr[right])) right--;
            
            if (left >= right) break;
            
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
        return new String(arr);
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'
                || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U'
                ;
    }
}

