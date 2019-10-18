/*
 * https://leetcode-cn.com/problems/score-after-flipping-matrix/ 
 *
 * https://leetcode-cn.com/problems/score-after-flipping-matrix/solution/fan-zhuan-ju-zhen-hou-de-de-fen-by-leetcode/ 
 
有一个二维矩阵 A 其中每个元素的值为 0 或 1 。

移动是指选择任一行或列，并转换该行或列中的每一个值：将所有 0 都更改为 1，将所有 1 都更改为 0。

在做出任意次数的移动后，将该矩阵的每一行都按照二进制数来解释，矩阵的得分就是这些数字的总和。

返回尽可能高的分数。

示例：
	输入：[[0,0,1,1],[1,0,1,0],[1,1,0,0]]
	输出：39
	解释：
	转换为 [[1,1,1,1],[1,0,0,1],[1,1,1,1]]
	0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39

提示：
    1. 1 <= A.length <= 20
    2. 1 <= A[0].length <= 20
    3. A[i][j] 是 0 或 1

----------------------------------------------------------------------------------------------------

We have a two dimensional matrix A where each value is 0 or 1.

A move consists of choosing any row or column, and toggling each value in that row or column: changing all 0s to 1s, and all 1s to 0s.

After making any number of moves, every row of this matrix is interpreted as a binary number, and the score of the matrix is the sum of these numbers.

Return the highest possible score.

Example 1:
	Input: [[0,0,1,1],[1,0,1,0],[1,1,0,0]]
	Output: 39
	Explanation:
	Toggled to [[1,1,1,1],[1,0,0,1],[1,1,1,1]].
	0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39

Note:
    1. 1 <= A.length <= 20
    2. 1 <= A[0].length <= 20
    3. A[i][j] is 0 or 1.
*/

class MySolution {
    public int matrixScore(int[][] A) {
        int row = A.length, col = A[0].length;
        int[] nums = new int[row];

        // 逐行翻转数组
        int ones = (1 << col) - 1, compare = 1 << (col - 1);
        for (int i = 0; i < row; i++) {
            nums[i] = toInt(A[i], ones, compare);
        }
        
        // 逐列翻转数组
        for (int j = 1; j < col; j++) {
            int xor = 1 << (col - 1 - j);
            nums = transfer(nums, xor);
        }

        int score = 0;
        for (int n : nums) score += n;

        return score;
    }

    /**
     * 将二进制数数组 arr 转化为 十进制数 nums
     * 转化中，若高位不为 1，则对数组 arr 进行翻转
     *
     * @param arr 二进制数数组
     * @param xor 置 1 个数等同 arr 长度的数值
     * @param compare 边界值
     * @return 十进制数 nums
     */
    private int toInt(int[] arr, int xor, int compare) {
        int len = arr.length;
        int n = 0;
        for (int i = 0; i < len; i++) {
            n += arr[i] * (1 << (len - 1 - i));
        }

        if (n < compare) {
            n = xor ^ n;  // 等同于 xor - n
        }

        return n;
    }

    /**
     * 在翻转数组总和大于原数组（nums）总和时，返回翻转数数组；反之返回原数组
     * @param nums
     * @param xor 在特定位 置 1的数，且只有一个位 置 1。
     * @return
     */
    private int[] transfer(int[] nums, int xor) {
        int[] newNums = new int[nums.length];
        int count = 0;

        for (int k = 0; k < nums.length; k++) {
            newNums[k] = nums[k] ^ xor;
            count += nums[k] < newNums[k] ? 1 : 0;
        }

        if (count * 2 > nums.length)
            return newNums;
        else
            return nums;
    }
}


class Solution1 {
    public int matrixScore(int[][] A) {
        int R = A.length, C = A[0].length;
        int ans = 0;
        for (int c = 0; c < C; ++c) {
            int col = 0;
            for (int r = 0; r < R; ++r)
                col += A[r][c] ^ A[r][0];
            ans += Math.max(col, R - col) * (1 << (C-1-c));
        }
        return ans;
    }
}

