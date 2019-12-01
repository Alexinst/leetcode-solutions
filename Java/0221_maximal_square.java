/*
 * https://leetcode-cn.com/problems/maximal-square/
 *
 * Reference: https://leetcode-cn.com/problems/maximal-square/solution/zui-da-zheng-fang-xing-by-leetcode/
 
在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。

示例:
	输入: 
	1 0 1 0 0
	1 0 1 1 1
	1 1 1 1 1
	1 0 0 1 0

	输出: 4

----------------------------------------------------------------------------------------------------

Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

Example:
	Input: 
	1 0 1 0 0
	1 0 1 1 1
	1 1 1 1 1
	1 0 0 1 0

	Output: 4
*/

class MySolution {
    public int maximalSquare(char[][] matrix) {
        int rows = matrix.length;
        int cols = rows > 0? matrix[0].length : 0;
        int maxSqLen = 0;
        int[] dp = new int[cols];


        for (int i = 0; i < rows; i++) {
            int prev = 0;
            for (int j = 0; j < cols; j++) {

                if (i == 0 || j == 0) {  // 第一行或第一列
                    prev = dp[j];
                    dp[j] = matrix[i][j] - '0';
                    
                } else {
                    int tmp = dp[j];
                    if (matrix[i][j] == '1') {
                        dp[j] = Math.min(prev,
                                Math.min(dp[j - 1], dp[j])) + 1;
                    } else {
                        dp[j] = 0;
                    }

                    prev = tmp;
                }

                maxSqLen = Math.max(maxSqLen, dp[j]);
            }
        }

        return maxSqLen * maxSqLen;
    }
}

class Solution1 {
    public int maximalSquare(char[][] matrix) {
        int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
        int[][] dp = new int[rows + 1][cols + 1];
        int maxsqlen = 0;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                if (matrix[i-1][j-1] == '1'){
                    dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
                    maxsqlen = Math.max(maxsqlen, dp[i][j]);
                }
            }
        }
        return maxsqlen * maxsqlen;
    }
}

class Solution2 {
    public int maximalSquare(char[][] matrix) {
        int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
        int[] dp = new int[cols + 1];
        int maxsqlen = 0, prev = 0;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                int temp = dp[j];
                if (matrix[i - 1][j - 1] == '1') {
                    dp[j] = Math.min(Math.min(dp[j - 1], prev), dp[j]) + 1;
                    maxsqlen = Math.max(maxsqlen, dp[j]);
                } else {
                    dp[j] = 0;
                }
                prev = temp;
            }
        }
        return maxsqlen * maxsqlen;
    }
}

class Solution3 {
    /**
     * num[height-1][width-1] 对应 height * width 子矩形"最大正方形"的边长
     */
    private int[][] num;

    /**
     * 矩形内"最大正方形"的边长
     */
    private int sideLen;

    /**
     * 用时最快 https://leetcode-cn.com/submissions/detail/36261372/
     * <p>
     * 思路：对于矩形的任意子矩形，定义：该子矩形内以右下角顶点为顶点的最大正方形为该子矩形的"最大正方形"
     */
    public int maximalSquare(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        // 矩阵的高
        int height = matrix.length;
        // 矩阵的宽
        int width = matrix[0].length;
        num = new int[height][width];
        sideLen = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                num[i][j] = -1;
            }
        }
        squareSearch(matrix, height, width);

        return sideLen * sideLen;
    }

    /**
     * 获得 height*width 子矩形"最大正方形"的边长（此处"最大正方形"定义见上面"思路"）
     */
    private int squareSearch(char[][] matrix, int height, int width) {
        if (height < 1 || width < 1) {
            return 0;
        }
        if (num[height - 1][width - 1] != -1) {
            return num[height - 1][width - 1];
        }

        // 左半侧子矩形"最大正方形"的边长
        int left = squareSearch(matrix, height, width - 1);
        // 上半侧子矩形"最大正方形"的边长
        int upper = squareSearch(matrix, height - 1, width);
        // 左上角子矩形"最大正方形"的边长
        int upLeft = squareSearch(matrix, height - 1, width - 1);

        // 上面3个长度的最小值
        int min = Math.min(Math.min(left, upper), upLeft);

        // 右下角顶点为'0'则该子矩形"最大正方形"的边长为0
        if (matrix[height - 1][width - 1] == '0') {
            num[height - 1][width - 1] = 0;
        } else {
            // 该子矩形"最大正方形"的边长
            num[height - 1][width - 1] = min + 1;
            sideLen = Math.max(num[height - 1][width - 1], sideLen);
        }
        return num[height - 1][width - 1];
    }
}
