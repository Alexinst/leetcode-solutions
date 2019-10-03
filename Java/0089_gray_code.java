/*
 * https://leetcode-cn.com/problems/gray-code/
 *
 * Reference: https://leetcode-cn.com/problems/gray-code/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--12/
 
格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。

给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。格雷编码序列必须以 0 开头。

示例 1:
	输入: 2
	输出: [0,1,3,2]
	解释:
	00 - 0
	01 - 1
	11 - 3
	10 - 2

	对于给定的 n，其格雷编码序列并不唯一。
	例如，[0,2,3,1] 也是一个有效的格雷编码序列。

	00 - 0
	10 - 2
	11 - 3
	01 - 1

	示例 2:

	输入: 0
	输出: [0]
	解释: 我们定义格雷编码序列必须以 0 开头。
	      给定编码总位数为 n 的格雷编码序列，其长度为 2^n。当 n = 0 时，长度为 20 = 1。
     	      因此，当 n = 0 时，其格雷编码序列为 [0]。

---------------------------------------------------------------------------------------------------

The gray code is a binary numeral system where two successive values differ in only one bit.

Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.

Example 1:
	Input: 2
	Output: [0,1,3,2]
	Explanation: 00 - 0
	             01 - 1
	             11 - 3
	             10 - 2

	For a given n, a gray code sequence may not be uniquely defined.
	For example, [0,2,3,1] is also a valid gray code sequence.

	             00 - 0
	             10 - 2
	             11 - 3
	             01 - 1

Example 2:
	Input: 0
	Output: [0]
	Explanation: We define the gray code sequence to begin with 0.
                     A gray code sequence of n has size = 2^n, which for n = 0 the size is 20 = 1.
                     Therefore, for n = 0 the gray code sequence is [0].
*/

class Solution1 {
    public List<Integer> grayCode(int n) {
        List<Integer> com = new ArrayList<>();

        com.add(0);

        for (int i = 0; i < n; i++) {
            int size = com.size();

            for (int j = size - 1; j >= 0; j--) {
                com.add(com.get(j) + size);
            }
        }

        return com;
    }
}


class Solution2 {
    public List<Integer> grayCode(int n) {
        List<Integer> list = new ArrayList();
        list.add(0);
        for(int i = 1; i <= n; i++) {
            int add = 1 << (i-1);
            int len = list.size();
            for(int j = len-1; j >= 0; j--) {
                list.add(list.get(j)+add);
            }
        }
        return list;
    }
}

class Solution3 {
    public List<Integer> grayCode(int n) {
        List<Integer> gray = new ArrayList<Integer>();
        gray.add(0); //初始化第零项
        for (int i = 1; i < (1 << n); i++) {
            //得到上一个的值
            int pre = gray.get(i - 1);

            //同第一项的情况
            if (i % 2 == 1) {
                // pre ^= 1; //和 0000001 做异或，使得最右边一位取反
                pre = pre % 2 == 1 ? pre - 1 : pre + 1;
                gray.add(pre);
                //同第二项的情况
            } else {
                int temp = pre;
                //寻找右边起第第一个为 1 的位元
                for (int j = 0; j < n; j++) {
                    if ((temp & 1) == 1) {
                        // 和 00001000000 类似这样的数做异或，使得相应位取反
                        pre = pre ^ (1 << (j + 1));
                        gray.add(pre);
                        break;
                    }
                    temp = temp >> 1;
                }
            }
        }
        return gray;
    }
}

/**
 * 公式法
 */
class Solution4 {
    public List<Integer> grayCode(int n) {
        List<Integer> gray = new ArrayList<Integer>();
        for(int binary = 0;binary < 1 << n; binary++){
            gray.add(binary ^ binary >> 1);
        }
        return gray;
    }
}

// 动态规划
class Solution5 {
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        res.add(0);
        
        int base = 1;
        while (n-- > 0) {
            for (int i = base - 1; i >= 0; i--) {
                res.add(base + res.get(i));
            }
            base <<= 1;
        }
        return res;
    }
}
