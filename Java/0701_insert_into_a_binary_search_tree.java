/*
给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 保证原始二叉搜索树中不存在新值。

注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回任意有效的结果。

例如, 给定二叉搜索树:
        4
       / \
      2   7
     / \
    1   3
	和 插入的值: 5
	
	你可以返回这个二叉搜索树:
         4
       /   \
      2     7
     / \   /
    1   3 5
	
	或者这个树也是有效的:
         5
       /   \
      2     7
     / \   
    1   3
         \
          4
*/
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
}

class MySolution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        insert(root, val);
        return root;
    }
    
    private void insert(TreeNode node, int val) {
        if (node.val < val) {
            if (node.right == null)
                node.right = new TreeNode(val);
            else 
                insert(node.right, val);
        }
        else {  // node.val > val
            if (node.left == null)
                node.left = new TreeNode(val);
            else
                insert(node.left, val);
        }
    }
}

class Solution1 {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return root;
        TreeNode cur = root;//相当于游标的前一个节点
        TreeNode insertpoint = null;//游标
        while (cur != null) {
            insertpoint = cur;
            if (cur.val > val) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        if (insertpoint != null) {//插入的节点
            if (insertpoint.val < val) {
                insertpoint.right = new TreeNode(val);
            } else {
                insertpoint.left = new TreeNode(val);
            }
        }
        
        return root;
    }
}
