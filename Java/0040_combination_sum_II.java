/*
 * https://leetcode-cn.com/problems/combination-sum-ii/
 
给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的每个数字在每个组合中只能使用一次。

说明：
    1. 所有数字（包括目标数）都是正整数。
    2. 解集不能包含重复的组合。 

示例 1:
	输入: candidates = [10,1,2,7,6,1,5], target = 8,
	所求解集为:
	[
	  [1, 7],
	  [1, 2, 5],
	  [2, 6],
	  [1, 1, 6]
	]

示例 2:
	输入: candidates = [2,5,2,1,2], target = 5,
	所求解集为:
	[
  	  [1,2,2],
  	  [5]
	]

---------------------------------------------------------------------------------------------------

Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

Each number in candidates may only be used once in the combination.

Note:
    1. All numbers (including target) will be positive integers.
    2. The solution set must not contain duplicate combinations.

Example 1:
	Input: candidates = [10,1,2,7,6,1,5], target = 8,
	A solution set is:
	[
	  [1, 7],
	  [1, 2, 5],
	  [2, 6],
	  [1, 1, 6]
	]

Example 2:
	Input: candidates = [2,5,2,1,2], target = 5,
	A solution set is:
	[
	  [1,2,2],
	  [5]
	]
*/

class MySolution {
    private List<List<Integer>> combinations = new ArrayList<>();
    private LinkedList<Integer> com = new LinkedList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);

        backtrack(candidates, 0, target);
        return combinations;
    }

    /**
     *
     * @param candidates
     * @param i 该次递归对 candidates 的起始索引
     * @param target
     */
    private void backtrack(int[] candidates, int i, int target) {
        if (target == 0) {
            combinations.add(new LinkedList<>(com));
            return ;
        }

        for (int j = i; j < candidates.length; j++) {
            // 剪枝：重复的组合
            if (j > i && candidates[j] == candidates[j - 1]) continue;

            int left = target - candidates[j];
            // 剪枝：不可能的组合
            if (left < 0) return;

            com.add(candidates[j]);
            backtrack(candidates, j + 1, left);
            com.pollLast();
        }
    }
}

class Solution1 {
    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        helper(candidates, 0, target, new ArrayList<>());

        return res;
    }

    public void helper(int[] nums, int start, int target, List<Integer> prev) {
        if (target < 0) return;
        if (target == 0) {
            res.add(new ArrayList<>(prev));
            return;
        }

        for (int i = start; i < nums.length; i++) {
            int curr = nums[i];
            if (curr > target) break;
            if (i > start && curr == nums[i - 1]) continue;

            prev.add(curr);
            helper(nums, i + 1, target - curr, prev);
            prev.remove(prev.size() - 1);
        }

        return;
    }
}
