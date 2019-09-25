/*
 * https://leetcode-cn.com/problems/minimum-height-trees/
 *
 * Reference: https://leetcode-cn.com/problems/minimum-height-trees/solution/tan-xin-fa-gen-ju-tuo-bu-pai-xu-de-si-lu-python-da/
 
对于一个具有树特征的无向图，我们可选择任何一个节点作为根。图因此可以成为树，在所有可能的树中，具有最小高度的树被称为最小高度树。给出这样的一个图，写出一个函数找到所有的最小高度树并返回他们的根节点。

格式:
	该图包含 n 个节点，标记为 0 到 n - 1。给定数字 n 和一个无向边 edges 列表（每一个边都是一对标签）。
	你可以假设没有重复的边会出现在 edges 中。由于所有的边都是无向边， [0, 1]和 [1, 0] 是相同的，因此不会同时出现在 edges 里。

示例 1:
	输入: n = 4, edges = [[1, 0], [1, 2], [1, 3]]

		0
		|
		1
	   / \
	  2   3 

	输出: [1]

示例 2:
	输入: n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]

	 0  1  2
	  \ | /
		3
		|
		4
		|
		5 

	输出: [3, 4]

说明:
	1. 根据树的定义，树是一个无向图，其中任何两个顶点只通过一条路径连接。 换句话说，一个任何没有简单环路的连通图都是一棵树。
	2. 树的高度是指根节点和叶子节点之间最长向下路径上边的数量。

---------------------------------------------------------------------------------------------------------------------------

For an undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted tree. Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). Given such a graph, write a function to find all the MHTs and return a list of their root labels.

Format:
	The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of undirected edges (each edge is a pair of labels).
	You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

Example 1 :
	Input: n = 4, edges = [[1, 0], [1, 2], [1, 3]]

        0
	    |
	    1
	   / \
	  2   3 

	Output: [1]

Example 2 :
	Input: n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]

	 0  1  2
	  \ | /
		3
		|
		4
		|
		5 

	Output: [3, 4]

Note:
    1. According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.”
    2. The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
*/

/**
 * 思路：每次删去度为 1 的节点，直至节点剩余个数 = 1 or 2
 */
class Solution1 {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n <= 2) {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < n; i++)
                list.add(i);
            return list;
        }

        Map<Integer, List<Integer>> adjs = new HashMap<>();
        int[] degree = new int[n];  // 每个节点的度（邻居数量）
        for (int[] edge : edges) {
            addEdge(adjs, edge[0], edge[1]);
            addEdge(adjs, edge[1], edge[0]);
            degree[edge[0]]++;
            degree[edge[1]]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1)
                q.offer(i);
        }

        boolean[] removed = new boolean[n];
        while (n > 2) {
            int size = q.size();
            n -= q.size();
            for (int i = 0; i < size; i++) {
                int node = q.poll();
                removed[node] = true;
                for (int adj : adjs.get(node)) {
                    degree[adj]--;

                    if (degree[adj] == 1)
                        q.offer(adj);
                }
            }

        }

        List<Integer> list = new ArrayList<>();
        // 将剩余节点作为返回
        for (int i = 0; i < removed.length; i++) {
            if (!removed[i])
                list.add(i);
        }

        return list;
    }

    private void addEdge(Map<Integer, List<Integer>> adjs, int from, int to) {
        // 往映射中加入边
        List<Integer> lst = adjs.getOrDefault(from, new ArrayList<>());
        lst.add(to);
        adjs.put(from, lst);
    }
}
