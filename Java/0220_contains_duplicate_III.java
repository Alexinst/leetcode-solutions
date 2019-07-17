/*

https://leetcode-cn.com/problems/contains-duplicate-iii/solution/cun-zai-zhong-fu-yuan-su-iii-by-leetcode/

给定一个整数数组，判断数组中是否有两个不同的索引 i 和 j，使得 nums [i] 和 nums [j] 的差的绝对值最大为 t，并且 i 和 j 之间的差的绝对值最大为 ķ。

示例 1:
	输入: nums = [1,2,3,1], k = 3, t = 0
	输出: true

示例 2:
	输入: nums = [1,0,1,1], k = 1, t = 2
	输出: true

示例 3:
	输入: nums = [1,5,9,1,5,9], k = 2, t = 3
	输出: false

------------------------------------------------------------------------------------------------------------------------------------

Given an array of integers, find out whether there are two distinct indices i and j in the array such that the absolute difference between nums[i] and nums[j] is at most t and the absolute difference between i and j is at most k.

Example 1:
	Input: nums = [1,2,3,1], k = 3, t = 0
	Output: true

Example 2:
	Input: nums = [1,0,1,1], k = 1, t = 2
	Output: true

Example 3:
	Input: nums = [1,5,9,1,5,9], k = 2, t = 3
	Output: false

*/

class Solution1 {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (t < 0 || k <= 0 ||
            nums.length == 0 || nums.length == 1)
            return false;

        Map<Long, Long> map = new HashMap<>();
        long bucketCount = (long) t + 1;
        for (int i = 0; i < nums.length; i++) {
            long id = getID((long) nums[i], bucketCount);

            if (map.containsKey(id)) return true;
            if (map.containsKey(id - 1) && 
                abs(map.get(id-1), nums[i]) < bucketCount)
                return true;
            if (map.containsKey(id+1) && 
                abs(map.get(id+1), nums[i]) < bucketCount)
                return true;

            map.put(id, (long)nums[i]);
            if (i >= k)
                map.remove(getID(nums[i - k], bucketCount));
        }

        return false;
    }

    private long getID(long n, long count){
        return n < 0 ? (n + 1) / count - 1 : n / count;
    }

    private long abs(long x1, int x2) {
        return x1 < (long) x2 ? x2 - x1 : x1 - x2;
    }
}


class Solution2 {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (k <= 0 || t < 0 ||
            nums.length == 0 || nums.length == 1)
            return false;

        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            Integer ceiling = set.ceiling(nums[i]);
            if (ceiling != null && ceiling - nums[i] <= t)
                return true;

            Integer floor = set.floor(nums[i]);
            if (floor != null && nums[i] - floor <= t)
                return true;

            set.add(nums[i]);
            if (set.size() > k)
                set.remove(nums[i - k]);
        }

        return false;
    }
}
