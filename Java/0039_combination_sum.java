/*
 * https://leetcode-cn.com/problems/combination-sum/submissions/

给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的数字可以无限制重复被选取。

说明：
    1. 所有数字（包括 target）都是正整数。
    2. 解集不能包含重复的组合。 

示例 1:
	输入: candidates = [2,3,6,7], target = 7,
	所求解集为:
	[
	  [7],
	  [2,2,3]
	]

示例 2:
	输入: candidates = [2,3,5], target = 8,
	所求解集为:
	[
	  [2,2,2,2],
	  [2,3,3],
	  [3,5]
	]

---------------------------------------------------------------------------------------------------

Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

The same repeated number may be chosen from candidates unlimited number of times.

Note:
    1. All numbers (including target) will be positive integers.
    2. The solution set must not contain duplicate combinations.

Example 1:
	Input: candidates = [2,3,6,7], target = 7,
	A solution set is:
	[
	  [7],
	  [2,2,3]
	]

Example 2:
	Input: candidates = [2,3,5], target = 8,
	A solution set is:
	[
	  [2,2,2,2],
	  [2,3,3],
	  [3,5]
	]
*/

class MySolution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> combinations = new ArrayList<>();
        backtrack(candidates, combinations, new LinkedList<>(), 0, target);

        return combinations;
    }

    private void backtrack(int[] candidates, List<List<Integer>> combinations,
                           LinkedList<Integer> com, int i, int left) {
        if (left == 0) {
            combinations.add(new LinkedList<Integer>(com));
            return;
        }

        for (; i < candidates.length; i++) {
            int complement = left - candidates[i];
            if (complement < 0) return;

            com.add(candidates[i]);
            backtrack(candidates, combinations, com, i, complement);
            com.pollLast();
        }
    }
}

class Solution1 {
    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        backtrack(candidates, 0, target, new ArrayList<Integer>());
        return res;
    }

    private void backtrack(int[] candidates, int start, int target, ArrayList<Integer> temp_list) {
        if (target < 0)
            return;
        if (target == 0) {
            res.add(new ArrayList<>(temp_list));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (target < candidates[i])
                break;
            temp_list.add(candidates[i]);
            backtrack(candidates, i, target - candidates[i], temp_list);
            temp_list.remove(temp_list.size() - 1);
        }

    }
}
