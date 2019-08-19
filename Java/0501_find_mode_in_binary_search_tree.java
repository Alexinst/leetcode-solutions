/*
 * https://leetcode-cn.com/problems/find-mode-in-binary-search-tree/ 
 
给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。

假定 BST 有如下定义：
	1. 结点左子树中所含结点的值小于等于当前结点的值
	2. 结点右子树中所含结点的值大于等于当前结点的值
	3. 左子树和右子树都是二叉搜索树

例如：
	给定 BST [1,null,2,2],
	   1
	    \
	     2
	    /
	   2
	返回[2].

提示：如果众数超过1个，不需考虑输出顺序

进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）

------------------------------------------------------------------------------------

Given a binary search tree (BST) with duplicates, find all the mode(s) (the most frequently occurred element) in the given BST.

Assume a BST is defined as follows:
	1. The left subtree of a node contains only nodes with keys less than or equal to the node's key.
	2. The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
	3. Both the left and right subtrees must also be binary search trees.

For example:
	Given BST [1,null,2,2],
	   1
	    \
	     2
	    /
	   2

	return [2].

Note: If a tree has more than one mode, you can return them in any order.

Follow up: Could you do that without using any extra space? (Assume that the implicit stack space incurred due to recursion does not count).
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class MySolution {
    private List<Integer> list = new ArrayList<>();
    private TreeNode pre = null;
    private int lastCount = 0, count = 0;

    public int[] findMode(TreeNode root) {
        if (root == null) return new int[0];
        
        inorderTraversal(root);
        push();
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++)
            res[i] = list.get(i);
        return res;
    }

    public void inorderTraversal(TreeNode node) {
        if (node == null)
            return ;

        inorderTraversal(node.left);
        if (pre != null && pre.val != node.val) {
            push();
            count = 1;
        }
        else 
            count++;
        
        pre = node;
        inorderTraversal(node.right);
    }
    
    private void push() {
        if (count >= lastCount) {
            if (count > lastCount) {
                list.clear();
                lastCount = count;
            }
            list.add(pre.val);
        }        
    }
}

class Solution1 {
    private int modeCount = 0;
    private int curCount = 0;
    private Integer prevVal = null;
    private List<Integer> ret = new ArrayList<>();

    public int[] findMode(TreeNode root) {
        traverse(root); 
        int[] result = new int[ret.size()];
        for (int i = 0; i < ret.size(); i++) {
            result[i] = ret.get(i);
        }
        return result;
    }
    
    private void traverse(TreeNode node) {
        if (node == null) {
            return;
        }
        traverse(node.left);
        if (prevVal == null || node.val != prevVal) {
            curCount = 1;
        } else {
            curCount++;
        }
        if (curCount > modeCount) {
            modeCount = curCount;
            ret.clear();
            ret.add(node.val);
        } else if (curCount == modeCount) {
            ret.add(node.val);
        }
        prevVal = node.val;
        traverse(node.right);
    }
}
