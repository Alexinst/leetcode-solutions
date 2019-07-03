/*
给定一个无序的整数数组，找到其中最长上升子序列的长度。

示例:
	输入: [10,9,2,5,3,7,101,18]
	输出: 4 
	解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。

说明:
1. 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
2. 你算法的时间复杂度应该为 O(n2) 。

进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
*/

class MySolution {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int maxLen = 0;
        
        for (int i = 0; i < nums.length; i++) {
            int len = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i])
                    len = Math.max(len, dp[j] + 1);
            }
            dp[i] = len;
            maxLen = Math.max(maxLen, dp[i]);
        }
       
        return maxLen;
    }
}

class Solution {
    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        if(len <= 1){
            return len;
        }
        int maxL = 0;
        //dp[i]: 所有长度为i+1的递增子序列中, 最小的那个序列尾数
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < len; i++) {
            if(nums[i] > dp[maxL]) {
                dp[++maxL]= nums[i];
            }
	    else {
                int left = 0, right = maxL;
                while(left < right){
                    int mid = left + (right-left)/2;
                    if(dp[mid] == nums[i]){
                        left = mid;
                        break;
                    } else if (dp[mid] < nums[i]) {
                        left = left + 1;
                    } else {
                        right =  mid;
                    }
                }
                dp[left] = nums[i];
            }
        }
        return ++maxL;
    }
}
