/*
给定一个二叉树，返回其按层次遍历的节点值。（即逐层地，从左到右访问所有节点）。

例如:
	给定二叉树: [3,9,20,null,null,15,7],
			3
		   / \
		  9  20
			/  \
		   15   7
	返回其层次遍历结果：
		[
		  [3],
		  [9,20],
		  [15,7]
		]
*/

class TreeNode {
     int val;
     TreeNode left = null;
     TreeNode right = null;
     TreeNode(int x) { val = x; }
}

class MySolution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root != null)
        {
            List<TreeNode> list1 = new ArrayList<>();
            list1.add(root);

            do
            {
                List<TreeNode> nextLevel = new ArrayList<>();
                List<Integer> values = new ArrayList<>();
                for (TreeNode node : list1)
                {
                    values.add(node.val);
                    if (node.left != null)
                        nextLevel.add(node.left);

                    if (node.right != null)
                        nextLevel.add(node.right);
                }
                list1 = nextLevel;
                result.add(values);
            } while(list1.size() > 0);
        }

        return result;
    }
}

class Solution1 {
    private List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> levelOrder(TreeNode root) {
        levelHandler(root, 0);
        return result;
    }

    public void levelHandler(TreeNode node, int level) {
        if (node == null) return;

        if (result.size() < level + 1)
            result.add(new ArrayList<>());

        result.get(level).add(node.val);
        levelHandler(node.left, level + 1);
        levelHandler(node.right, level + 1);
    }
}
