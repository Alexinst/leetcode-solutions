/*
我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。

（这里，平面上两点之间的距离是欧几里德距离。）

你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。

示例 1：
	输入：points = [[1,3],[-2,2]], K = 1
	输出：[[-2,2]]
	解释：(1, 3) 和原点之间的距离为 sqrt(10)，
		  (-2, 2) 和原点之间的距离为 sqrt(8)，
	      由于 sqrt(8) < sqrt(10)，(-2, 2) 离原点更近。
		  我们只需要距离原点最近的 K = 1 个点，所以答案就是 [[-2,2]]。

示例 2：
	输入：points = [[3,3],[5,-1],[-2,4]], K = 2
	输出：[[3,3],[-2,4]]
	（答案 [[-2,4],[3,3]] 也会被接受。）
 
提示：
1. 1 <= K <= points.length <= 10000
2. -10000 < points[i][0] < 10000
3. -10000 < points[i][1] < 10000

----------------------------------------------------------------------------------------

We have a list of points on the plane.  Find the K closest points to the origin (0, 0).

(Here, the distance between two points on a plane is the Euclidean distance.)

You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)

Example 1:
	Input: points = [[1,3],[-2,2]], K = 1
	Output: [[-2,2]]
	Explanation: The distance between (1, 3) and the origin is sqrt(10).
				 The distance between (-2, 2) and the origin is sqrt(8).
	 			 Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
				 We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].

Example 2:
	Input: points = [[3,3],[5,-1],[-2,4]], K = 2
	Output: [[3,3],[-2,4]]
			(The answer [[-2,4],[3,3]] would also be accepted.)

Note:
1. 1 <= K <= points.length <= 10000
2. -10000 < points[i][0] < 10000
3. -10000 < points[i][1] < 10000
*/

class MySolution {
    class DistComparator implements Comparator<int[]> {
        @Override
        public int compare(int[] a, int[] b) {
            int distA = a[0] * a[0] + a[1] * a[1];
            int distB = b[0] * b[0] + b[1] * b[1];
            return distA - distB;       
		}
    }

    public int[][] kClosest(int[][] points, int K) {
        Arrays.sort(points, new DistComparator());
        return Arrays.copyOfRange(points, 0, K);
    }
}

class Solution1 {
    public int[][] kClosest(int[][] points, int K) {
        int l = 0, r = points.length-1;
       	while (l <= r) {
        	int mid = helper(points, l, r);
        	if (mid == K) break;
        	if (mid < K) {
            	l = mid + 1;
        	} else {
            	r = mid - 1;
        	}
    	}

        //假设已经前面K+1小的数组都找到了，然后就是填进去了

        return Arrays.copyOfRange(points, 0, K);
    }

    private int helper(int[][] points, int lo, int hi) {
        int[] pivot = points[lo];
        while (lo < hi) {
            while (lo < hi && compare(pivot, points[hi]) <= 0 ) {
                hi--;
            }
            points[lo] = points[hi];
            while (lo < hi && compare(pivot, points[lo]) >= 0) {
                lo++;
            }
            points[hi] = points[lo];
        }
        points[lo] = pivot;
        return lo;
    }

    private int compare(int[] p1, int[] p2) {
        return p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1];
    }
}
