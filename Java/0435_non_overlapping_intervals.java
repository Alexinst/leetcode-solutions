/*
 * https://leetcode-cn.com/problems/non-overlapping-intervals/
 *
 * Reference: https://leetcode-cn.com/problems/non-overlapping-intervals/

给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。

注意:
    1. 可以认为区间的终点总是大于它的起点。
    2. 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。

示例 1:
	输入: [ [1,2], [2,3], [3,4], [1,3] ]
	输出: 1
	解释: 移除 [1,3] 后，剩下的区间没有重叠。

示例 2:
	输入: [ [1,2], [1,2], [1,2] ]
	输出: 2
	解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。

示例 3:
	输入: [ [1,2], [2,3] ]
	输出: 0
	解释: 你不需要移除任何区间，因为它们已经是无重叠的了。

----------------------------------------------------------------------------------------------------

Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.

Note:
    1. You may assume the interval's end point is always bigger than its start point.
    2. Intervals like [1,2] and [2,3] have borders "touching" but they don't overlap each other.

Example 1:
	Input: [ [1,2], [2,3], [3,4], [1,3] ]
	Output: 1
	Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.

Example 2:
	Input: [ [1,2], [1,2], [1,2] ]
	Output: 2
	Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.

Example 3:
	Input: [ [1,2], [2,3] ]
	Output: 0
	Explanation: You don't need to remove any of the intervals since they're already non-overlapping.

NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
*/

class MySolution {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length < 2) return 0;

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] arr1, int[] arr2) {
                if (arr1[0] != arr2[0])
                    return arr1[0] - arr2[0];
                else
                    return arr1[1] - arr2[1];
            }
        });

        int n = 0;
        int left = intervals[0][0], right = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i];
            if (interval[0] == left || interval[0] < right) {
                n++;
            }

            if (left < interval[0] && interval[1] < right || right <= interval[0]) {
                left = interval[0];
                right = interval[1];
            }
        }

        return n;
    }
}

/**
 * 从起始点的动态规划
 */
class Solution1 {
    class myComparator implements Comparator<int[]> {
        public int compare(int[] a, int[] b) {
            return a[0] - b[0];
        }
    }
    public boolean isOverlapping(int[] i, int[] j) {
        return i[1] > j[0];
    }
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, new myComparator());
        int dp[] = new int[intervals.length];
        dp[0] = 1;
        int ans = 1;
        for (int i = 1; i < dp.length; i++) {
            int max = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (!isOverlapping(intervals[j], intervals[i])) {
                    max = Math.max(dp[j], max);
                }
            }
            dp[i] = max + 1;
            ans = Math.max(ans, dp[i]);

        }
        return intervals.length - ans;
    }
}

/**
 * 从终点的动态规划
 */
class Solution2 {
    class myComparator implements Comparator<int[]> {
        public int compare(int[] a, int[] b) {
            return a[0] - b[1];
        }
    }

    public boolean isOverlapping(int[] i, int[] j) {
        return i[1] > j[0];
    }

    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, new myComparator());
        int dp[] = new int[intervals.length];
        dp[0] = 1;
        int ans = 1;
        for (int i = 1; i < dp.length; i++) {
            int max = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (!isOverlapping(intervals[j], intervals[i])) {
                    max = Math.max(dp[j], max);
                    break;
                }
            }
            dp[i] = Math.max(max + 1, dp[i - 1]);
            ans = Math.max(ans, dp[i]);
        }
        return intervals.length - ans;
    }
}


/**
 * 从起点的贪心算法
 */
class Solution3 {
    class myComparator implements Comparator<int[]> {
        public int compare(int[] a, int[] b) {
            return a[0] - b[0];
        }
    }
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, new myComparator());
        int end = intervals[0][1], prev = 0, count = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[prev][1] > intervals[i][0]) {
                if (intervals[prev][1] > intervals[i][1]) {
                    prev = i;
                }
                count++;
            } else {
                prev = i;
            }
        }
        return count;
    }
}

/**
 * 从终点的贪心算法
 */
class Solution4 {
    class myComparator implements Comparator<int[]> {
        public int compare(int[] a, int[] b) {
            return a[1] - b[1];
        }
    }
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, new myComparator());
        int end = intervals[0][1];
        int count = 1;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= end) {
                end = intervals[i][1];
                count++;
            }
        }
        return intervals.length - count;
    }
}
