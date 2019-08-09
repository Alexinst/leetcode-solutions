/*
 * https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/

将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。

本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。

示例:
	给定有序数组: [-10,-3,0,5,9],

	一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：

	      0
	     / \
	   -3   9
	   /   /
	 -10  5
-----------------------------------------------------------------------------------

Given an array where elements are sorted in ascending order, convert it to a height balanced BST.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

Example:
	Given the sorted array: [-10,-3,0,5,9],

	One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:

	      0
	     / \
	   -3   9
	   /   /
	 -10  5
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

    private int[] nums;
    private int[] checked;

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0)
            return null;
        
        this.nums = nums;
        int len = nums.length;
        checked = new int[len];

        int mid = (len - 1) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        checked[mid] = 1;
        toBST(root, 0, mid, len - 1);

        return root;
    }

    private void toBST(TreeNode par, int from, int mid, int to) {
        int l = from + (mid - from) / 2;
        int r = mid + (to - mid + 1) / 2;
        if (checked[l] == 1 && checked[r] == 1)
            return;

        TreeNode left = null, right = null;
        if (checked[l] == 0) {
            left = new TreeNode(nums[l]);
            checked[l] = 1;
        }
        if (checked[r] == 0) {
            right = new TreeNode(nums[r]);
            checked[r] = 1;
        }
        par.left = left;
        par.right = right;
        toBST(left, from, l, mid);
        toBST(right, mid, r, to);
    }
}

class Solution {
    public TreeNode sortedArrayToBST(int[] num) {
		return sortedArrayToBST(num, 0, num.length - 1);
	}

	private TreeNode sortedArrayToBST(int[] arr, int start, int end) {
		if (start > end) {
			return null;
		}
		int mid = (start + end) / 2;
		TreeNode node = new TreeNode(arr[mid]);
		node.left = sortedArrayToBST(arr, start, mid - 1);
		node.right = sortedArrayToBST(arr, mid + 1, end);
		return node;
	}
}
