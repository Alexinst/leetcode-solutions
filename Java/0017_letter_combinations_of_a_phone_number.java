/*
 * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/

给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
图片：https://assets.leetcode-cn.com/aliyun-lc-upload/original_images/17_telephone_keypad.png

示例:
	输入："23"
	输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].

说明:尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。

---------------------------------------------------------------------------------------------------

Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
Photo: https://assets.leetcode-cn.com/aliyun-lc-upload/original_images/17_telephone_keypad.png

Example:
	Input: "23"
	Ohutput: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].

Note: Although the above answer is in lexicographical order, your answer could be in any order you want.
*/

/**
 * 思路：回溯算法
 */
class MySolution {
    public List<String> letterCombinations(String digits) {
        List<String> coms = new ArrayList<>();
        if (digits.length() == 0) return coms;

        Map<Character, char[]> map = new HashMap<>();
        map.put('2', new char[]{'a', 'b', 'c'});
        map.put('3', new char[]{'d', 'e', 'f'});
        map.put('4', new char[]{'g', 'h', 'i'});
        map.put('5', new char[]{'j', 'k', 'l'});
        map.put('6', new char[]{'m', 'n', 'o'});
        map.put('7', new char[]{'p', 'q', 'r', 's'});
        map.put('8', new char[]{'t', 'u', 'v'});
        map.put('9', new char[]{'w', 'x', 'y', 'z'});

        dfs(map, digits.toCharArray(), coms, new StringBuilder(), 0);

        return coms;
    }

    private void dfs(Map<Character, char[]> map, char[] digits,
                     List<String> coms, StringBuilder com, int i) {
        if (i == digits.length) {
            coms.add(com.toString());
            return;
        }

        for (char c : map.get(digits[i])) {
            com.append(c);
            dfs(map, digits, coms, com, i + 1);
            com.deleteCharAt(com.length() - 1);
        }
    }
}

class Solution1 {
    public List<String> letterCombinations(String digits) {
        HashMap<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        List<String> list = new ArrayList<>();
        if (digits.length() == 0) {
            return list;
        }

        for (int i = 0; i < map.get(digits.charAt(0)).length(); i++) {
            list.add(Character.valueOf(map.get(digits.charAt(0)).charAt(i)).toString());
        }

        for (int i = 1; i < digits.length(); i++) {
            list = merge(list, map.get(digits.charAt(i)));
        }
        return list;
    }


    private List<String> merge(List<String> strs, String str2) {
        List<String> list = new ArrayList<>();
        for (String str : strs) {
            for (int i = 0; i < str2.length(); i++) {
                String strNew = str + str2.charAt(i);
                list.add(strNew);
            }
        }

        return list;
    }
}
