/*
 * https://leetcode-cn.com/problems/permutations/ 
 
给定一个没有重复数字的序列，返回其所有可能的全排列。

示例:
	输入: [1,2,3]
	输出:
	[
	  [1,2,3],
	  [1,3,2],
	  [2,1,3],
	  [2,3,1],
	  [3,1,2],
	  [3,2,1]
	]

---------------------------------------------------------------------------------------------------

Given a collection of distinct integers, return all possible permutations.

Example:
	Input: [1,2,3]
	Output:
	[
	  [1,2,3],
	  [1,3,2],
	  [2,1,3],
	  [2,3,1],
	  [3,1,2],
	  [3,2,1]
	]
*/

class MySolution {
    private List<List<Integer>> permutations = new ArrayList<>();
    private List<Integer> per = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0)
            return permutations;

        backtrack(nums, new boolean[nums.length]);
        return permutations;
    }

    /**
     * 
     * @param nums
     * @param used 排列中，标记 nums 中数值是否已取用
     */
    private void backtrack(int[] nums, boolean[] used) {
        if (per.size() == nums.length) {
            permutations.add(new ArrayList<>(per));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                per.add(nums[i]);
                used[i] = true;
                backtrack(nums, used);

                per.remove(per.size() - 1);
                used[i] = false;
            }
        }
    }
}

class Solution1 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }

        List<Integer> first = new LinkedList<>();
        first.add(nums[0]);
        result.add(first);

        for (int i = 2; i <= nums.length; i++) {
            int size = result.size();
            for (int j = 0; j < i - 1; j++) {
                for (int k = 0; k < size; k++) {
                    List<Integer> list = new LinkedList<>(result.get(k));
                    result.add(list);
                }
            }

            for (int j = 0; j < result.size(); j++) {
                result.get(j).add(j / size, nums[i - 1]);
            }
        }

        return result;
    }
}
