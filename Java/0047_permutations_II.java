/*
 * https://leetcode-cn.com/problems/permutations-ii/submissions/ 

给定一个可包含重复数字的序列，返回所有不重复的全排列。

示例:
	输入: [1,1,2]
	输出:
	[
	  [1,1,2],
	  [1,2,1],
	  [2,1,1]
	]

---------------------------------------------------------------------------------------------------

Given a collection of numbers that might contain duplicates, return all possible unique permutations.

Example:
	Input: [1,1,2]
	Output:
	[
	  [1,1,2],
	  [1,2,1],
	  [2,1,1]
	]
*/

/**
 * 暴力法
 */
class MySolution {
   private Set<List<Integer>> permutations = new HashSet<>();
   private List<Integer> per = new ArrayList<>();

   public List<List<Integer>> permuteUnique(int[] nums) {
	   if (nums == null || nums.length == 0)
		   return new ArrayList<>();

	   backtrack(nums, new boolean[nums.length]);
	   return new ArrayList<>(permutations);
   }

   private void backtrack(int[] nums, boolean[] used){
	   if (per.size() == nums.length) {
		   permutations.add(new ArrayList<>(per));
		   return ;
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
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        doPermute(result, new ArrayList<Integer>(), nums, 0);

        return result;
    }

    private void doPermute(List<List<Integer>> result, List<Integer> tempList, int[] nums, int begin) {
        if (begin == nums.length) {
            result.add(new ArrayList<Integer>(tempList));
            return;
        }

        for (int i = begin; i < nums.length; i++) {
            if (canSwap(nums, begin, i)) {
                tempList.add(nums[i]);
                swap(nums, begin, i);
                doPermute(result, tempList, nums, begin + 1);
                swap(nums, begin, i);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    // 如果在begin到cur之间有与cur相等的值，说明该值在begin已经存在过，再swap就重复了
    // 保证begin位置的值不重复
    private boolean canSwap(int[] nums, int begin, int cur) {
        for (int i = begin; i < cur; i++) {
            if (nums[i] == nums[cur]) return false;
        }
        return true;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
