
class Solution1 {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n <= 2) {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < n; i++) 
                list.add(i);
            return list;
        }

        Map<Integer, List<Integer>> adjs = new HashMap<>();
        int[] degree = new int[n];
        for (int[] edge : edges) {
            addEdge(adjs, edge[0], edge[1]);
            addEdge(adjs, edge[1], edge[0]);
            degree[edge[0]]++;
            degree[edge[1]]++;
        }

        Queue<Integer> q1 = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1)
                q1.offer(i);
        }

        Queue<Integer> q2 = new LinkedList<>();
        boolean[] removed = new boolean[n];
        while (n > 2) {
            int size = q1.size();
            n -= q1.size();
            for (int i = 0; i < size; i++) {
                int node = q1.poll();
                removed[node] = true;
                for (int adj : adjs.get(node)) {
                    degree[adj]--;

                    if (degree[adj] == 1)
                        q1.offer(adj);
                }
            }

        }

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < removed.length; i++) {
            if (!removed[i])
                list.add(i);
        }

        return list;
    }

    private void addEdge(Map<Integer, List<Integer>> adjs, int from, int to) {
        List<Integer> lst = adjs.getOrDefault(from, new ArrayList<>());
        lst.add(to);
        adjs.put(from, lst);
    }
}
