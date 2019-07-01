/*
一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。

问总共有多少条不同的路径？



例如，上图是一个7 x 3 的网格。有多少可能的路径？

说明：m 和 n 的值均不超过 100。

示例 1:
	输入: m = 3, n = 2
	输出: 3
	解释:
	从左上角开始，总共有 3 条路径可以到达右下角。
		1. 向右 -> 向右 -> 向下
		2. 向右 -> 向下 -> 向右
		3. 向下 -> 向右 -> 向右
示例 2:
	输入: m = 7, n = 3
	输出: 28
*/

class Solution1 {
    public int uniquePaths(int m, int n) {
        int[][] routes = new int[m][n];
        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                if (x == 0 || y == 0)
                    routes[x][y] = 1;
                else
                    routes[x][y] = routes[x-1][y] + routes[x][y-1];
            }
        }
        
        return routes[m-1][n-1];
    }
}

class Solution2 {
    public int uniquePaths(int m, int n) {
        int N = m + n - 2;
        int M = m < n ? m - 1 : n - 1;
        // 计算 C(N,M)
        // 根据：C(N,M)=C(N,M-1)*M/(N-M+1);
        long ans = 1;
        for (int i = 1; i <= M; i++)
            ans = ans * (N - i + 1) / i;
        return (int)ans;
    }
}
