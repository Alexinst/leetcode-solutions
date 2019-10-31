/*
 * https://leetcode-cn.com/problems/advantage-shuffle/ 
 *
 * Reference: https://leetcode-cn.com/problems/advantage-shuffle/solution/you-shi-xi-pai-by-leetcode/
 
给定两个大小相等的数组 A 和 B，A 相对于 B 的优势可以用满足 A[i] > B[i] 的索引 i 的数目来描述。

返回 A 的任意排列，使其相对于 B 的优势最大化。

示例 1：
	输入：A = [2,7,11,15], B = [1,10,4,11]
	输出：[2,11,7,15]
	
示例 2：
	输入：A = [12,24,8,32], B = [13,25,32,11]
	输出：[24,32,8,12]
	 

提示：
	1. 1 <= A.length = B.length <= 10000
	2. 0 <= A[i] <= 10^9
	3. 0 <= B[i] <= 10^9

----------------------------------------------------------------------------------------------------

Given two arrays A and B of equal size, the advantage of A with respect to B is the number of indices i for which A[i] > B[i].

Return any permutation of A that maximizes its advantage with respect to B.

Example 1:
	Input: A = [2,7,11,15], B = [1,10,4,11]
	Output: [2,11,7,15]
	
Example 2:
	Input: A = [12,24,8,32], B = [13,25,32,11]
	Output: [24,32,8,12]
	 
Note:
	1. 1 <= A.length = B.length <= 10000
	2. 0 <= A[i] <= 10^9
	3. 0 <= B[i] <= 10^9
*/

class MySolution {
    public int[] advantageCount(int[] A, int[] B) {
        int len = A.length;
        if (len == 1) return A;

        int[][] BMap = new int[len][2];
        for (int i = 0; i < len; ++i) {
            BMap[i][0] = B[i];
            BMap[i][1] = i;
        }

        Arrays.sort(A);
        Arrays.sort(BMap, new Comparator<int[]>() {
            @Override
            public int compare(int[] arr1, int[] arr2) {
                return arr1[0] - arr2[0];
            }
        });

        int[] ans = new int[len];
        boolean[] isUsed = new boolean[len];

        for (int i = 0, j = 0; j < len; ++j) {
            while (i < len - 1 && A[i] <= BMap[j][0]) ++i;
            while (isUsed[i]) i = (i + 1) % len;

            ans[BMap[j][1]] = A[i];
            isUsed[i] = true;
        }

        return ans;
    }
}

class Solution1 {
    class Temp implements Comparable<algorithms.Test.Temp> {
        int num, index;         //存储B数组的值和下标

        public Temp(int num, int index) {
            this.index = index;
            this.num = num;
        }

        @Override
        public int compareTo(algorithms.Test.Temp o) {
            return this.num - o.num;
        }
    }
    
    public int[] advantageCount(int[] A, int[] B) {
        //从小到大排序，若R1<R2 则比较找到一个A中靠左小于B值，与R2匹配
        Temp[] arr = new Temp[B.length];

        for (int i = 0; i < B.length; ++i) {     //复制B的值
            arr[i] = new Temp(B[i], i);
        }
        Arrays.sort(A); //从小到大排序
        Arrays.sort(arr);
        int L1 = 0;
        int R1 = A.length - 1;
        int L2 = 0;
        int R2 = B.length - 1;

        while (L1 <= R1) {
            if (A[R1] > arr[R2].num) {
                B[arr[R2].index] = A[R1];
                R1--;
                R2--;
            } else if (A[R1] < arr[R2].num) {     //若R1 <= R2,就需要在A中找一个无用值与B最大的值匹配，田忌赛马
                B[arr[R2].index] = A[L1];
                R2--;
                L1++;
            } else {
                if (A[L1] > arr[L2].num) {
                    B[arr[L2].index] = A[L1];
                    L1++;
                    L2++;
                } else {
                    B[arr[R2].index] = A[L1];
                    R2--;
                    L1++;
                }
            }
        }
        return B;
    }
}

class Solution2 {
    public int[] advantageCount(int[] A, int[] B) {
        int n = A.length;
        int[] res = new int[n];
        int[] idx = new int[n];
        Arrays.sort(A);
        for (int i = 0; i < n; i++) {
            idx[i] = i;
        }
        quickSort(B, idx, 0, n - 1);
        int maxIdx = n - 1;
        int index = n - 1;
        int minIdx = 0;
        while (index >= 0) {
            if (B[index] < A[maxIdx]) {
                res[idx[index]] = A[maxIdx];
                maxIdx--;
                index--;
            } else {
                res[idx[index]] = A[minIdx];
                minIdx++;
                index--;
            }
        }
        return res;
    }

    private void quickSort(int[] B, int[] idx, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int j = partition(B, idx, lo, hi);
        quickSort(B, idx, lo, j - 1);
        quickSort(B, idx, j + 1, hi);
    }

    private int partition(int[] B, int[] idx, int lo, int hi) {
        int l = lo;
        int r = hi + 1;
        int com = B[lo];
        while (true) {
            while (B[++l] < com) {
                if (l == hi) {
                    break;
                }
            }

            while (B[--r] > com) {
                if (r == lo) {
                    break;
                }
            }
            if (l >= r) {
                break;
            }
            swap(B, l, r);
            swap(idx, l, r);
        }
        swap(B, lo, r);
        swap(idx, lo, r);
        return r;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
