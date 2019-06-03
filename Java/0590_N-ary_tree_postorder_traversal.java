/*
给定一个 N 叉树，返回其节点值的后序遍历。

例如，给定一个 3叉树 :
		1
	   /|\
	  3 2 4
	 / \
	5   6
返回其后序遍历: [5,6,3,2,4,1].

说明: 递归法很简单，你可以使用迭代法完成此题吗?
*/

class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}

class MySolution {
    public List<Integer> postorder(Node root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;

        Stack<Node> stack = new Stack<>();
        Node last = new Node();
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.peek();

            if (root.children != null && root.children.size() > 0
                    && root.children.get(root.children.size() - 1) != last) {
                for (int i = root.children.size(); i > 0; i--)
                    stack.push(root.children.get(i-1));

                continue;
            }

            list.add(root.val);
            last = root;
            stack.pop();
        }
        return list;
    }
}

class Solution1 {
    List<Integer> list = new ArrayList<>();
    public List<Integer> postorder(Node root) {
        if(root == null)
            return list;
        for(Node node:root.children)
            postorder(node);
        list.add(root.val);
        return list;
    }
}

class Solution2 {
    public List<Integer> postorder(Node root) {
        LinkedList<Integer> result = new LinkedList<Integer>();
        if(root == null) return result;

        // 迭代的方式
        Deque<Node> q = new ArrayDeque<Node>();
        q.add(root);
        while(!q.isEmpty()){
            Node t = q.pop();
            result.addFirst(t.val);
            if(t.children.size() > 0){
                for(int i = 0; i < t.children.size(); i++){
                    q.push(t.children.get(i));
                }
            }
        }
        return result;
    }
}
