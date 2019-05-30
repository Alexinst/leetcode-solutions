/*
给定一个二叉树，判断其是否是一个有效的二叉搜索树。

假设一个二叉搜索树具有如下特征：
	1. 节点的左子树只包含小于当前节点的数。
	2. 节点的右子树只包含大于当前节点的数。
	3. 所有左子树和右子树自身必须也是二叉搜索树。

示例 1:
	输入:
	    2
	   / \
	  1   3
	输出: true

示例 2:
	输入:
	    5
	   / \
	  1   4
	     / \
	    3   6
	输出: false
	解释: 输入为: [5,1,4,null,null,3,6]。
     	  根节点的值为 5 ，但是其右子节点值为 4 。
*/

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class MySolution {
    public boolean isValidBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (list.size() > 0 && list.get(list.size()-1) >= root.val)
                return false;
            else 
                list.add(root.val);
            
            root = root.right;
        }
        return true;
    }
}

class Solution1 {
   
    double last = -Double.MAX_VALUE;

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (isValidBST(root.left)) {
            if (last < root.val) {
                last = root.val;
                return isValidBST(root.right);
            }
        }
        return false;
    }
}
