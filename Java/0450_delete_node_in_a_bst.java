class Solution {
    private keyNode = null;
    
    public TreeNode deleteNode(TreeNode root, int key) {
        search(root, key);
        
        if (node.left == null && node.right == null)
            keyNode = null;
        else if (node.left == null) {
            keyNode = keyNode.right.val;
            keyNode.left = keyNode.right.left;
            keyNode.right = keyNode.right.right;
        }
        else if (node.right == null) {
            keyNode = keyNode.left.val;
            keyNode.left = keyNode.left.left;
            keyNode.right = keyNode.left.right;
        }
        else {
            
        }
        
        return root;
    }
    
    private void search(TreeNode node, int val) {
        if (node.val == val)
            keyNode = node;
        else if (node.val < val)
            search(node.right, val);
        else
            search(node.left, val);
    }
}
