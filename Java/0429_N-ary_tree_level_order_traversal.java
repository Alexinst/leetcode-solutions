/*
给定一个 N 叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。

例如，给定一个 3叉树 :
		1
	   /|\
	  3 2 4
	 / \
	5   6
返回其层序遍历:
	[
     [1],
     [3,2,4],
     [5,6]
	]

说明:
1. 树的深度不会超过 1000。
2. 树的节点总数不会超过 5000。
*/

class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
};

class MySolution {
    private List<List<Integer>> list = new ArrayList<>();
    
    public List<List<Integer>> levelOrder(Node root) {
        levelHandler(root, 0);
        return list;
    }
    
    private void levelHandler(Node node, int level) {
        if (node == null) return;
        
        if (list.size() < level + 1)
            list.add(new ArrayList<>());
        list.get(level).add(node.val);
        
        for (Node child : node.children)
            levelHandler(child, level + 1);
    }
}
