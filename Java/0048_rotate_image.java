/*
 * https://leetcode-cn.com/problems/rotate-image/

给定一个 n × n 的二维矩阵表示一个图像，将图像顺时针旋转 90 度。

说明：你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。

示例 1:
	给定 matrix = 
	[
	  [1,2,3],
	  [4,5,6],
	  [7,8,9]
	],

	原地旋转输入矩阵，使其变为:
	[
	  [7,4,1],
	  [8,5,2],
	  [9,6,3]
	]

示例 2:
	给定 matrix =
	[
	  [ 5, 1, 9,11],
	  [ 2, 4, 8,10],
	  [13, 3, 6, 7],
	  [15,14,12,16]
	], 

	原地旋转输入矩阵，使其变为:
	[
	  [15,13, 2, 5],
	  [14, 3, 4, 1],
	  [12, 6, 8, 9],
	  [16, 7,10,11]
	]

----------------------------------------------------------------------------------------------------------

You are given an n x n 2D matrix representing an image, Rotate the image by 90 degrees (clockwise).

Note: You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.

Example 1:
	Given input matrix = 
	[
	  [1,2,3],
	  [4,5,6],
	  [7,8,9]
	],

	rotate the input matrix in-place such that it becomes:
	[
	  [7,4,1],
	  [8,5,2],
	  [9,6,3]
	]

Example 2:
	Given input matrix =
	[
	  [ 5, 1, 9,11],
	  [ 2, 4, 8,10],
	  [13, 3, 6, 7],
	  [15,14,12,16]
	], 

	rotate the input matrix in-place such that it becomes:
	[
	  [15,13, 2, 5],
	  [14, 3, 4, 1],
	  [12, 6, 8, 9],
	  [16, 7,10,11]
	]
*/

class MySolution {
    public void rotate(int[][] matrix) {
        int len = matrix.length;
        if (len == 0 || len == 1) return;
        
        int[] dx = {1, 1, -1, -1};
        int[] dy = {1, -1, -1, 1};

        for (int i = 0; i < len / 2; i++) {
            int start = i;
            int end = len - i - 1;
            int steps = end - start;

            for (int j = start; j < end; j++) {
                int x = i, y = j, flag = -1;   // flag = 1, x先走； flag = -1, y先走
                int tmp1 = matrix[x][y];

                for (int count = 0; count < 4; count++) {
                    int stepsDup = steps;
                    if (flag == 1) {
                        do {
                            x += dx[count];
                        } while (stepsDup-- > 0 && x > start && x < end);
                        
                        while (stepsDup-- > 0 && y >= start && y <= end) {
                            y += dy[count];
                        }
                    } else {
                        do {
                            y += dy[count];
                        } while (stepsDup-- > 0 && y > start && y < end);

                        while (stepsDup-- > 0 && x >= start && x <= end)
                            x += dx[count];
                    }
                    flag *= -1;
                    
                    int tmp2 = matrix[x][y];
                    matrix[x][y] = tmp1;
                    tmp1 = tmp2;
                }
            }
        }
    }
}

class Solution1 {
    public void rotate(int[][] matrix) {
        int temp;
        //旋转多圈
        for (int i = 0; i < matrix.length / 2; i++) {
            //旋转一圈
            for (int j = i; j < matrix.length - i - 1; j++) {
                //旋转4个点
                temp = matrix[i][j];
                matrix[i][j] = matrix[matrix.length - 1 - j][i];
                
                matrix[matrix.length - 1 - j][i] = matrix[matrix.length - 1 - i][matrix.length - 1 - j];

                matrix[matrix.length - 1 - i][matrix.length - 1 - j] = matrix[j][matrix.length - 1 - i];

                matrix[j][matrix.length - 1 - i] = temp;
            }
        }
    }
}
