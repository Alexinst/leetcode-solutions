/*
给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点。

示例：
	给定 nums = [-2, 0, 3, -5, 2, -1]，求和函数为 sumRange()

	sumRange(0, 2) -> 1
	sumRange(2, 5) -> -1
	sumRange(2, 5) -> -3

说明:
1. 你可以假设数组不可变。
2. 会多次调用 sumRange 方法。
*/

class MyNumArray {
    private int[] sums;
    
    public NumArray(int[] nums) {
        sums = new int[nums.length];
        int s = 0;
        for (int i = 0; i < nums.length; i++) {
            s += nums[i];
            sums[i] = s;
        }
    }
    
    public int sumRange(int i, int j) {
        if (i == 0)
            return sums[j];
        else
            return sums[j] - sums[i - 1];
    }
}

