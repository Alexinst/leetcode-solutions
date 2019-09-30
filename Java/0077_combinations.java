/*
 * https://leetcode-cn.com/problems/combinations/

给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。

示例:
	输入: n = 4, k = 2
	输出:
	[
	  [2,4],
	  [3,4],
	  [2,3],
	  [1,2],
	  [1,3],
	  [1,4],
	]

---------------------------------------------------------------------------------------------------

Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

Example:
	Input: n = 4, k = 2
	Output:
	[
	  [2,4],
	  [3,4],
	  [2,3],
	  [1,2],
	  [1,3],
	  [1,4],
	]
 */

class MySolution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> combinations = new ArrayList<>();
        if (n < 1 || k < 1 || n < k) return combinations;
        backtrack(combinations, new LinkedList<>(), 1, n, k);

        return combinations;
    }

    /**
     *
     * @param combinations 所有排列
     * @param com 某种可能的组合
     * @param i
     * @param n
     * @param k 组合中尚未确定的个数
     */
    private void backtrack(List<List<Integer>> combinations, LinkedList<Integer> com,
                           int i, int n, int k) {
        if (k == 0) {
            combinations.add(new ArrayList<>(com));
            return;
        }

        for (; i <= n - k + 1; i++) {
            com.add(i);
            backtrack(combinations, com, i + 1, n, k - 1);
            com.pollLast();
        }
    }
}

class Solution1 {
    List<List<Integer>> res = new ArrayList<>();
    private int n;
    private int k;

    public List<List<Integer>> combine(int n, int k) {
        this.n = n;
        this.k = k;
        if (n >= 1) {
            helper(new ArrayList<>(), 1);
        }

        return res;
    }

    private void helper(List<Integer> list, int i) {
        if (list.size() == k) {
            res.add(new ArrayList<>(list));
            return;
        }

        for (int j = i; j <= n - (k - list.size() - 1); j++) {
            list.add(j);
            helper(list, j + 1);
            list.remove(list.size() - 1);
        }
    }
}
