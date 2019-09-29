/*
 * https://leetcode-cn.com/problems/binary-watch/ 
 
二进制手表顶部有 4 个 LED 代表小时（0-11），底部的 6 个 LED 代表分钟（0-59）。

每个 LED 代表一个 0 或 1，最低位在右侧。

	https://upload.wikimedia.org/wikipedia/commons/8/8b/Binary_clock_samui_moon.jpg
	例如，上面的二进制手表读取 “3:25”。

给定一个非负整数 n 代表当前 LED 亮着的数量，返回所有可能的时间。

案例:
	输入: n = 1
	返回: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]

注意事项:
	1. 输出的顺序没有要求。
	2. 小时不会以零开头，比如 “01:00” 是不允许的，应为 “1:00”。
	3. 分钟必须由两位数组成，可能会以零开头，比如 “10:2” 是无效的，应为 “10:02”。

---------------------------------------------------------------------------------------------------

A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the bottom represent the minutes (0-59).

Each LED represents a zero or one, with the least significant bit on the right.

	https://upload.wikimedia.org/wikipedia/commons/8/8b/Binary_clock_samui_moon.jpg
	For example, the above binary watch reads "3:25".

Given a non-negative integer n which represents the number of LEDs that are currently on, return all possible times the watch could represent.

Example:
	Input: n = 1
	Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]

Note:
	1. The order of output does not matter.
	2. The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".
	3. The minute must be consist of two digits and may contain a leading zero, for example "10:2" is not valid, it should be "10:02".

*/

class MySolution {
    public List<String> readBinaryWatch(int num) {
        List<String> ans = new ArrayList<>();
        if (num < 0 || num >= 9) return ans;

        boolean[] watch = new boolean[10];
        helper(ans, watch, 0, num);

        return ans;
    }

    private void helper(List<String> ans, boolean[] watch, int i, int left) {
        // 剩余空位置 1，全 1 位也少于 num
        if (watch.length - i < left) return;

        int h = toHour(watch);
        if (h >= 12) return;
        int m = toMin(watch);
        if (m >= 60) return;

        // 保存正确的时刻，并返回
        if (left <= 0) {
            ans.add(format(h, m));
            return;
        }

        helper(ans, watch, i + 1, left);
        watch[i] = true;
        helper(ans, watch, i + 1, left - 1);
        watch[i] = false;
    }

    private int toHour(boolean[] watch) {
        int h = 0;
        for (int i = 0; i < 4; i++) {
            if (watch[i])
                h += 1 << (3 - i);

            if (h >= 12) return h;
        }
        return h;
    }

    private int toMin(boolean[] watch) {
        int m = 0;
        for (int i = 4; i < 10; i++) {
            if (watch[i])
                m += 1 << (9 - i);

            if (m >= 60) return m;
        }
        return m;
    }

    private String format(int h, int m) {
        // 返回格式化的时刻
        StringBuilder time = new StringBuilder();
        time.append(h).append(':');
        if (m < 10) time.append(0);
        time.append(m);

        return time.toString();
    }
}

class Solution1 {
    public List<String> readBinaryWatch(int num) {
        HashMap<Integer, int[]> hour = new HashMap<>();
        hour.put(0, new int[]{0});
        hour.put(1, new int[]{1, 2, 4, 8});
        hour.put(2, new int[]{3, 5, 6, 9, 10});
        hour.put(3, new int[]{7, 11});

        HashMap<Integer, int[]> min = new HashMap<>();
        min.put(0, new int[]{0});
        min.put(1, new int[]{1, 2, 4, 8, 16, 32});
        min.put(2, new int[]{3, 5, 6, 9, 10, 12, 17, 18, 20, 24, 33, 34, 36, 40, 48});
        min.put(3, new int[]{7, 11, 13, 14, 19, 21, 22, 25, 26, 28, 35, 37, 38, 41, 42, 44, 49, 50, 52, 56});
        min.put(4, new int[]{15, 23, 27, 29, 30, 39, 43, 45, 46, 51, 53, 54, 57, 58});
        min.put(5, new int[]{31, 47, 55, 59});

        ArrayList<String> ans = new ArrayList<>();
        if (num < 0 || num > 11) {
            return ans;
        }
        for (int i = 0; i <= num && i < 4; i++) {
            if (min.containsKey(num - i)) {
                for (int j = 0; j < hour.get(i).length; j++) {
                    for (int k = 0; k < min.get(num - i).length; k++) {
                        StringBuilder value = new StringBuilder();
                        value.append(hour.get(i)[j]).append(':');
                        if (min.get(num - i)[k] < 10) value.append('0');
                        value.append(min.get(num - i)[k]);

                        ans.add(value.toString());
                    }
                }
            }
        }
        return ans;
    }
}
