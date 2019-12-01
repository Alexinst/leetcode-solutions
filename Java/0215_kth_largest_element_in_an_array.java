/*
 * https://leetcode-cn.com/problems/kth-largest-element-in-an-array/ 
 * 
 * Reference: https://leetcode-cn.com/problems/kth-largest-element-in-an-array/solution/shu-zu-zhong-de-di-kge-zui-da-yuan-su-by-leetcode/
 
在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

示例 1:
	输入: [3,2,1,5,6,4] 和 k = 2
	输出: 5
	
示例 2:
	输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
	输出: 4
	
说明: 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。

----------------------------------------------------------------------------------------------------

Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

Example 1:
	Input: [3,2,1,5,6,4] and k = 2
	Output: 5
	
Example 2:
	Input: [3,2,3,1,2,4,5,5,6] and k = 4
	Output: 4
	
Note: You may assume k is always valid, 1 ≤ k ≤ array's length.
*/

class Solution1 {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for (int num : nums) {
            pq.add(num);
            
            if (pq.size() > k)
                pq.poll();
        }
        
        return pq.poll();
    }
}

class Solution2 {
    // partition 的优化同样可以用在这一题上

    private static Random random = new Random(System.currentTimeMillis());

    public int findKthLargest(int[] nums, int k) {
        int len = nums.length;
        int target = len - k;
        int left = 0;
        int right = len - 1;

        while (true) {
            int index = partition(nums, left, right);
            if (index < target) {
                left = index + 1;
            } else if (index > target) {
                right = index - 1;
            } else {
                return nums[index];
            }
        }
    }

    // 在区间 [left, right] 这个区间执行 partition 操作
    private int partition(int[] nums, int left, int right) {
        // 在区间随机选择一个元素作为标定点
        if (right > left) {
            int randomIndex = left + 1 + random.nextInt(right - left);
            swap(nums, left, randomIndex);
        }

        int pivot = nums[left];
        int j = left;
        for (int i = left + 1; i <= right; i++) {
            if (nums[i] < pivot) {
                j++;
                swap(nums, j, i);
            }
        }
        swap(nums, left, j);
        return j;
    }

    private void swap(int[] nums, int index1, int index2) {
        if (index1 == index2) {
            return;
        }
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}
