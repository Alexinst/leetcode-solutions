/*
 * https://leetcode-cn.com/problems/subsets/ 
 
给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。

说明：解集不能包含重复的子集。

示例:
	输入: nums = [1,2,3]
	输出:
	[
	  [3],
	  [1],
	  [2],
	  [1,2,3],
	  [1,3],
	  [2,3],
	  [1,2],
	  []
	]

---------------------------------------------------------------------------------------------------

Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:
	Input: nums = [1,2,3]
	Output:
	[
	  [3],
	  [1],
	  [2],
	  [1,2,3],
	  [1,3],
	  [2,3],
	  [1,2],
	  []
	]
 */

class MySolution {
    private List<List<Integer>> permutations = new ArrayList<>();
    private List<Integer> per = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        if (nums == null || nums.length == 0)
            return permutations;

        permutations.add(new ArrayList<>());
        backtrack(nums, 0);
        return permutations;
    }

    /**
     * @param nums
     * @param i    对 nums 的索引
     */
    private void backtrack(int[] nums, int i) {
        if (per.size() == nums.length) return;

        for (; i < nums.length; i++) {
            per.add(nums[i]);
            permutations.add(new ArrayList<>(per));

            backtrack(nums, i + 1);

            per.remove(per.size() - 1);
        }

    }
}

class Solution1 {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < (1 << nums.length); i++) {  // 2 ^ (nums.length-1) 次
            for (int j = 0; j < nums.length; j++) {
                if (((i >> j) & 1) == 1) {
                    list.add(nums[j]);
                }
            }
            res.add(new ArrayList<Integer>(list));
            list.clear();
        }

        return res;
    }
}


