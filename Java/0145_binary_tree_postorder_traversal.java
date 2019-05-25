/*
给定一个二叉树，返回它的 后序 遍历。

示例:
	输入: [1,null,2,3]  
   		1
    	 \
     	  2
    	 /
   		3 
	输出: [3,2,1]

进阶: 递归算法很简单，你可以通过迭代算法完成吗？
*/

class TreeNode {
    int val;
    TreeNode left = null;
    TreeNode right = null;
    TreeNode(int x) { val = x; }
}

class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        TreeNode last = null;
        while (!stack.isEmpty() || node != null)
        {
            while (node != null)
            {
                stack.push(node);
                node = node.left;
            }
            node = stack.peek();
            if (node.right == null || node.right == last)
            {
                list.add(node.val);
                stack.pop();
                last = node;
                node = null;
            }
            else
                node = node.right;
        }
        return list;
    }
}
