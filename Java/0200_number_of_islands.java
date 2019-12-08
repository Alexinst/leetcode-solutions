/*
 * https://leetcode-cn.com/problems/number-of-islands/ 
 
给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。你可以假设网格的四个边均被水包围。

示例 1:
	输入:
		11110
		11010
		11000
		00000
	输出: 1

示例 2:
	输入:
		11000
		11000
		00100
		00011
	输出: 3

----------------------------------------------------------------------------------------------------

Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:
	Input:
		11110
		11010
		11000
		00000
	Output: 1
	
Example 2:
	Input:
		11000
		11000
		00100
		00011
	Output: 3
*/

class MySolution {
    public int numIslands(char[][] grid) {
        if (grid == null) return 0;
        int rows = grid.length;
        int cols = rows > 0 ? grid[0].length : 0;
        if (cols == 0) return 0;

        boolean[][] marked = new boolean[rows][cols];
        int num = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!marked[i][j] && grid[i][j] == '1') {
                    num++;
                    findBorder(grid, marked, i, j);
                }
            }
        }

        return num;
    }

    private void findBorder(char[][] grid, boolean[][] found, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) return;
        if (grid[i][j] == '0') return;
        if (found[i][j]) return;

        found[i][j] = true;
        findBorder(grid, found, i - 1, j);
        findBorder(grid, found, i + 1, j);
        findBorder(grid, found, i, j - 1);
        findBorder(grid, found, i, j + 1);
    }
}


class Solution1 {
    private char[][] grid;
    private int rows, cols;

    public int numIslands(char[][] grid) {
        this.grid = grid;
        this.rows = grid.length;
        if (rows == 0)  return 0;
        this.cols = grid[0].length;
        if (cols == 0)  return 0;

        int sum = 0;
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (grid[i][j] == '1') {
                    dfs(i, j);
                    ++sum;
                }
            }
        }

        return sum;
    }

    private void dfs(int i, int j) {
        if (grid[i][j] == '0')  return;

        grid[i][j] = '0';
        if (i > 0)         dfs(i - 1, j);
        if (i < rows - 1)  dfs(i + 1, j);
        if (j > 0)         dfs(i, j - 1);
        if (j < cols - 1)  dfs(i, j + 1);
    }
}

