/*
 * https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array/
 
给定一个范围在  1 <= a[i] <= n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。

找到所有在 [1, n] 范围之间没有出现在数组中的数字。

您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。

示例:
	输入: [4,3,2,7,8,2,3,1]
	输出: [5,6]

----------------------------------------------------------------------------------------------------

Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

Find all the elements of [1, n] inclusive that do not appear in this array.

Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.

Example:
	Input: [4,3,2,7,8,2,3,1]
	Output: [5,6]
*/

class MySolution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> ret = new ArrayList<>();
        if (nums.length == 1) return ret;

        for (int i = 0; i < nums.length; ) {
            if (nums[i] == i + 1 || nums[i] == nums[nums[i] - 1]){
                i++;
                continue;
            }

            int tmp = nums[i];
            nums[i] = nums[tmp - 1];
            nums[tmp - 1] = tmp;
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1)
                ret.add(i + 1);
        }

        return ret;
    }
}

class Solution1 {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int l = nums.length;
        List<Integer> ret = new ArrayList<>();

        for (int i = 0; i < l; i++) {
            int idx = (nums[i] - 1) % l;
            nums[idx] += l;
        }

        for (int i = 0; i < l; i++) {
            if (nums[i] <= l) {
                ret.add(i + 1);
            }
        }
        return ret;
    }
}
