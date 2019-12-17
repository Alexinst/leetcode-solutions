/*
 * https://leetcode-cn.com/problems/combination-sum-iv/ 
 *
 * Reference: https://leetcode-cn.com/problems/combination-sum-iv/solution/python-zi-ding-xiang-xia-hui-su-huan-cun-cha-zhao-/
 
给定一个由正整数组成且不存在重复数字的数组，找出和为给定目标正整数的组合的个数。

示例:
	nums = [1, 2, 3]
	target = 4

	所有可能的组合为：
	(1, 1, 1, 1)
	(1, 1, 2)
	(1, 2, 1)
	(1, 3)
	(2, 1, 1)
	(2, 2)
	(3, 1)

	请注意，顺序不同的序列被视作不同的组合。

	因此输出为 7。

----------------------------------------------------------------------------------------------------

Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.

Example:
	nums = [1, 2, 3]
	target = 4

	The possible combination ways are:
	(1, 1, 1, 1)
	(1, 1, 2)
	(1, 2, 1)
	(1, 3)
	(2, 1, 1)
	(2, 2)
	(3, 1)

	Note that different sequences are counted as different combinations.

	Therefore the output is 7.
*/

class MySolution {
    private Map<Integer, Integer> map = new HashMap<>();

    public int combinationSum4(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        Arrays.sort(nums);
        return helper(nums, target);
    }

    private int helper(int[] nums, int target) {
        if (map.containsKey(target)) return map.get(target);
        if (target == 0) return 1;
        if (target < 0 || target < nums[0]) return 0;

        int res = 0;
        for (int n : nums) {
            res += helper(nums, target - n);
        }
        map.put(target, res);

        return res;
    }
}

class Solution1 {
    public int combinationSum4(int[] nums, int target) {
        if (nums.length == 0) return 0;
        int[] memo = new int[target + 1];
        memo[0] = 1;
        for (int i = 1; i < memo.length; i++) memo[i] = -1;
        int res = com(nums, target, memo);
        return res;
    }

    public int com(int[] nums, int target, int[] memo) {
        if (target < 0) return 0;
        if (memo[target] != -1) return memo[target];

        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res += com(nums, target - nums[i], memo);
        }

        memo[target] = res;
        return memo[target];
    }
}
