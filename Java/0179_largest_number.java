/*
给定一组非负整数，重新排列它们的顺序使之组成一个最大的整数。

示例 1:
	输入: [10,2]
	输出: 210

示例 2:
	输入: [3,30,34,5,9]
	输出: 9534330
	
说明: 输出结果可能非常大，所以你需要返回一个字符串而不是整数。

--------------------------------------------------------------------------------

Given a list of non negative integers, arrange them such that they form the largest number.

Example 1:
	Input: [10,2]
	Output: "210"

Example 2:
	Input: [3,30,34,5,9]
	Output: "9534330"

Note: The result may be very large, so you need to return a string instead of an integer.

https://leetcode-cn.com/problems/largest-number/solution/zui-da-shu-by-leetcode/
*/

class Solution1 {
	private class LargestNumberComparator implements Comparator<String> {
		@Override
		public int compare(String s1, String s2) {
			String com1 = s1 + s2;
			String com2 = s2 + s1;
			
			return com2.compareTo(com1);
		}
	}

    public String largestNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++)
            strs[i] = Integer.toString(nums[i]);
        Arrays.sort(strs, new LargestNumberComparator());
        
        if (strs[0].equals("0")) return "0";
        
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < strs.length; i++)
            res.append(strs[i]);
        
        return res.toString();
    }
}



class Solution {
	public String largestNumber(int[] nums) {
        if(checkzero(nums)) return "0";
        sort(nums, 0, nums.length - 1);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < nums.length; i++){
            sb.append(nums[i]);
        }
        return sb.toString();
    }

    private boolean checkzero(int[] nums) {
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != 0){
                return false;
            }
        }
        return true;
    }

    private void sort(int[] nums, int left, int right) {
        if(left < right){
            int base = nums[left];
            int start = left;
            int end = right + 1;
            while(start < end){
                while(--end > start && compare(nums[end], base)){
                }
                while(end > ++start && compare(base, nums[start])){
                }
                if(end > start){
                    int temp = nums[start];
                    nums[start] = nums[end];
                    nums[end] = temp;
                }
            }
            nums[left] = nums[end];
            nums[end] = base;
            sort(nums, left, end - 1);
            sort(nums, end + 1, right);
        }
    }

    //自定义判断num是否小于base
    private boolean compare(int num, int base) {
        return makeNum(num, base) <  makeNum(base, num);
    }

    //如：num-34 base-5,构成345这样的数字
    private long makeNum(int num, int base) {
        if(base == 0){
            return num * 10;
        }
        long i = base, length = 1;
        while(i != 0){
            length *= 10;
            i /= 10;
        }
        return num * length + base;
    }
}
