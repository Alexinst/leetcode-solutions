/*
 * https://leetcode-cn.com/problems/delete-columns-to-make-sorted-ii/ 
 *
 * Reference: https://leetcode-cn.com/problems/delete-columns-to-make-sorted-ii/solution/shan-lie-zao-xu-ii-by-leetcode/
 
给定由 N 个小写字母字符串组成的数组 A，其中每个字符串长度相等。

选取一个删除索引序列，对于 A 中的每个字符串，删除对应每个索引处的字符。

比如，有 A = ["abcdef", "uvwxyz"]，删除索引序列 {0, 2, 3}，删除后 A 为["bef", "vyz"]。

假设，我们选择了一组删除索引 D，那么在执行删除操作之后，最终得到的数组的元素是按 字典序（A[0] <= A[1] <= A[2] ... <= A[A.length - 1]）排列的，然后请你返回 D.length 的最小可能值。

示例 1：
	输入：["ca","bb","ac"]
	输出：1
	解释： 
	删除第一列后，A = ["a", "b", "c"]。
	现在 A 中元素是按字典排列的 (即，A[0] <= A[1] <= A[2])。
	我们至少需要进行 1 次删除，因为最初 A 不是按字典序排列的，所以答案是 1。
	
示例 2：
	输入：["xc","yb","za"]
	输出：0
	解释：
	A 的列已经是按字典序排列了，所以我们不需要删除任何东西。
	注意 A 的行不需要按字典序排列。
	也就是说，A[0][0] <= A[0][1] <= ... 不一定成立。

示例 3：
	输入：["zyx","wvu","tsr"]
	输出：3
	解释：
	我们必须删掉每一列。
	 
提示：
	1. 1 <= A.length <= 100
	2. 1 <= A[i].length <= 100

----------------------------------------------------------------------------------------------------

We are given an array A of N lowercase letter strings, all of the same length.

Now, we may choose any set of deletion indices, and for each string, we delete all the characters in those indices.

For example, if we have an array A = ["abcdef","uvwxyz"] and deletion indices {0, 2, 3}, then the final array after deletions is ["bef","vyz"].

Suppose we chose a set of deletion indices D such that after deletions, the final array has its elements in lexicographic order (A[0] <= A[1] <= A[2] ... <= A[A.length - 1]).

Return the minimum possible value of D.length.

Example 1:
	Input: ["ca","bb","ac"]
	Output: 1
	Explanation: 
	After deleting the first column, A = ["a", "b", "c"].
	Now A is in lexicographic order (ie. A[0] <= A[1] <= A[2]).
	We require at least 1 deletion since initially A was not in lexicographic order, so the answer is 1.
	
Example 2:
	Input: ["xc","yb","za"]
	Output: 0
	Explanation: 
	A is already in lexicographic order, so we don't need to delete anything.
	Note that the rows of A are not necessarily in lexicographic order:
	ie. it is NOT necessarily true that (A[0][0] <= A[0][1] <= ...)

Example 3:
	Input: ["zyx","wvu","tsr"]
	Output: 3
	Explanation: 
	We have to delete every column.

Note:
	1. 1 <= A.length <= 100
	2. 1 <= A[i].length <= 100
*/

class Solution1 {
    public int minDeletionSize(String[] A) {
        int row = A.length, col = A[0].length();
        int ans = 0;
        String[] strings1 = new String[row];

        for (int i = 0; i < col; i++) {
            String[] strings2 = Arrays.copyOf(strings1, row);

            for (int j = 0; j < row; j++) {
                strings2[j] += A[j].charAt(i);
            }

            if (isSorted(strings2))
                strings1 = strings2;
            else
                ans++;
        }

        return ans;
    }

    private boolean isSorted(String[] strings) {
        for (int i = 0; i < strings.length - 1; i++) {
            if (strings[i].compareTo(strings[i + 1]) > 0)
                return false;
        }
        return true;
    }
}

class Solution2 {
    public int minDeletionSize(String[] A) {
        int row = A.length;
        int col = A[0].length();
        // cuts[j] is true : we don't need to check any new A[i][j] <= A[i][j+1]
        boolean[] cuts = new boolean[row - 1];

        int ans = 0;
        search:
        for (int j = 0; j < col; ++j) {
            // Evaluate whether we can keep this column
            for (int i = 0; i < row - 1; ++i)
                if (!cuts[i] && A[i].charAt(j) > A[i + 1].charAt(j)) {
                    // Can't keep the column - delete and continue
                    ans++;
                    continue search;
                }

            // Update 'cuts' information
            for (int i = 0; i < row - 1; ++i)
                if (A[i].charAt(j) < A[i + 1].charAt(j))
                    cuts[i] = true;
        }

        return ans;
    }
}
