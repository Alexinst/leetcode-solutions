/*
 * https://leetcode-cn.com/problems/partition-labels/ 
 
字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一个字母只会出现在其中的一个片段。返回一个表示每个字符串片段的长度的列表。

示例 1:
	输入: S = "ababcbacadefegdehijhklij"
	输出: [9,7,8]
	解释:
	划分结果为 "ababcbaca", "defegde", "hijhklij"。
	每个字母最多出现在一个片段中。
	像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。

注意:
	1. S的长度在[1, 500]之间。
	2. S只包含小写字母'a'到'z'。

----------------------------------------------------------------------------------------------------

A string S of lowercase letters is given. We want to partition this string into as many parts as possible so that each letter appears in at most one part, and return a list of integers representing the size of these parts.

Example 1:
	Input: S = "ababcbacadefegdehijhklij"
	Output: [9,7,8]
	Explanation:
	The partition is "ababcbaca", "defegde", "hijhklij".
	This is a partition so that each letter appears in at most one part.
	A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
	
Note:
	1. S will have length in range [1, 500].
	2. S will consist of lowercase letters ('a' to 'z') only.
*/

class MySolution {
    public List<Integer> partitionLabels(String S) {
        Map<Character, Integer> map = new HashMap<>();
        Stack<int[]> stack = new Stack<>();
        int start = 0;

        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (!map.containsKey(c))
                map.put(c, i);

            int firstLoc = map.get(c);
            while (!stack.isEmpty() && stack.peek()[1] >= firstLoc) {
                start = stack.pop()[0];
            }

            stack.push(new int[]{start, i});
            start = i + 1;

        }

        LinkedList<Integer> ans = new LinkedList<>();
        while (!stack.isEmpty()) {
            int[] section = stack.pop();
            ans.addFirst(section[1] - section[0] + 1);
        }

        return ans;
    }
}

class Solution1 {
    public List<Integer> partitionLabels(String S) {
        char[] array = S.toCharArray();
        int[] end = new int[26];
        for (int i = 0; i < array.length; i++) {
            end[array[i] - 97] = i;
        }

        List<Integer> list = new ArrayList<Integer>();
        int i = 0;
        while (i < array.length) {
            int count = 0;
            int endp = 0;

            while (true) {
                count++;
                endp = Math.max(endp, end[array[i] - 97]);

                if (endp == i) {
                    i++;
                    list.add(count);
                    break;
                }
                i++;
            }

        }
        return list;
    }
}
