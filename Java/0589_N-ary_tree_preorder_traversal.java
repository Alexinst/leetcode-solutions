/*
给定一个 N 叉树，返回其节点值的前序遍历。

例如，给定一个 3叉树 :
		1
	   /|\
      3 2 4
     / \
    5   6
返回其前序遍历: [1,3,5,6,2,4]。
 
说明: 递归法很简单，你可以使用迭代法完成此题吗?
*/

class MySolution {
    public List<Integer> preorder(Node root) {
        if (root == null)
            return new ArrayList<>();
        
        Stack<Node> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            list.add(root.val);
            
            if (root.children != null) {
                for (int i = root.children.size() - 1; i >= 0; i--)
                    stack.push(root.children.get(i));
            }
        }
        
        return list;
    }
}

class Solution1 {
    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }

    public void helper(Node root, List<Integer> list){
        if(root == null)
            return;
        list.add(root.val);
        for(Node n : root.children)
            helper(n, list);
    }
}
