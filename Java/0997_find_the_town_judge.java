/*
 * https://leetcode-cn.com/problems/find-the-town-judge/

在一个小镇里，按从 1 到 N 标记了 N 个人。传言称，这些人中有一个是小镇上的秘密法官。

如果小镇的法官真的存在，那么：
	1. 小镇的法官不相信任何人。
	2. 每个人（除了小镇法官外）都信任小镇的法官。
	3. 只有一个人同时满足属性 1 和属性 2 。
给定数组 trust，该数组由信任对 trust[i] = [a, b] 组成，表示标记为 a 的人信任标记为 b 的人。

如果小镇存在秘密法官并且可以确定他的身份，请返回该法官的标记。否则，返回 -1。

示例 1：
	输入：N = 2, trust = [[1,2]]
	输出：2

示例 2：
	输入：N = 3, trust = [[1,3],[2,3]]
	输出：3

示例 3：
	输入：N = 3, trust = [[1,3],[2,3],[3,1]]
	输出：-1

示例 4：
	输入：N = 3, trust = [[1,2],[2,3]]
	输出：-1

示例 5：
	输入：N = 4, trust = [[1,3],[1,4],[2,3],[2,4],[4,3]]
	输出：3

提示：
	1. 1 <= N <= 1000
	2. trust.length <= 10000
	3. trust[i] 是完全不同的
	4. trust[i][0] != trust[i][1]
	5. 1 <= trust[i][0], trust[i][1] <= N

-----------------------------------------------------------------------------------------------

In a town, there are N people labelled from 1 to N.  There is a rumor that one of these people is secretly the town judge.

If the town judge exists, then:
	1. The town judge trusts nobody.
	2. Everybody (except for the town judge) trusts the town judge.
	3. There is exactly one person that satisfies properties 1 and 2.
You are given trust, an array of pairs trust[i] = [a, b] representing that the person labelled a trusts the person labelled b.

If the town judge exists and can be identified, return the label of the town judge.  Otherwise, return -1.

Example 1:
	Input: N = 2, trust = [[1,2]]
	Output: 2

Example 2:
	Input: N = 3, trust = [[1,3],[2,3]]
	Output: 3

Example 3:
	Input: N = 3, trust = [[1,3],[2,3],[3,1]]
	Output: -1
	
Example 4:
	Input: N = 3, trust = [[1,2],[2,3]]
	Output: -1
	
Example 5:
	Input: N = 4, trust = [[1,3],[1,4],[2,3],[2,4],[4,3]]
	Output: 3

Note:
	1. 1 <= N <= 1000
	2. trust.length <= 10000
	3. trust[i] are all different
	4. trust[i][0] != trust[i][1]
	5. 1 <= trust[i][0], trust[i][1] <= N
*/

/**
 * 思路：法官的入度为 N-1，出度为 0 
 */
class MySolution {
    public int findJudge(int N, int[][] trust) {
        if (N == 1) return N;

        // degree[i][0]: i 的入度（indegree）
        // degree[i][1]: i 的出度（outdegree）
        int[][] degree = new int[N + 1][2];
        int judge = -1;

        for (int i = 0; i < trust.length; i++) {
            degree[trust[i][0]][1] += 1;
            degree[trust[i][1]][0] += 1;
        }

        for (int i = 1; i < degree.length; i++) {
            if (degree[i][0] == N - 1 && degree[i][1] == 0)
                judge = i;
        }
        return judge;
    }
}

class Solution1 {
    public int findJudge(int N, int[][] trust) {
        int[] nums = new int[N];
        for (int[] ints : trust) {
            nums[ints[0] - 1]--;
            nums[ints[1] - 1]++;
        }

        for (int i = 0; i < N; i++) {
            if (nums[i] == N - 1)
                return i + 1;
        }

        return -1;
    }
}
