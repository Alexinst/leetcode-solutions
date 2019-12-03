/*
 * https://leetcode-cn.com/problems/product-of-array-except-self/
 *
 * Reference: https://leetcode-cn.com/problems/product-of-array-except-self/solution/bao-cun-zuo-ji-he-you-ji-python3-by-zhu_shi_fu/
 
给定长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。

示例:
	输入: [1,2,3,4]
	输出: [24,12,8,6]
	说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。

进阶：你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）

----------------------------------------------------------------------------------------------------

Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Example:
	Input:  [1,2,3,4]
	Output: [24,12,8,6]
	Note: Please solve it without division and in O(n).

Follow up: Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)
*/

class MySolution {
    public int[] productExceptSelf(int[] nums) {
        int[] output = new int[nums.length];
        int product = 1;
        
        for (int i = 0; i < nums.length; i++) {
            output[i] = product;
            product *= nums[i];
        }
        
        product = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            output[i] = product * output[i];
            product *= nums[i];
        }
        
        return output;
    }
}


