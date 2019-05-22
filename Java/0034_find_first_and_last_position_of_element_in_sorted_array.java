/*
给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。

你的算法时间复杂度必须是 O(log n) 级别。

如果数组中不存在目标值，返回 [-1, -1]。

示例 1:
	输入: nums = [5,7,7,8,8,10], target = 8
	输出: [3,4]

示例 2:
	输入: nums = [5,7,7,8,8,10], target = 6
	输出: [-1,-1]
*/

class MySolution {
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return new int[] {-1, -1};

        int left = 0, right = nums.length - 1;
        int[] res = {-1, -1};
        while (left <= right)
        {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target)
            {
                int first = mid, last = mid;
                while (first > 0 && nums[first-1] == target)
                    first--;
                while (last < nums.length-1 && nums[last+1] == target)
                    last++;
                res[0] = first;
                res[1] = last;
                break;
            }
            else if (nums[mid] < target)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return res;
    }
}

class Solution1 {
    public int[] searchRange(int[] nums, int target) {
        int result[] = new int[2];
        int start = -1;
        int end = -1;
        boolean bool = true;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target){
                if (bool){
                    start = i;
                    bool = false;
                }
                end = i;
            }
        }
        result[0] = start;
        result[1] = end;
        return result;
    }
}
