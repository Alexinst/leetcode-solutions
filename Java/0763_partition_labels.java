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
        int[][] sections = new int[26][2];

        char[] chars = S.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (sections[chars[i] - 97][0]== 0)
                sections[chars[i] - 97][0] = i + 1;

            sections[chars[i] - 97][1] = i + 1;
        }

        Arrays.sort(sections, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        int count = 0;
        int[] tmp = new int[2];
        Arrays.fill(tmp, 1);

        for (int i = 0; i < sections.length; ) {
            int[] sec = sections[i];

            if (sec[0] != 0) {
                if (tmp[1] >= sections[i][0]) {
                    tmp[0] = Math.min(tmp[0], sec[0]);
                    tmp[1] = Math.max(tmp[1], sec[1]);
                    i++;

                } else {
                    sections[count][0] = tmp[0];
                    sections[count][1] = tmp[1];

                    tmp[0] = tmp[1] + 1;
                    tmp[1] = tmp[1] + 1;

                    count++;
                }
            } else {
                i++;
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            ans.add(sections[i][1] - sections[i][0] + 1);
        }
        ans.add(tmp[1] - tmp[0] + 1);

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

class Solution2 {
    public List<Integer> partitionLabels(String str) {
        ArrayList<Integer> res = new ArrayList<>();
        int[] last = new int[26];
        char[] chars = str.toCharArray();

        // 记录每个字母最后出现的位置
        for (int i = 0; i < str.length(); i++) {
            last[chars[i] - 'a'] = i;
        }
        // preIndex表示上个区间的右端点
        // maxIndex表示当前遍历的字符最后出现位置的最大值
        int preIndex = -1, maxIndex = 0;
        for (int i = 0; i < chars.length; i++) {
            int index = last[chars[i] - 'a'];
            // 更新区间的右端点, 向右延展
            maxIndex = Math.max(maxIndex, index);
            // 如果当前位置i等于当前所遍历的字符最后出现位置的最大值
            // 说明maxIndex即为区间的右端点
            if (i == maxIndex) {
                res.add(i - preIndex);
                preIndex = i;
            }
        }
        return res;

    }
}
