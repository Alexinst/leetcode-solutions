/*
给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。

此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。

注意:
	不能使用代码库中的排序函数来解决这道题。

示例:
	输入: [2,0,2,1,1,0]
	输出: [0,0,1,1,2,2]

进阶：
1. 一个直观的解决方案是使用计数排序的两趟扫描算法。
   首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
2. 你能想出一个仅使用常数空间的一趟扫描算法吗？

-----------------------------------------------------------------------------------------------------------------------

Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note: You are not suppose to use the library's sort function for this problem.

Example:
	Input: [2,0,2,1,1,0]
	Output: [0,0,1,1,2,2]

Follow up:
1. A rather straight forward solution is a two-pass algorithm using counting sort.
   First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
2. Could you come up with a one-pass algorithm using only constant space?
*/

class MySolution {
	// 不符合要求

    public void sortColors(int[] nums) {
        int[] count = new int[3];
        for (int num : nums) {
            count[num]++;
        }
        
        int i = 0;
        for (int num = 0; num < 3; num++) {
            while (count[num]-- > 0) {
                nums[i] = num;
                i++;
            }
        }
    }
}

class Solution1 {
    public void sortColors(int[] nums) {
        int p0 = 0, cur = 0;
        int p2 = nums.length - 1;

        int tmp;
        while (cur <= p2) {
            if (nums[cur] == 0) {
                tmp = nums[p0];
                nums[p0++] = nums[cur];
                nums[cur++] = tmp;
            }
            else if (nums[cur] == 2) {
                tmp = nums[cur];
                nums[cur] = nums[p2];
                nums[p2--] = tmp;
            }
            else cur++;
        }
    }
}
