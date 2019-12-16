/*
 * https://leetcode-cn.com/problems/stone-game/ 
 *
 * Reference: https://leetcode-cn.com/problems/stone-game/
 
亚历克斯和李用几堆石子在做游戏。偶数堆石子排成一行，每堆都有正整数颗石子 piles[i] 。

游戏以谁手中的石子最多来决出胜负。石子的总数是奇数，所以没有平局。

亚历克斯和李轮流进行，亚历克斯先开始。 每回合，玩家从行的开始或结束处取走整堆石头。 这种情况一直持续到没有更多的石子堆为止，此时手中石子最多的玩家获胜。

假设亚历克斯和李都发挥出最佳水平，当亚历克斯赢得比赛时返回 true ，当李赢得比赛时返回 false 。

示例：
	输入：[5,3,4,5]
	输出：true
	解释：
	亚历克斯先开始，只能拿前 5 颗或后 5 颗石子 。
	假设他取了前 5 颗，这一行就变成了 [3,4,5] 。
	如果李拿走前 3 颗，那么剩下的是 [4,5]，亚历克斯拿走后 5 颗赢得 10 分。
	如果李拿走后 5 颗，那么剩下的是 [3,4]，亚历克斯拿走后 4 颗赢得 9 分。
	这表明，取前 5 颗石子对亚历克斯来说是一个胜利的举动，所以我们返回 true 。
	 

提示：
	1. 2 <= piles.length <= 500
	2. piles.length 是偶数。
	3. 1 <= piles[i] <= 500
	4. sum(piles) 是奇数。

----------------------------------------------------------------------------------------------------

Alex and Lee play a game with piles of stones.  There are an even number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].

The objective of the game is to end with the most stones.  The total number of stones is odd, so there are no ties.

Alex and Lee take turns, with Alex starting first.  Each turn, a player takes the entire pile of stones from either the beginning or the end of the row.  This continues until there are no more piles left, at which point the person with the most stones wins.

Assuming Alex and Lee play optimally, return True if and only if Alex wins the game.

Example 1:
	Input: [5,3,4,5]
	Output: true
	Explanation: 
	Alex starts first, and can only take the first 5 or the last 5.
	Say he takes the first 5, so that the row becomes [3, 4, 5].
	If Lee takes 3, then the board is [4, 5], and Alex takes 5 to win with 10 points.
	If Lee takes the last 5, then the board is [3, 4], and Alex takes 4 to win with 9 points.
	This demonstrated that taking the first 5 was a winning move for Alex, so we return true.
	 

Note:
	1. 2 <= piles.length <= 500
	2. piles.length is even.
	3. 1 <= piles[i] <= 500
	4. sum(piles) is odd.
*/

class Solution1 {
    public boolean stoneGame(int[] piles) {
        int N = piles.length;

        // dp[i+1][j+1] = the value of the game [piles[i], ..., piles[j]].
        int[][] dp = new int[N+2][N+2];
        for (int size = 1; size <= N; ++size)
            for (int i = 0; i + size <= N; ++i) {
                int j = i + size - 1;
                int parity = (j + i + N) % 2;  // j - i - N; but +x = -x (mod 2)
                if (parity == 1)
                    dp[i+1][j+1] = Math.max(piles[i] + dp[i+2][j+1], piles[j] + dp[i+1][j]);
                else
                    dp[i+1][j+1] = Math.min(-piles[i] + dp[i+2][j+1], -piles[j] + dp[i+1][j]);
            }

        return dp[1][N] > 0;
    }
}

class Solution2 {
	public boolean stoneGame(int[] piles) {
		return true;
	}
}
