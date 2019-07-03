/*
给定一个整数数组 nums ，找出一个序列中乘积最大的连续子序列（该序列至少包含一个数）。

示例 1:
	输入: [2,3,-2,4]
	输出: 6
	解释: 子数组 [2,3] 有最大乘积 6。

示例 2:
	输入: [-2,0,-1]
	输出: 0
	解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
*/

class MySolution {
    public int maxProduct(int[] nums) {
        int max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            int product = 1;
            for (int j = i; j < nums.length; j++) {
                product *= nums[j];
                max = Math.max(max, product);
            }
        }
        return max;
    }
}

class Solution {
    public int maxProduct(int[] nums) {
        
        //dp方法 维护两个数组
        // if(nums.length == 0) return 0;
        // int[] dpMax = new int[nums.length];
        // int[] dpMin = new int[nums.length];
        // dpMax[0]=dpMax[0]=nums[0];
        // int res = nums[0];
        // for(int i=1;i<nums.length;i++){
        //     dpMax[i] = Math.max(Math.max(nums[i],nums[i]*dpMax[i-1]),nums[i]*dpMin[i-1]);
        //     dpMin[i] = Math.min(Math.min(nums[i],nums[i]*dpMax[i-1]),nums[i]*dpMin[i-1]);
        //     res = Math.max(res,dpMax[i]);
        // }
        // return res;
        
        //方式二
        // if(nums.length == 0) return 0;
        // int max = nums[0];
        // int min = nums[0];
        // int res = nums[0];
        // for(int i=1;i<nums.length;i++){
        //     int tempMax = max;
        //     int tempMin = min;
        //     max = Math.max(Math.max(nums[i],nums[i]*tempMax),nums[i]*tempMin);
        //     min = Math.min(Math.min(nums[i],nums[i]*tempMax),nums[i]*tempMin);
        //     res = Math.max(res,max);
        // }
        // return res;
        
        //方式三
        if(nums.length == 0) return 0;
        int max = nums[0];
        int min = nums[0];
        int res = nums[0];
        for(int i=1;i<nums.length;i++){
            max *= nums[i];
            min *= nums[i];
            if(max<min){
                int temp = max;
                max = min;
                min = temp;
            }
            if(max<nums[i]) max = nums[i];
            if(min>nums[i]) min = nums[i];
            res = Math.max(res,max);
        }
        return res;
        
    }
}
