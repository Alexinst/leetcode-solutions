class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    private TreeNode keyNode = null;
    private TreeNode exchange = null;

    public TreeNode deleteNode(TreeNode root, int key) {
        search(root, key);

        if (keyNode.left == null && keyNode.right == null) {
            setNull(root, keyNode);
        }
        else if (keyNode.left == null) {
            replaceAWithB(keyNode, keyNode.right);
        }
        else if (keyNode.right == null) {
            replaceAWithB(keyNode, keyNode.left);
        }
        else {
            searchMin(keyNode);  // keyNode分支的最小值（最左值）
            keyNode.val = exchange.val;
            if (exchange.right == null)
                setNull(keyNode, exchange);
            else {
                exchange.val = exchange.right.val;
                exchange.left = exchange.right.left;
                exchange.right = exchange.right.right;
            }
        }

        return root;
    }

    private void replaceAWithB(TreeNode a, TreeNode b) {
        a.val = b.val;
        a.left = b.left;
        a.right = b.right;
    }

    private void setNull(TreeNode root, TreeNode toBeNull) {
        if (root.left == toBeNull) {
            root.left = null;
            return;
        }
        if (root.right == toBeNull){
            root.right = null;
            return;
        }
        if (root.val <= toBeNull.val)
            setNull(root.right, toBeNull);
        else // root.val > toBeNull.val)
            setNull(root.left, toBeNull);
    }

    private void search(TreeNode node, int val) {
        if (node.val == val)
            keyNode = node;
        else if (node.val < val)
            search(node.right, val);
        else
            search(node.left, val);
    }

    private void searchMin(TreeNode node) {
        if (node.left != null)
            searchMin(node.left);
        else
            exchange = node;
    }
}

class Solution {
    private List<TreeNode> list = new ArrayList<>();
    private int indexKey = -1;
    private Stack<TreeNode> stack = new Stack<>();
    
    public TreeNode deleteNode(TreeNode root, int key) {
        sort(root, key);
        if (indexKey == 0)
            return root.right;
        else if (indexKey == list.size() - 1)
            return root.left;
        else {
            TreeNode nodeKey = list.get(indexKey);
            if (nodeKey.left == null && nodeKey.right == null)
                setNull(nodeKey);
            else if (nodeKey.left == null)
                repAWithB(nodeKey, nodeKey.right);
            else if (nodeKey.right == null)
                repAWithB(nodeKey, nodeKey.left);
            else {
                
            }
        }
    }
    
    private void sort(TreeNode root, int key) {
        
    }
    
    private void setNull(TreeNode node)
    {
        
    }
    
    private void repAWithB(TreeNode a, TreeNode b)
    {
        a.val = b.val;
        a.left = b.left;
        a.right = b.right;
    }
}
