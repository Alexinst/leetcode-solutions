/*
给定一个排序好的数组，两个整数 k 和 x，从数组中找到最靠近 x（两数之差最小）的 k 个数。返回的结果必须要是按升序排好的。如果有两个数与 x 的差值一样，优先选择数值较小的那个数。

示例 1:
	输入: [1,2,3,4,5], k=4, x=3
	输出: [1,2,3,4]

示例 2:
	输入: [1,2,3,4,5], k=4, x=-1
	输出: [1,2,3,4]

说明:
1. k 的值为正数，且总是小于给定排序数组的长度。
2. 数组不为空，且长度不超过 10^4
3. 数组里的每个元素与 x 的绝对值不超过 10^4
*/

class MySolution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        // 找到x所在区间
        int len = arr.length;
        int left = 0, right = len - 1, mid = 0;
        while (left < right) {
            mid = left + (right - left) / 2;
            if (arr[mid] == x)
                break;
            else if (arr[mid] < x)
                left = mid + 1;
            else
                right = mid;
        }
    
        // 找到与 x 最接近的数的索引
        mid = left + (right - left) / 2;
        if (mid + 1 < len && arr[mid] < x)
            mid = x-arr[mid] <= arr[mid+1]-x ? mid : mid+1;
        else if (mid - 1 >= 0 && arr[mid] > x)
            mid = x-arr[mid-1] <= arr[mid]-x ? mid-1 : mid;
        left = mid; 
        right = mid;
        
        // 确定最接近 x 的 K 个数的起止索引
        while (k > 1 && left >= 1 && right <= len - 2) {
            if (x - arr[left-1] <= arr[right+1] - x)
                left--;
            else
                right++;

            k--;
        }
        if (left == 0) right += k - 1;
        if (right == len - 1) left -= k - 1;
        
        // 新建 List 储存返回值
        List<Integer> res = new ArrayList<>();
        for (int i = left; i <= right; i++)
            res.add(arr[i]);

        return res;
    }
}

class Solution1 {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> res = new ArrayList<>();
        int start = 0, end = arr.length - k;
        while (start < end) {
            int mid = start + (end - start) / 2;
            
            //中位数距离x比较远  所以看右半部分
            if (Math.abs(arr[mid]-x) > Math.abs(arr[mid+k]-x)) 
                start = mid + 1;
            else
                end = mid;
        }

        for (int i = start; i < start + k; i++)
            res.add(arr[i]);
        
        return res;
    }
}
