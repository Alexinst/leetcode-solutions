/*
给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。

注意：答案中不可以包含重复的三元组。

例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，

满足要求的三元组集合为：
	[
  		[-1, 0, 1],
  		[-1, -1, 2]
	]
*/

class Solution1 {
    public List<List<Integer>> threeSum(int[] nums) {
 
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length == 0) {
            return result;
        }

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) {
                break;
            }
            if(i > 0 && nums[i] == nums[i-1]) {
                continue;
            }

            int m = i + 1, n = nums.length - 1;
            while (m < n) {
                if (n < nums.length-1 && nums[n] == nums[n+1] 
                    || nums[i] + nums[m] + nums[n] > 0) {
                    n--;
                } 
                else if (m > i+1 && nums[m] == nums[m-1]
                         || nums[i] + nums[m] + nums[n] < 0) {
                    m++;
                } 
                else {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[m++]);
                    list.add(nums[n--]);
                    result.add(list);
                }
            }
        }
        return result;
    }
}
