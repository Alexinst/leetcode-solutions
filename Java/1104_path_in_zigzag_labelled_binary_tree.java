/*
 * https://leetcode-cn.com/problems/path-in-zigzag-labelled-binary-tree/ 
 
在一棵无限的二叉树上，每个节点都有两个子节点，树中的节点 逐行 依次按 “之” 字形进行标记。

如下图所示，在奇数行（即，第一行、第三行、第五行……）中，按从左到右的顺序进行标记；

而偶数行（即，第二行、第四行、第六行……）中，按从右到左的顺序进行标记。

	https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/06/28/tree.png

给你树上某一个节点的标号 label，请你返回从根节点到该标号为 label 节点的路径，该路径是由途经的节点标号所组成的。
 

示例 1：
	输入：label = 14
	输出：[1,3,4,14]

示例 2：
	输入：label = 26
	输出：[1,2,6,10,26]
	 

提示：1 <= label <= 10^6

---------------------------------------------------------------------------------------------------------------

In an infinite binary tree where every node has two children, the nodes are labelled in row order.

In the odd numbered rows (ie., the first, third, fifth,...), the labelling is left to right, while in the even numbered rows (second, fourth, sixth,...), the labelling is right to left.

	https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/06/28/tree.png

Given the label of a node in this tree, return the labels in the path from the root of the tree to the node with that label.
 

Example 1:
	Input: label = 14
	Output: [1,3,4,14]

Example 2:
	Input: label = 26
	Output: [1,2,6,10,26]

Constraints: 1 <= label <= 10^6
*/

/**
 * 首先找到 label 所在层数，之后进行逆推
 */
class Solution {
    private List<Integer> list;

    public List<Integer> pathInZigZagTree(int label) {
        list = new ArrayList<>();
        int level = getLevel(label);
        list.add(label);
        findPath(label, level);
        return list;
    }
    
    // 2 ^ 10 = 1048576
    private int getLevel(int label) {
        int left = 1, right = 20;
        while (left < right) {
            int level = left + (right - left) / 2;
            int border = (int) Math.pow(2, level) - 1;
            if (label == border)
                return level;
            else if (label < border)
                right = level;
            else
                left = level + 1;
        }
        return right;
    }

    private void findPath(int label, int level) {
        if (level > 1) {
            int dist = (int)Math.pow(2, level) - 1 - label;
            int path = (int)Math.pow(2, level - 2) + dist / 2;
            list.add(0, path);
            findPath(path, level - 1);
        }
    }
}

class Solution1 {
    public List<Integer> pathInZigZagTree(int label) {
        List<Integer> ans = new ArrayList<Integer>();
        while (label != 1) {
            ans.add(label);
            label = label >> 1;
            int tmp = Integer.highestOneBit(label) * 2 - 1;
            label = label ^ (tmp >> 1);
        }
        ans.add(1);
        Collections.reverse(ans);
        return ans;
    }
}


