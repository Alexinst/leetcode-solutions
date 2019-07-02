/*
给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。

说明:
1. 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
2. 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。

示例:
	输入:
	nums1 = [1,2,3,0,0,0], m = 3
	nums2 = [2,5,6],       n = 3
	
	输出: [1,2,2,3,5,6]
*/

class MySolution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] merge = new int[m + n];
        for (int i = 0, j = 0, k = 0; k < m + n;) {
            if (i < m && j < n) {
                if (nums1[i] > nums2[j]) {
                    merge[k] = nums2[j];
                    j++;
                }
                else {
                    merge[k] = nums1[i];
                    i++;
                }  
            }
            else if (i >= m) 
                merge[k] = nums2[j++];
            else 
                merge[k] = nums1[i++];
            
            k++;
        }
        for (int i = 0; i < m + n; i++)
            nums1[i] = merge[i];
    }
}

class Solution1 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
     	int end = m + n - 1;
        int ml = m - 1, nl = n - 1;
        while (ml >= 0 && nl >= 0) {
            nums1[end--] = nums1[ml] > nums2[nl] ? nums1[ml--] : nums2[nl--];
        }
        
        while (nl >= 0) {
            nums1[end--] = nums2[nl--];
        }
    }
}
