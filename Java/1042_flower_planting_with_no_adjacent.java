/*
 * https://leetcode-cn.com/problems/flower-planting-with-no-adjacent/ 

有 N 个花园，按从 1 到 N 标记。在每个花园中，你打算种下四种花之一。

paths[i] = [x, y] 描述了花园 x 到花园 y 的双向路径。

另外，没有花园有 3 条以上的路径可以进入或者离开。

你需要为每个花园选择一种花，使得通过路径相连的任何两个花园中的花的种类互不相同。

以数组形式返回选择的方案作为答案 answer，其中 answer[i] 为在第 (i+1) 个花园中种植的花的种类。花的种类用  1, 2, 3, 4 表示。保证存在答案。

 

示例 1：
	输入：N = 3, paths = [[1,2],[2,3],[3,1]]
	输出：[1,2,3]

示例 2：
	输入：N = 4, paths = [[1,2],[3,4]]
	输出：[1,2,1,2]

示例 3：
	输入：N = 4, paths = [[1,2],[2,3],[3,4],[4,1],[1,3],[2,4]]
	输出：[1,2,3,4]

提示：
    1. 1 <= N <= 10000
    2. 0 <= paths.size <= 20000
    3. 不存在花园有 4 条或者更多路径可以进入或离开。
    4. 保证存在答案。

------------------------------------------------------------------------------------------------------

You have N gardens, labelled 1 to N.  In each garden, you want to plant one of 4 types of flowers.

paths[i] = [x, y] describes the existence of a bidirectional path from garden x to garden y.

Also, there is no garden that has more than 3 paths coming into or leaving it.

Your task is to choose a flower type for each garden such that, for any two gardens connected by a path, they have different types of flowers.

Return any such a choice as an array answer, where answer[i] is the type of flower planted in the (i+1)-th garden.  The flower types are denoted 1, 2, 3, or 4.  It is guaranteed an answer exists.

 
Example 1:
	Input: N = 3, paths = [[1,2],[2,3],[3,1]]
	Output: [1,2,3]

Example 2:
	Input: N = 4, paths = [[1,2],[3,4]]
	Output: [1,2,1,2]

Example 3:
	Input: N = 4, paths = [[1,2],[2,3],[3,4],[4,1],[1,3],[2,4]]
	Output: [1,2,3,4]

Note:
    1. 1 <= N <= 10000
    2. 0 <= paths.size <= 20000
    3. No garden has 4 or more paths coming into or leaving it.
    4. It is guaranteed an answer exists.
*/

class Solution1 {
    public int[] gardenNoAdj(int N, int[][] paths) {
        int[][] map = new int[N][4];
        int[] ans = new int[N];

        for (int[] path : paths) {
            int a = path[0];
            int b = path[1];
            if (a > b) {
                map[a - 1][map[a - 1][3]++] = b - 1;
            } else {
                map[b - 1][map[b - 1][3]++] = a - 1;
            }
        }

        for (int i = 0; i < N; i++) {
            boolean[] use = new boolean[5];
            for (int j = 0; j < map[i][3]; j++) {
                use[ans[map[i][j]]] = true;
            }
            for (int j = 1; j < 5; j++) {
                if (!use[j]) {
                    ans[i] = j;
                    break;
                }
            }
        }

        return ans;
    }
}

class Solution2 {
    public int[] gardenNoAdj(int N, int[][] paths) {
        int[] rs = new int[N];
        int[][] table = new int[N][4];
        for (int[] path : paths) {
            int g1 = path[0], g2 = path[1];
            if (g1 > g2) {
                table[g1 - 1][table[g1 - 1][3]++] = g2 - 1;
            } else {
                table[g2 - 1][table[g2 - 1][3]++] = g1 - 1;
            }

        }
        for (int i = 0; i < N; i++) {
            int color = 1;
            while (true) {
                boolean correct = true;
                for (int j = 0; j < table[i][3]; j++) {
                    if (rs[table[i][j]] == color) {
                        color++;
                        correct = false;
                        break;
                    }
                }
                if (correct)
                    break;
            }
            rs[i] = color;
        }
        return rs;
    }
}

