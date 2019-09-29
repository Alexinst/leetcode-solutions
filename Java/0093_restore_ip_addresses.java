/*
 * https://leetcode-cn.com/problems/restore-ip-addresses/

给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。

示例:
	输入: "25525511135"
	输出: ["255.255.11.135", "255.255.111.35"]

---------------------------------------------------------------------------------------------------

Given a string containing only digits, restore it by returning all possible valid IP address combinations.

Example:
	Input: "25525511135"
	Output: ["255.255.11.135", "255.255.111.35"]
*/

class MySolution {
    private List<String> ans = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        if (s.length() < 4) return ans;

        backtrack(s, 0, new LinkedList<>());
        return ans;
    }

    /**
     * 
     * @param s
     * @param i 对字符串 s 的索引
     * @param blocks 分块存放 ip 地址的容器
     */
    private void backtrack(String s, int i, LinkedList<String> blocks) {
        if (i == s.length()) {
            StringBuilder ip = new StringBuilder();
            int j = 0;
            while (j < 3) {
                ip.append(blocks.get(j)).append(".");
                j++;
            }
            ip.append(blocks.get(j));

            ans.add(ip.toString());
            return;
        }

        for (int j = 1; j <= 3; j++) {
			// 除非数字是 0, 否则不能以 0 开头
            if (s.charAt(i) == '0' && j > 1) return;
			
			// i + j 不能越界；若剩余字符串长度太长或太短，则跳过本次迭代
            if (i + j > s.length() ||
                    s.length() - i - j > (3 - blocks.size()) * 3 ||
                    s.length() - i - j < (3 - blocks.size()))
                continue;
			
			// 若数字为百位数，需要检查其是否小于256
            if (j == 3 && !isValid(s, i, j))
                continue;

            blocks.add(s.substring(i, i + j));
            backtrack(s, i + j, blocks);
            blocks.pollLast();
        }
    }

    /**
     * 
     * @param s
     * @param start 起始索引
     * @param len 数字长度
     * @return
     */
    private boolean isValid(String s, int start, int len) {
        int n = 0;
        for (int i = start; i < start + len; i++) {
            n += (s.charAt(i) - '0') * (int) Math.pow(10, (start + len - 1 - i));
        }

        return n <= 255;
    }
}

class Solution1 {
    public List<String> restoreIpAddresses(String s) {
        List<String> results = new ArrayList<>();
        if (s == null || s.length() < 4) return results;
        char[] buffer = new char[s.length() + 3];
        backtracking(s, 0, buffer, 0, results);
        return results;
    }

    private void backtracking(String s, int start, char[] buffer, int count, List<String> result) {
        if (start == s.length() && count == 4) {
            result.add(new String(buffer));
            return;
        }
        if (start >= s.length()) return;
        if (count >= 4) return;

        int n = 0;
        for (int end = start; end < start + 3 && end < s.length(); end++) {
            if (end > start && s.charAt(start) == '0') break;
            n = n * 10 + (s.charAt(end) - '0');
            if (n > 255) break;
            buffer[end + count] = s.charAt(end);
            if (count < 3) buffer[end + count + 1] = '.';
            backtracking(s, end + 1, buffer, count + 1, result);
        }
    }
}

