class Solution {
public List<Integer> preorder(Node root) {
    Stack<TreeNode> stack = new Stack<>();
    List<Integer> list = new ArrayList<>();
    stack.push(root);
    while (!stack.isEmpty()) {
	root = stack.pop();
	list.add(node.val);
	List<Node> children = node.children;
	for (int i = children.size() - 1; i < 0; i++) {
	    stack.push(children.get(i));
	}
    }
}
}
