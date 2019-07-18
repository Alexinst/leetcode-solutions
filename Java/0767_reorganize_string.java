/*
给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。

若可行，输出任意可行的结果。若不可行，返回空字符串。

示例 1:
	输入: S = "aab"
	输出: "aba"

示例 2:
	输入: S = "aaab"
	输出: ""

注意:
	S 只包含小写字母并且长度在[1, 500]区间内。

------------------------------------------------------------------------------

Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.

If possible, output any possible result.  If not possible, return the empty string.

Example 1:
	Input: S = "aab"
	Output: "aba"

Example 2:
	Input: S = "aaab"
	Output: ""

Note:
	S will consist of lowercase letters and have length in range [1, 500].

*/

class MySolution {
    public String reorganizeString(String S) {
        if (S.length() == 1)
            return S;

        // 统计字母出现次数
        Map<Character, Integer> map = new HashMap<>();
        char cMost = S.charAt(0);
        for (char c : S.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);

            if (map.get(cMost) < map.get(c))
                cMost = c;
        }

        // 某个字母出现次数超过字符串长度的一半，则不存在可行结果
        if (map.get(cMost) > (S.length() + 1) / 2)
            return "";

        // 在偶数位填充出现次数最多的字母
        char[] res = new char[S.length()];
        int even = 0;
        for (; even < map.get(cMost) * 2; even += 2) {
            res[even] = cMost;
        }

        // 将剩余字母统合到一起
        char[] tmp = new char[res.length - map.get(cMost)];
        int indexTmp = 0;
        for (char key : map.keySet()) {
            if (key == cMost) continue;

            for (int i = 0; i < map.get(key); i++) {
                tmp[indexTmp] = key;
                indexTmp++;
            }
        }

        // 继续在返回字符串的偶数位填充字母
        indexTmp = 0;
        for (; even < res.length; even += 2) {
            res[even] = tmp[indexTmp];
            indexTmp++;
        }

        // 在返回字符串的奇数位填充字母
        for (int odd = 1; odd < res.length; odd += 2) {
            res[odd] = tmp[indexTmp];
            indexTmp++;
        }

        return new String(res);
    }
}

class Solution1 {
    public String reorganizeString(String S) {
        int length = S.length();
        if (length == 1)
            return S;

        int[] arr = new int[26];
        int maxLen = 0;
        for (char c : S.toCharArray()) {
            if (maxLen < ++arr[c-'a'])
                maxLen = arr[c-'a'];
        }
        if (maxLen > (length + 1) / 2)
            return "";

        char[] ret = new char[S.length()];
        int even = 0, odd = 1;
        for (int i = 0;i < 26; i++) {
            while (arr[i] > 0 && arr[i] < (length/2+1) && odd < length) {
                ret[odd] = (char)(i + 'a');
                arr[i]--;
                odd += 2;
            }

            while (arr[i] > 0) {
                ret[even] = (char)(i + 'a');
                arr[i]--;
                even += 2;
            }
        }
        return new String(ret);
    }
}
