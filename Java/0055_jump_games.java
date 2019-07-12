/*
给定一个非负整数数组，你最初位于数组的第一个位置。数组中的每个元素代表你在该位置可以跳跃的最大长度。
判断你是否能够到达最后一个位置。

示例 1:
	输入: [2,3,1,1,4]
	输出: true
	解释: 从位置 0 到 1 跳 1 步, 然后跳 3 步到达最后一个位置。

示例 2:
	输入: [3,2,1,0,4]
	输出: false
	解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。

https://leetcode-cn.com/problems/jump-game/solution/tiao-yue-you-xi-by-leetcode/
*/

class MySolution {
    public boolean canJump(int[] nums) {
        int len = nums.length;
        if (len == 0 || len == 1) return true;
        
        int[] dp = new int[len];
        dp[0] = 1;
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] == 1 && nums[j] >= i - j) {
                    dp[i] = 1;
                    break;
                }
            }
        }
        
        return dp[len-1] == 1;
    }
}

class Solution1 {
    public boolean canJump(int[] nums) {
        int last = nums.length - 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (i + nums[i] >= last) {
                last = i;
            }
        }
        return last == 0;
    }
}

enum Index {
    GOOD, BAD, UNKNOWN
}

class Solution2 {
    public boolean canJump(int[] nums) {
        Index[] memo = new Index[nums.length];
        for (int i = 0; i < memo.length; i++) {
            memo[i] = Index.UNKNOWN;
        }
        memo[memo.length - 1] = Index.GOOD;

        for (int i = nums.length - 2; i >= 0; i--) {
            int furthestJump = Math.min(i + nums[i], nums.length - 1);
            for (int j = i + 1; j <= furthestJump; j++) {
                if (memo[j] == Index.GOOD) {
                    memo[i] = Index.GOOD;
                    break;
                }
            }
        }

        return memo[0] == Index.GOOD;
    }
}

作者：LeetCode
链接：https://leetcode-cn.com/problems/two-sum/solution/tiao-yue-you-xi-by-leetcode/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
