/*
实现一个二叉搜索树迭代器。你将使用二叉搜索树的根节点初始化迭代器。

调用 next() 将返回二叉搜索树中的下一个最小的数。

示例：
		7
	   / \
	  3  15
	    /  \
	   9   20

	BSTIterator iterator = new BSTIterator(root);
	iterator.next();    // 返回 3
	iterator.next();    // 返回 7
	iterator.hasNext(); // 返回 true
	iterator.next();    // 返回 9
	iterator.hasNext(); // 返回 true
	iterator.next();    // 返回 15
	iterator.hasNext(); // 返回 true
	iterator.next();    // 返回 20
	iterator.hasNext(); // 返回 false
*/

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
}

class MyBSTIterator {
    private Stack<TreeNode> stack = new Stack<>();

    public MyBSTIterator(TreeNode root) {
        push(root);
    }

    public void push(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }
    /** @return the next smallest number */
    public int next() {
        if (hasNext()) {
            TreeNode node = stack.pop();
            push(node.right);
            return node.val;
        }
        return -Integer.MAX_VALUE;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }
}

class BSTIterator1 {
	private Stack<TreeNode> stack = new Stack<>();

	public BSTIterator1(TreeNode root) {
		inorder(root);
	}

	public void inOrder(TreeNode node) {
		if (node == null)
			return;
		else {
			inOrder(node.right);
			stack.push(node);
			inOrder(node.left);
		}
	}

	public int next() {
		return stack.pop().val;
	}

	public boolean hasNext() {
		return !stack.isEmpty();
	}
}

class BSTIterator2 {
    private ArrayList<Integer> order = new ArrayList<>();
    private int currIdx;
    private int size;

    public BSTIterator2(TreeNode root) {
        inOrder(root);
        currIdx = -1;
        size = order.size();
    }
    
    /** @return the next smallest number */
    public int next() {
        if (hasNext())
            return order.get(++currIdx);
        return -1; // 表示不存在下一个元素
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return currIdx < size - 1;
    }
    
    private void inOrder(TreeNode root) {
        if (root == null)
            return ;
        inOrder(root.left);
        order.add(root.val);
        inOrder(root.right);
    } 
}
			
