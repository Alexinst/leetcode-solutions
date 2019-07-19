/*
给出一个区间的集合，请合并所有重叠的区间。

示例 1:
	输入: [[1,3],[2,6],[8,10],[15,18]]
	输出: [[1,6],[8,10],[15,18]]
	解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].

示例 2:
	输入: [[1,4],[4,5]]
	输出: [[1,5]]
	解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。 

-----------------------------------------------------------

Given a collection of intervals, merge all overlapping intervals.

Example 1:
	Input: [[1,3],[2,6],[8,10],[15,18]]
	Output: [[1,6],[8,10],[15,18]]
	Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
	
Example 2:
	Input: [[1,4],[4,5]]
	Output: [[1,5]]
	Explanation: Intervals [1,4] and [4,5] are considered overlapping.

NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.

*/

class Solution1 {
    class IntervalComparator implements Comparator<int[]> {
        @Override
        public int compare(int[] a, int[] b) {
            return a[0] < b[0] ? -1 : a[0] == b[0] ? 0 : 1;
        }
    }
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new IntervalComparator());

        LinkedList<int[]> list = new LinkedList<>();
        for (int[] interval : intervals) {
            if (list.isEmpty() || list.getLast()[1] < interval[0])
                list.add(interval);
            else
                list.getLast()[1] = Math.max(list.getLast()[1], interval[1]);
        }

        int[][] ret = new int[list.size()][2];
        for (int i = 0; i < ret.length; i++) {
            ret[i][0] = list.get(i)[0];
            ret[i][1] = list.get(i)[1];
        }
        
        return ret;
    }
}

