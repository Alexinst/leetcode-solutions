/*
 * https://leetcode-cn.com/problems/largest-values-from-labels/ 
 
我们有一个项的集合，其中第 i 项的值为 values[i]，标签为 labels[i]。

我们从这些项中选出一个子集 S，这样一来：
    1. S.length <= num_wanted
    2. 对于任意的标签 L，子集 S 中标签为 L 的项的数目总满足 <= use_limit。

返回子集 S 的最大可能的 和。

示例 1：
	输入：values = [5,4,3,2,1], labels = [1,1,2,2,3], num_wanted = 3, use_limit = 1
	输出：9
	解释：选出的子集是第一项，第三项和第五项。

示例 2：
	输入：values = [5,4,3,2,1], labels = [1,3,3,3,2], num_wanted = 3, use_limit = 2
	输出：12
	解释：选出的子集是第一项，第二项和第三项。

示例 3：
	输入：values = [9,8,8,7,6], labels = [0,0,0,1,1], num_wanted = 3, use_limit = 1
	输出：16
	解释：选出的子集是第一项和第四项。

示例 4：
	输入：values = [9,8,8,7,6], labels = [0,0,0,1,1], num_wanted = 3, use_limit = 2
	输出：24
	解释：选出的子集是第一项，第二项和第四项。

提示：
    1. 1 <= values.length == labels.length <= 20000
    2. 0 <= values[i], labels[i] <= 20000
    3. 1 <= num_wanted, use_limit <= values.length

----------------------------------------------------------------------------------------------------

We have a set of items: the i-th item has value values[i] and label labels[i].

Then, we choose a subset S of these items, such that:
    1. the length of S <= num_wanted
    2. For every label L, the number of items in S with label L is <= use_limit.

Return the largest possible sum of the subset S.

Example 1:
	Input: values = [5,4,3,2,1], labels = [1,1,2,2,3], num_wanted = 3, use_limit = 1
	Output: 9
	Explanation: The subset chosen is the first, third, and fifth item.

Example 2:
	Input: values = [5,4,3,2,1], labels = [1,3,3,3,2], num_wanted = 3, use_limit = 2
	Output: 12
	Explanation: The subset chosen is the first, second, and third item.

Example 3:
	Input: values = [9,8,8,7,6], labels = [0,0,0,1,1], num_wanted = 3, use_limit = 1
	Output: 16
	Explanation: The subset chosen is the first and fourth item.

Example 4:
	Input: values = [9,8,8,7,6], labels = [0,0,0,1,1], num_wanted = 3, use_limit = 2
	Output: 24
	Explanation: The subset chosen is the first, second, and fourth item.

Note:
    1. 1 <= values.length == labels.length <= 20000
    2. 0 <= values[i], labels[i] <= 20000
    3. 1 <= num_wanted, use_limit <= values.length
*/

class MySolution {
    public int largestValsFromLabels(int[] values, int[] labels, int num_wanted, int use_limit) {
        int len = values.length;
        int maxLabel = 0;
        int[][] pairs = new int[len][2];
        for (int i = 0; i < len; i++) {
            pairs[i][0] = values[i];
            pairs[i][1] = labels[i];
            maxLabel = Math.max(maxLabel, labels[i]);
        }
		
		// 逆序排序
        Arrays.sort(pairs, new Comparator<int[]>() {
            @Override
            public int compare(int[] arr1, int[] arr2) {
                return arr2[0] - arr1[0];
            }
        });

        int max = 0;
        int[] limits = new int[maxLabel + 1];
        for (int i = 0; i < len && num_wanted > 0; i++) {
            if (limits[pairs[i][1]] < use_limit) {
                max += pairs[i][0];
                limits[pairs[i][1]]++;
                num_wanted--;
            }
        }

        return max;
    }
}

class Solution1 {
    public int largestValsFromLabels(int[] values, int[] labels, int num_wanted, int use_limit) {
        int maxLabel = 0;
        for (int i = 0; i < values.length; i++) {
            values[i] = (values[i] << 16) | labels[i];
            maxLabel = Math.max(labels[i], maxLabel);
        }
        Arrays.sort(values);

        int res = 0, L = 0;
        int[] counts = new int[maxLabel + 1];
        for (int i = values.length - 1; i >= 0 && L < num_wanted; i--) {
            if (counts[values[i] & 0xFFFF]++ < use_limit) {
                res += (values[i] >>> 16);
                L++;
            }
        }
        return res;
    }
}
