/*
给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

示例:
	输入: [-2,1,-3,4,-1,2,1,-5,4],
	输出: 6
	解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。

进阶:
	如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
*/

class MySolution {
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum > max)
                    max = sum;
            }    
            sum = 0;
        }
        
        return max;
    }
}

class Solution1 {
    public int maxSubArray(int[] nums) {
        int max = nums[0];
		int sum = nums[0];

		for (int i = 1; i < nums.length; i ++) {
			sum = Math.max(nums[i], sum + nums[i]);
			max = Math.max(max, sum);
		}

		return max;
    }
}

class Solution2 {
    public int maxSubArray(int[] nums) {
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if(sum + nums[i] < nums[i]){
                sum = nums[i];
            }else{
                sum += nums[i];
            }
            if(sum > max){
                max = sum;
            }
        }
        return max;
    }
}
