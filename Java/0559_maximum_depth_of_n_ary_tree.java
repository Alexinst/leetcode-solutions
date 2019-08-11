/*
 * https://leetcode-cn.com/problems/maximum-depth-of-n-ary-tree/

给定一个 N 叉树，找到其最大深度。

最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。

例如，给定一个 3叉树 :

	https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/12/narytreeexample.png

	我们应返回其最大深度，3。

说明:
	1. 树的深度不会超过 1000。
	2. 树的节点总不会超过 5000。

--------------------------------------------------------------------------------------------

Given a n-ary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

For example, given a 3-ary tree:

 	https://assets.leetcode.com/uploads/2018/10/12/narytreeexample.png

	We should return its max depth, which is 3.

Note:
	1. The depth of the tree is at most 1000.
	2. The total number of nodes is at most 5000.
*/

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/
class MySolution {
    public int maxDepth(Node root) {
        if (root == null)
            return 0;
        
        int max = 0;
        for (Node child : root.children) {
            max = Math.max(max, maxDepth(child));
        }
        return max + 1;
    }
}


