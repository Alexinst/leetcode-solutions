/*
 * https://leetcode-cn.com/problems/reconstruct-itinerary/
 *
 * Reference: https://leetcode-cn.com/problems/reconstruct-itinerary/solution/javadfsjie-fa-by-pwrliang/

给定一个机票的字符串二维数组 [from, to]，子数组中的两个成员分别表示飞机出发和降落的机场地点，对该行程进行重新规划排序。所有这些机票都属于一个从JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 出发。

说明:
    1. 如果存在多种有效的行程，你可以按字符自然排序返回最小的行程组合。例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排序更靠前
    2. 所有的机场都用三个大写字母表示（机场代码）。
    3. 假定所有机票至少存在一种合理的行程。

示例 1:
	输入: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
	输出: ["JFK", "MUC", "LHR", "SFO", "SJC"]

示例 2:
	输入: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
	输出: ["JFK","ATL","JFK","SFO","ATL","SFO"]
	解释: 另一种有效的行程是 ["JFK","SFO","ATL","JFK","ATL","SFO"]。但是它自然排序更大更靠后。

--------------------------------------------------------------------------------------------------

Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

Note:
    1. If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
    2. All airports are represented by three capital letters (IATA code).
    3. You may assume all tickets form at least one valid itinerary.

Example 1:
	Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
	Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]

Example 2:
	Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
	Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
	Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
                 But it is larger in lexical order.
*/


class Solution1 {
    private List<String> path = new LinkedList<>();  // 返回结果
    private Map<String, PriorityQueue<String>> graph = new HashMap<>();  // (起飞地，Queue<目的地>)

    public List<String> findItinerary(List<List<String>> tickets) {
        if (tickets == null || tickets.size() == 0) return path;

        for (List<String> edge : tickets) {
            String from = edge.get(0), to = edge.get(1);
            PriorityQueue<String> adj = graph.getOrDefault(from, new PriorityQueue<>());
            adj.offer(to);
            graph.put(from, adj);
        }

        visit("JFK");
        return path;
    }

    private void visit(String src) {
        PriorityQueue<String> nbrs = graph.get(src);
        while (nbrs != null && !nbrs.isEmpty()) {
			// 当前地方没有飞行记录
            String nbr = nbrs.poll();
            visit(nbr);
        }

        path.add(0, src);
    }
}

class Solution2 {
    private Map<String, PriorityQueue<String>> map = new HashMap<>();

    private List<String> resList = new LinkedList<>();

    public List<String> findItinerary(List<List<String>> tickets) {
        for (List<String> ticket : tickets) {
            String src = ticket.get(0);
            String dst = ticket.get(1);
            if (!map.containsKey(src)) {
                PriorityQueue<String> pq = new PriorityQueue<>();
                map.put(src, pq);
            }
            map.get(src).add(dst);
        }
        dfs("JFK");
        return resList;
    }

    private void dfs(String src) {
        PriorityQueue<String> pq = map.get(src);
        while (pq != null && !pq.isEmpty())
            dfs(pq.poll());
        ((LinkedList<String>) resList).addFirst(src);
    }
}
