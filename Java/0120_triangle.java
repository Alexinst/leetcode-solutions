/*
 * https://leetcode-cn.com/problems/triangle/

给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。

例如，给定三角形：
	[
		 [2],
		[3,4],
	   [6,5,7],
	  [4,1,8,3]
	]

	自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。

说明：
	如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。

--------------------------------------------------------------------------------------------------

Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

For example, given the following triangle
	[
		 [2],
		[3,4],
	   [6,5,7],
	  [4,1,8,3]
	]

	The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Note:
	Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
*/

/**
 * 思路：动态规划（Dynamic Programming）
 * 自右而左：保证上一层的 dp 在用到之前不会被覆盖
 * 例如：对于 triangle[5][2] 的 dp[2] = Math.min(dp[1], dp[2]) + triangle[5][2],
 *       对应 triangle[5][3] 的 dp[3] = Math.min(dp[2], dp[3]) + triangle[5][3], 然而 dp[2] 已经被覆盖，算出的 dp[3] 就是错的
 */
class MySolution {
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.size() == 0) return 0;
        
        int rows = triangle.size();
        int[] dp = new int[rows];
        dp[0] = triangle.get(0).get(0);
        int min = dp[0];

        for (int i = 1; i < rows; i++) {
            List<Integer> list = triangle.get(i);
            for (int j = i; j >= 0; j--) {
                int shorter = 0;	// 相邻路径中的更短者
                if (j == 0) 
                    shorter = dp[j];
                else if (j == i) 
                    shorter = dp[j - 1];
                else
                    shorter = Math.min(dp[j - 1], dp[j]);
                dp[j] = shorter + list.get(j);
                
                if (i + 1 == rows) {	// 遍历到最后一行
                    if (j == list.size() - 1)
                        min = dp[j];
                    else
                        min = Math.min(min, dp[j]);
                }
            }
        }

        return min;
    }
}

/**
 * 自下而上，自右而左
 * 不会覆盖，也不用考虑越界问题
 */
class Solution1 {
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.size() == 0) return 0;

        int[] res = new int[triangle.size()];

        for (int i = 0; i < triangle.size(); i++)
            res[i] = triangle.get(triangle.size() - 1).get(i);


        for (int i = triangle.size() - 2; i >= 0; i--) {
            List<Integer> list = triangle.get(i);
            for (int j = 0; j <= i; j++) {
                res[j] = Math.min(res[j], res[j + 1]) + list.get(j);
            }
        }

        return res[0];
    }
}
