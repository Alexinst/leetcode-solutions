/*
 * https://leetcode-cn.com/problems/permutation-sequence/
 *
 * Reference: https://leetcode-cn.com/problems/permutation-sequence/solution/hui-su-jian-zhi-python-dai-ma-java-dai-ma-by-liwei/
 
给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。

按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
    "123"
    "132"
    "213"
    "231"
    "312"
    "321"

给定 n 和 k，返回第 k 个排列。

说明：
    1. 给定 n 的范围是 [1, 9]。
    2. 给定 k 的范围是[1,  n!]。

示例 1:
	输入: n = 3, k = 3
	输出: "213"

示例 2:
	输入: n = 4, k = 9
	输出: "2314"

----------------------------------------------------------------------------------------------------

The set [1,2,3,...,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order, we get the following sequence for n = 3:
    "123"
    "132"
    "213"
    "231"
    "312"
    "321"

Given n and k, return the kth permutation sequence.

Note:
    1. Given n will be between 1 and 9 inclusive.
    2. Given k will be between 1 and n! inclusive.

Example 1:
	Input: n = 3, k = 3
	Output: "213"

Example 2:
	Input: n = 4, k = 9
	Output: "2314"
*/


/**
 * 暴力解法
 */
class MySolution {
    public String getPermutation(int n, int k) {

        char[] chars = new char[n];
        for (char i = 0; i < n; i++)
            chars[i] = (char)(i + '1');

        // permute k-1 次
        while (k-- > 1) {
            permute(chars);
        }

        return new String(chars);
    }

    private void permute(char[] perm) {
        int i = perm.length - 2;
        while (i > 0 && perm[i] >= perm[i + 1]) i--;

        int j = perm.length - 1;
        while (j > i && perm[i] >= perm[j]) j--;

        swap(perm, i, j);
        reverse(perm, i + 1, perm.length - 1);
    }

    private void swap(char[] arr, int i, int j) {
        if (i == j) return;
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private void reverse(char[] arr, int i, int j) {
        while (i < j) {
            swap(arr, i, j);
            i++;
            j--;
        }
    }
}

class MySolution_Modified {
    private int[] factorial = null;

    public String getPermutation(int n, int k) {
        char[] perm = new char[n];
        for (char i = 0; i < n; i++)
            perm[i] = (char)(i + '1');

        if (k > 1) {
            int[] factorial = new int[n + 1];  // 阶乘表，factorial[i] = i的阶乘
            factorial[0] = 1;
            for (int i = 1; i < n + 1; i++) {
                factorial[i] = factorial[i - 1] * i;
            }
            this.factorial = factorial;

            dfs(perm, 0, n, k);
        }
        return new String(perm);
    }

    private void dfs(char[] perm, int i, int n, int k) {
        if (n == 1) {
            return;
        }

        int base = factorial[n - 1];  // 基数
        int j = 1;  // 倍数
        while (base * j < k) j++;

        swap(perm, i, i + j - 1);
        Arrays.sort(perm, i + 1, perm.length);
        dfs(perm, i + 1, n - 1, k - base * (j - 1));
    }

    private void swap(char[] arr, int i, int j) {
        if (i == j) return;

        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}


class Solution1 {
    private boolean[] flag = null;
    private char[] res = null;

    public String getPermutation(int n, int k) {
        flag = new boolean[n + 1];
        res = new char[n];
        helper(n, n, k);
        return new String(res);
    }

    private int jie(int n) {
        if (n < 0) return -1;
        int cnt = 1;
        while (n > 0) {
            cnt *= n--;
        }
        return cnt;
    }

    // count:未使用数字的个数
    private boolean helper(int n, int count, int k) {
        if (count == 0) {
            return true;
        }
        for (int i = 1; i <= n; i++) {
            if (!flag[i]) {
                // 计算分支i的序列个数
                int cnt = jie(count - 1);
                if (k > cnt) {
                    k -= cnt;
                    continue;
                }
                // k <= cnt 进入当前分支i
                flag[i] = true;
                res[n - count] = (char) (i + '0');
                if (helper(n, count - 1, k))
                    return true;
            }
        }

        return false;
    }
}
