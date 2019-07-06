/*
给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。
你可以返回任何满足上述条件的数组作为答案。

示例：
	输入：[4,2,5,7]
	输出：[4,5,2,7]
	解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。

提示：
1. 2 <= A.length <= 20000
2. A.length % 2 == 0
3. 0 <= A[i] <= 1000
*/

class MySolution {
    public int[] sortArrayByParityII(int[] A) {
        int len = A.length;
        if (len == 0) return A;
        
        // i: 偶数索引；j: 奇数索引
        for (int i = 0, j = 1; j < len;) {
            if (!isEven(A[i]) && isEven(A[j])) {
                int temp = A[i];
                A[i] = A[j];
                A[j] = temp;
                i += 2;
                j += 2;
            }
            else if (isEven(A[i]))
                i += 2;
            else if (!isEven(A[j]))
                j += 2;
        }
        
        return A;
    }
    
    private boolean isEven(int n) {
        return n % 2 == 0 ? true : false;
    }
}

class Solution1 {
    public int[] sortArrayByParityII(int[] A) {
        int size = A.length;
        int[] res = new int[size];
        int odd = 1, even = 0;
        for (int i=0; i<size; ++i) {
            if ((A[i] & 1) == 1) {
                res[odd] = A[i];
                odd += 2;
            } else {
                res[even] = A[i];
                even += 2;
            }
        }
        return res;
    }
}
