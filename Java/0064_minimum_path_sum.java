/*
给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。

说明：每次只能向下或者向右移动一步。

示例:
	输入:
		[
		  [1,3,1],
  		  [1,5,1],
  		  [4,2,1]
		]
	输出: 7
	解释: 因为路径 1->3->1->1->1 的总和最小。
*/

class MySolution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        
        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                if (x == 0 && y == 0)
                    dp[x][y] = grid[x][y];
                else if (x == 0)
                    dp[x][y] = dp[x][y - 1] + grid[x][y];
                else if (y == 0)
                    dp[x][y] = dp[x - 1][y] + grid[x][y];
                else
                    dp[x][y] = Math.min(dp[x - 1][y], dp[x][y - 1]) + grid[x][y];
            }
        }
        
        return dp[m - 1][n - 1];
    }
}

class Solution1 {
    private int[][] cache;
    
    public int minPathSum(int[][] grid) {
        // validate
        if (null == grid || 0 == grid.length || 0 == grid[0].length) {
            return 0;
        }
        cache = new int[grid[0].length][grid.length];
        return minPathSum(grid,0,0,grid.length,grid[0].length);
    }
    
    private int minPathSum(int[][] grid,int x,int y,int rows,int cols) {
        // base case
        if (x == cols-1) {
            int sum = 0;
            while (y < rows) {
                sum += grid[y][x];
                y++;
            }
            return sum;
        }
        if (y == rows-1) {
            int sum = 0;
            while (x < cols) {
                sum += grid[y][x];
                x++;
            }
            return sum;
        }
        // retrieve from cache
        if (0 != cache[x][y]) {
            return cache[x][y];
        }
        int res = grid[y][x] + Math.min(minPathSum(grid,x+1,y,rows,cols),minPathSum(grid,x,y+1,rows,cols));
        // update cache
        cache[x][y] = res;
        return res;
    }
}
