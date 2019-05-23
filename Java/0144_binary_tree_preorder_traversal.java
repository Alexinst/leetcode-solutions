/*
给定一个二叉树，返回它的 前序 遍历。

示例:
	输入: [1,null,2,3]  
	   1
		\
		 2
		/
	   3 
	输出: [1,2,3]
	进阶: 递归算法很简单，你可以通过迭代算法完成吗？
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
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root != null) {
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);       // 将根节点压入栈
            while (!stack.isEmpty()) {
                // 栈：先进后出
                // 取出栈顶节点，并按先左后右的顺序将其子节点压入栈中
                root = stack.pop();
                list.add(root.val);
                
                if (root.right != null)
                    stack.push(root.right);
                
                if (root.left != null)
                    stack.push(root.left);
            }
        }
        return list;
    }
}
