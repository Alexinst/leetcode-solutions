/*
 * https://leetcode-cn.com/problems/majority-element/
 *
 * Reference: https://leetcode-cn.com/problems/majority-element/solution/qiu-zhong-shu-by-leetcode-2/
 
给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。

你可以假设数组是非空的，并且给定的数组总是存在多数元素。

示例 1:
	输入: [3,2,3]
	输出: 3
	
示例 2:
	输入: [2,2,1,1,1,2,2]
	输出: 2

----------------------------------------------------------------------------------------------------

Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.

Example 1:
	Input: [3,2,3]
	Output: 3
	
Example 2:
	Input: [2,2,1,1,1,2,2]
	Output: 2
*/

class MySolution {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int ret = 0;
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);

            if (map.get(n) * 2 > nums.length)
                ret = n;
        }

        return ret;
    }
}

class Solution1 {
    public int majorityElement(int[] nums) {
        int count = 0;
        Integer candidate = null;

        for (int n : nums) {
            if (count == 0)
                candidate = n;

            count += (n == candidate) ? 1 : -1;
        }

        return candidate != null ? candidate : -1;
    }
}

class Solution2 {
    public int majorityElement(int[] nums) {
        int value = nums[0];
        int i = 1, j = 1;
        while (i < nums.length) {
            if (nums[i] == value) {
                i++;
                j++;
            } else {
                i++;
                j--;
            }

            if (j == 0) {
                j = 1;
                value = nums[i];
                i++;
            }
        }

        return value;
    }
}
