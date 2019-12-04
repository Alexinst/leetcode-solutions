/*
 * https://leetcode-cn.com/problems/partition-equal-subset-sum/
 *
 * Reference: https://leetcode-cn.com/problems/partition-equal-subset-sum/solution/0-1-bei-bao-wen-ti-xiang-jie-zhen-dui-ben-ti-de-yo/
 
给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。

注意:
	1. 每个数组中的元素不会超过 100
	2. 数组的大小不会超过 200

示例 1:
	输入: [1, 5, 11, 5]
	输出: true
	解释: 数组可以分割成 [1, 5, 5] 和 [11].

示例 2:
	输入: [1, 2, 3, 5]
	输出: false
	解释: 数组不能分割成两个元素和相等的子集.

----------------------------------------------------------------------------------------------------

Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

Note:
	1. Each of the array element will not exceed 100.
	2. The array size will not exceed 200.

Example 1:
	Input: [1, 5, 11, 5]
	Output: true
	Explanation: The array can be partitioned as [1, 5, 5] and [11].
	 

Example 2:
	Input: [1, 2, 3, 5]
	Output: false
	Explanation: The array cannot be partitioned into equal sum subsets.
*/

class MySolution {
    public boolean canPartition(int[] nums) {
        if (nums.length == 1) return false;

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if (sum % 2 == 1) return false;
        
        int target = sum / 2;
        
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        dp[nums[0]] = true;

        for (int i = 1; i < nums.length; i++) {
            boolean[] next = new boolean[target + 1];

            for (int j = 0; j <= target; j++) {
                next[j] = dp[j];
                
                if (j - nums[i] >= 0)
                    next[j] |= dp[j - nums[i]]; 
            }

            dp = next;
        }

        return dp[target];
    }
}

class Solution1 {
    public boolean canPartition(int[] nums) {
        int size = nums.length;

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if ((sum & 1) == 1) return false;

        int target = sum / 2;

        boolean[] dp = new boolean[target + 1];

        // 状态转移方程：dp[i - 1][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]]
        // 单独 1 个数可以构成子集，根据状态转移方程，当 j == nums[i] 时，会来看 dp[i - 1][0] 的值
        // 因此根据语义，dp[0] 应该设置为 True
        dp[0] = true;

        for (int j = 1; j < target + 1; j++) {
            if (nums[0] == j) {
                dp[j] = true;
                break;
            }
        }

        for (int i = 1; i < size; i++) {
            // 先看最后一个数是不是返回 True，如果是，后面就没有必要计算了，方法可以直接返回 True
            if (target >= nums[i]) {
                dp[target] = dp[target] || dp[target - nums[i]];
            } else if (dp[target]) {
                return true;
            }

            // 然后再写倒数第 2 个数，倒数第 3 个数
            for (int j = target - 1; j >= nums[i]; j--) {
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }

        return dp[target];
    }
}

class Solution2 {
    public boolean canPartition(int[] nums) {
        if (nums.length < 2) return false;

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        //和为奇数，肯定不能分割为两个和相等的子集
        if (sum % 2 != 0) return false;

        //将数组做降序排序，是为了可以进行剪支，避免无用的查找
        Arrays.sort(nums);
//        reverse(nums);

        return canPartition(nums, sum / 2, 0);
    }

    //翻转数组
    private void reverse(int[] data) {
        for (int left = 0, right = data.length - 1; left < right; left++, right--) {
            // swap the values at the left and right indices
            int temp = data[left];
            data[left] = data[right];
            data[right] = temp;
        }
    }

    private boolean canPartition(int[] nums, int target, int index) {
        if (index >= nums.length || nums[index] > target) {
            return false;
        } else if (nums[index] == target) {
            return true;
        }

        //对于数组每个元素，都有选择和不选择两种可能。
        return canPartition(nums, target - nums[index], index + 1)
                || canPartition(nums, target, index + 1);
    }
}
