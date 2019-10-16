/*
 * https://leetcode-cn.com/problems/minimum-number-of-arrows-to-burst-balloons/ 
 
在二维空间中有许多球形的气球。对于每个气球，提供的输入是水平方向上，气球直径的开始和结束坐标。由于它是水平的，所以y坐标并不重要，因此只要知道开始和结束的x坐标就足够了。开始坐标总是小于结束坐标。平面内最多存在104个气球。

一支弓箭可以沿着x轴从不同点完全垂直地射出。在坐标x处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足  xstart ≤ x ≤ xend，则该气球会被引爆。可以射出的弓箭的数量没有限制。 弓箭一旦被射出之后，可以无限地前进。我们想找到使得所有气球全部被引爆，所需的弓箭的最小数量。

Example:
	输入: [[10,16], [2,8], [1,6], [7,12]]
	输出: 2
	解释: 对于该样例，我们可以在x = 6（射爆[2,8],[1,6]两个气球）和 x = 11（射爆另外两个气球）。

----------------------------------------------------------------------------------------------------

There are a number of spherical balloons spread in two-dimensional space. For each balloon, provided input is the start and end coordinates of the horizontal diameter. Since it's horizontal, y-coordinates don't matter and hence the x-coordinates of start and end of the diameter suffice. Start is always smaller than end. There will be at most 104 balloons.

An arrow can be shot up exactly vertically from different points along the x-axis. A balloon with xstart and xend bursts by an arrow shot at x if xstart ≤ x ≤ xend. There is no limit to the number of arrows that can be shot. An arrow once shot keeps travelling up infinitely. The problem is to find the minimum number of arrows that must be shot to burst all balloons.

Example:
	Input: [[10,16], [2,8], [1,6], [7,12]]
	Output: 2

Explanation: One way is to shoot one arrow for example at x = 6 (bursting the balloons [2,8] and [1,6]) and another arrow at x = 11 (bursting the other two balloons).
*/

class MySolution {
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] arr1, int[] arr2) {
                return arr1[0] - arr2[0];
            }
        });

        int min = 0;
        int[] intersection = points[0];
        for (int i = 1; i < points.length; i++) {
            int[] point = points[i];

            if (intersection[1] >= point[0]) {
                intersection[0] = Math.max(intersection[0], point[0]);
                intersection[1] = Math.min(intersection[1], point[1]);

            } else {
                min++;
                intersection = point;
            }
        }

        return min + 1;
    }
}

class Solution1 {
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[1] - b[1];
            }
        });

        if (points.length == 0) {
            return 0;
        }

        int count = 1;
        int spanEnd = points[0][1];
        for (int i = 1; i < points.length; i++) {
            int start = points[i][0];
            if (start > spanEnd) {
                count += 1;
                spanEnd = points[i][1];
            }
        }
        return count;
    }
}
