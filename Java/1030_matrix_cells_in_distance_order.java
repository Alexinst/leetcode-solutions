/*
给出 R 行 C 列的矩阵，其中的单元格的整数坐标为 (r, c)，满足 0 <= r < R 且 0 <= c < C。

另外，我们在该矩阵中给出了一个坐标为 (r0, c0) 的单元格。

返回矩阵中的所有单元格的坐标，并按到 (r0, c0) 的距离从最小到最大的顺序排，其中，两单元格(r1, c1) 和 (r2, c2) 之间的距离是曼哈顿距离，|r1 - r2| + |c1 - c2|。（你可以按任何满足此条件的顺序返回答案。）

示例 1：
	输入：R = 1, C = 2, r0 = 0, c0 = 0
	输出：[[0,0],[0,1]]
	解释：从 (r0, c0) 到其他单元格的距离为：[0,1]

示例 2：
	输入：R = 2, C = 2, r0 = 0, c0 = 1
	输出：[[0,1],[0,0],[1,1],[1,0]]
	解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2]
		[[0,1],[1,1],[0,0],[1,0]] 也会被视作正确答案。

示例 3：
	输入：R = 2, C = 3, r0 = 1, c0 = 2
	输出：[[1,2],[0,2],[1,1],[0,1],[1,0],[0,0]]
	解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2,2,3]
	其他满足题目要求的答案也会被视为正确，例如 [[1,2],[1,1],[0,2],[1,0],[0,1],[0,0]]。

提示：
1. 1 <= R <= 100
2. 1 <= C <= 100
3. 0 <= r0 < R
4. 0 <= c0 < C

---------------------------------------------------------------------------------------------------

Return the coordinates of all cells in the matrix, sorted by their distance from (r0, c0) from smallest distance to largest distance.  Here, the distance between two cells (r1, c1) and (r2, c2) is the Manhattan distance, |r1 - r2| + |c1 - c2|.  (You may return the answer in any order that satisfies this condition.)

Example 1:
	Input: R = 1, C = 2, r0 = 0, c0 = 0
	Output: [[0,0],[0,1]]
	Explanation: The distances from (r0, c0) to other cells are: [0,1]

Example 2:
	Input: R = 2, C = 2, r0 = 0, c0 = 1
	Output: [[0,1],[0,0],[1,1],[1,0]]
	Explanation: The distances from (r0, c0) to other cells are: [0,1,1,2]
		         The answer [[0,1],[1,1],[0,0],[1,0]] would also be accepted as correct.

Example 3:
	Input: R = 2, C = 3, r0 = 1, c0 = 2
	Output: [[1,2],[0,2],[1,1],[0,1],[1,0],[0,0]]
	Explanation: The distances from (r0, c0) to other cells are: [0,1,1,2,2,3]
	             There are other answers that would also be accepted as correct, such as [[1,2],[1,1],[0,2],[1,0],[0,1],[0,0]].
 

Note:
1. 1 <= R <= 100
2. 1 <= C <= 100
3. 0 <= r0 < R
4. 0 <= c0 < C

*/

class Solution {
    
    private int r0;
    private int c0;

    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        this.r0 = r0;
        this.c0 = c0;
        int[][] res = new int[R * C][2];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                res[i * C + j][0] = i;
                res[i * C + j][1] = j;
            }
        }

        Arrays.sort(res, new DistComparator());
        return res;
    }

    class DistComparator implements Comparator<int[]> {
        @Override
        public int compare(int[] crd1, int[] crd2) { // coordinate
            int dist1 = Math.abs(crd1[0] - r0) + Math.abs(crd1[1] - c0);
            int dist2 = Math.abs(crd2[0] - r0) + Math.abs(crd2[1] - c0);
            return dist1 - dist2;
        }
    }
}

class Solution1 {
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        int[][] res = new int[R * C][];
        int resIndex = 0;
        res[resIndex++] = new int[]{r0, c0};
        
        int r, c;
        for (int i = 1; resIndex < R * C; ++i) {
            //
            r = r0 - i;
            c = c0;
            if (r < 0) {
                c += 0 - r;
                r = 0;
            }
            while (r <= r0 && c < C) {
                res[resIndex++] = new int[]{r, c};
                ++r;
                ++c;
            }
            
            //
            r = r0 - i;
            c = c0;
            if (r < 0) {
                c -= 0 - r;
                r = 0;
            }
            else {
                ++r;
                --c;  
            }

            while (r <= r0 && c >= 0) {
                res[resIndex++] = new int[]{r, c};
                ++r;
                --c;
            }
            
            //
            r = r0 + i;
            c = c0;
            if (r >= R) {
                c += r - R + 1;
                r = R - 1;
            }
            while (r > r0 && c < C) {
                res[resIndex++] = new int[]{r, c};
                --r;
                ++c;
            }
            
            // 
            r = r0 + i;
            c = c0;
            if (r >= R) {
                c -= r - R + 1;
                r = R - 1;
            }
            else {
                --r;
                --c;                
            }
            while (r > r0 && c >= 0) {
                res[resIndex++] = new int[]{r, c};
                --r;
                --c;
            }
            
        }
        return res;
    }
}
