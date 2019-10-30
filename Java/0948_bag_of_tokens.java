/*
 * https://leetcode-cn.com/problems/bag-of-tokens/ 
 *
 * Reference: https://leetcode-cn.com/problems/bag-of-tokens/solution/ling-pai-fang-zhi-by-leetcode/
 
令牌的值为 token[i]，每个令牌最多只能使用一次，可能的两种使用方法如下：
    1. 如果你至少有 token[i] 点能量，可以将令牌置为正面朝上，失去 token[i] 点能量，并得到 1 分。
    2. 如果我们至少有 1 分，可以将令牌置为反面朝上，获得 token[i] 点能量，并失去 1 分。

在使用任意数量的令牌后，返回我们可以得到的最大分数。

示例 1：
	输入：tokens = [100], P = 50
	输出：0
	
示例 2：
	输入：tokens = [100,200], P = 150
	输出：1
	
示例 3：
	输入：tokens = [100,200,300,400], P = 200
	输出：2
	 
提示：
	1. tokens.length <= 1000
	2. 0 <= tokens[i] < 10000
	3. 0 <= P < 10000

----------------------------------------------------------------------------------------------------

You have an initial power P, an initial score of 0 points, and a bag of tokens.

Each token can be used at most once, has a value token[i], and has potentially two ways to use it.
	1. If we have at least token[i] power, we may play the token face up, losing token[i] power, and gaining 1 point.
	2.If we have at least 1 point, we may play the token face down, gaining token[i] power, and losing 1 point.

Return the largest number of points we can have after playing any number of tokens.

Example 1:
	Input: tokens = [100], P = 50
	Output: 0

Example 2:
	Input: tokens = [100,200], P = 150
	Output: 1
	
Example 3:
	Input: tokens = [100,200,300,400], P = 200
	Output: 2
	 
Note:
	1. tokens.length <= 1000
	2. 0 <= tokens[i] < 10000
	3. 0 <= P < 10000
*/

class Solution1 {
    public int bagOfTokensScore(int[] tokens, int P) {
        Arrays.sort(tokens);

        int low = 0, high = tokens.length - 1;
        int points = 0, ans = 0;
        while (low <= high && (P >= tokens[low] || points > 0)) {
            // 能量 (P) 换分数 (points)
            while (low <= high && P >= tokens[low]) {
                P -= tokens[low++];
                points++;
            }

            ans = Math.max(ans, points);
            // 分数 (points) 换能量 (P)
            if (low <= high && points > 0) {
                P += tokens[high--];
                points--;
            }
        }

        return ans;
    }
}


