/*
 * https://leetcode-cn.com/problems/clone-graph/

给定无向连通图中一个节点的引用，返回该图的深拷贝（克隆）。图中的每个节点都包含它的值 val（Int） 和其邻居的列表（list[Node]）。

示例：
	https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/02/23/113_sample.png
输入：{"$id":"1","neighbors":[{"$id":"2","neighbors":[{"$ref":"1"},{"$id":"3","neighbors":[{"$ref":"2"},{"$id":"4","neighbors":[{"$ref":"3"},{"$ref":"1"}],"val":4}],"val":3}],"val":2},{"$ref":"4"}],"val":1}

解释：
	节点 1 的值是 1，它有两个邻居：节点 2 和 4 。
	节点 2 的值是 2，它有两个邻居：节点 1 和 3 。
	节点 3 的值是 3，它有两个邻居：节点 2 和 4 。
	节点 4 的值是 4，它有两个邻居：节点 1 和 3 。

提示：
    1. 节点数介于 1 到 100 之间。
    2. 无向图是一个简单图，这意味着图中没有重复的边，也没有自环。
    3. 由于图是无向的，如果节点 p 是节点 q 的邻居，那么节点 q 也必须是节点 p 的邻居。
    4. 必须将给定节点的拷贝作为对克隆图的引用返回。

----------------------------------------------------------------------------------------------

Given a reference of a node in a connected undirected graph, return a deep copy (clone) of the graph. Each node in the graph contains a val (int) and a list (List[Node]) of its neighbors.

Example:
	https://assets.leetcode.com/uploads/2019/02/19/113_sample.png
Input: {"$id":"1","neighbors":[{"$id":"2","neighbors":[{"$ref":"1"},{"$id":"3","neighbors":[{"$ref":"2"},{"$id":"4","neighbors":[{"$ref":"3"},{"$ref":"1"}],"val":4}],"val":3}],"val":2},{"$ref":"4"}],"val":1}

Explanation:
	Node 1's value is 1, and it has two neighbors: Node 2 and 4.
	Node 2's value is 2, and it has two neighbors: Node 1 and 3.
	Node 3's value is 3, and it has two neighbors: Node 2 and 4.
	Node 4's value is 4, and it has two neighbors: Node 1 and 3.

Note:
    1. The number of nodes will be between 1 and 100.
    2. The undirected graph is a simple graph, which means no repeated edges and no self-loops in the graph.
    3. Since the graph is undirected, if node p has node q as neighbor, then node q must have node p as neighbor too.
    4. You must return the copy of the given node as a reference to the cloned graph.
*/

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {}

    public Node(int _val,List<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
};
*/
/**
 * 思路：深度优先遍历，并利用 map 记录已遍历的节点。
 */
class MySolution {
    private Map<Integer, Node> map = new HashMap<>();

    public Node cloneGraph(Node node) {
        // 递归结束条件：node已克隆过
        if (map.containsKey(node.val))
            return map.get(node.val);
        
        // 克隆节点
        Node dup = new Node(node.val, new ArrayList<Node>());
        map.put(node.val, dup);

        for (Node neightbor : node.neighbors) {
            dup.neighbors.add(cloneGraph(neightbor));
        }

        return dup;
    }
}

class Solution1 {
    HashMap<Integer, Node> marked = new HashMap<Integer, Node>();

    public Node cloneGraph(Node node) {
        Node rt = new Node();
        rt.val = node.val;
        marked.put(rt.val, rt);
        List<Node> l = new ArrayList<>();
        for (Node n : node.neighbors) {
            if (marked.containsKey(n.val)) {
                l.add(marked.get(n.val));
            } else {
                Node tmp = cloneGraph(n);
                l.add(tmp);
            }
        }
        rt.neighbors = l;
        return rt;
    }
}
