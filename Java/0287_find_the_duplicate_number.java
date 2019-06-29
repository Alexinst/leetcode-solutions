/*
给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。

示例 1:
	输入: [1,3,4,2,2]
	输出: 2

示例 2:
	输入: [3,1,3,4,2]
	输出: 3

说明：
1. 不能更改原数组（假设数组是只读的）。
2. 只能使用额外的 O(1) 的空间。
3. 时间复杂度小于 O(n2) 。
4. 数组中只有一个重复的数字，但它可能不止重复出现一次。
*/

class MySolution {
    public int findDuplicate(int[] nums)
    {
        int[] n = new int[nums.length];
        int res = -1;
        for (int num : nums)
        {
            if (n[num] > 0)
            {
                res = num;
                break;
            }
            else
                n[num] += 1;
        }
        return res;
    }
}

class Solution1 {
    public int findDuplicate(int[] nums) {
        int slow = 0;
        int fast = 0;

        do {
            slow = nums[slow];
            fast = nums[fast];
            fast = nums[fast];
        } while (slow != fast);

        fast = 0;

        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return fast;
    }
}
