/*
 * https://leetcode-cn.com/problems/course-schedule/
 *
 * Reference: https://leetcode-cn.com/problems/course-schedule/solution/course-schedule-tuo-bu-pai-xu-bfsdfsliang-chong-fa/
 
现在你总共有 n 门课需要选，记为 0 到 n-1。

在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]

给定课程总量以及它们的先决条件，判断是否可能完成所有课程的学习？

示例 1:
	输入: 2, [[1,0]] 
	输出: true
	解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。

示例 2:
	输入: 2, [[1,0],[0,1]]
	输出: false
	解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。

说明:
	1. 输入的先决条件是由边缘列表表示的图形，而不是邻接矩阵。详情请参见图的表示法。
	2. 你可以假定输入的先决条件中没有重复的边。

提示:
	1. 这个问题相当于查找一个循环是否存在于有向图中。如果存在循环，则不存在拓扑排序，因此不可能选取所有课程进行学习。
	2. 通过 DFS 进行拓扑排序 - 一个关于Coursera的精彩视频教程（21分钟），介绍拓扑排序的基本概念。
	3. 拓扑排序也可以通过 BFS 完成。

-------------------------------------------------------------------------------------------------------

There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

Example 1:
	Input: 2, [[1,0]] 
	Output: true
	Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.

Example 2:
	Input: 2, [[1,0],[0,1]]
	Output: false
	Explanation: There are a total of 2 courses to take. 
	             To take course 1 you should have finished course 0, and to take course 0 you should
	             also have finished course 1. So it is impossible.
Note:
	1. The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
	2. You may assume that there are no duplicate edges in the input prerequisites.

Tips:
	1. This problem is equivalent to finding if a cycle exists in a directed graph. If a cycle exists, no topological ordering exists and therefore it will be impossible to take all courses.
	2. Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera explaining the basic concepts of Topological Sort.
	3. Topological sort could also be done via BFS.
*/
/**
 * 思路：DFS。如果出现二次访问某一门课程（即出现环），意味着不可能完成所有课程的学习。
 *
 * 参数说明：
 * state：0 -- 尚未被访问
 *        1 -- 已被本次 DFS 访问
 *       -1 -- 已被其他 DFS 访问
 */
class MySolution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int[] pre : prerequisites) {
            if (!map.containsKey(pre[0]))
                map.put(pre[0], new ArrayList<>());

            map.get(pre[0]).add(pre[1]);
        }

        int[] state = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (!dfs(map, state, i))
                return false;
        }

        return true;
    }

    private boolean dfs(Map<Integer, List<Integer>> map, int[] state, int course) {
        if (state[course] == 1) return false;
        if (state[course] == -1) return true;

        state[course] = 1;
        if (map.containsKey(course)) {
            for (int adj : map.get(course)) {
                if (!dfs(map, state, adj)) return false;
            }
        }

        state[course] = -1;
        return true;
    }
}

/*
 * DFS
 */
class Solution1 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] graphic = new List[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graphic[i] = new ArrayList<>();
        }
        for (int[] pre : prerequisites) {
            graphic[pre[0]].add(pre[1]);
        }
        boolean[] globalMarked = new boolean[numCourses];
        boolean[] localMarked = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (hasCycle(globalMarked, localMarked, graphic, i)) {
                return false;
            }
        }
        return true;
    }

    private boolean hasCycle(boolean[] globalMarked, boolean[] localMarked,
                             List<Integer>[] graphic, int curNode) {
        if (localMarked[curNode]) return true;
        if (globalMarked[curNode]) return false;

        globalMarked[curNode] = true;
        localMarked[curNode] = true;
        for (int nextNode : graphic[curNode]) {
            if (hasCycle(globalMarked, localMarked, graphic, nextNode)) {
                return true;
            }
        }
        localMarked[curNode] = false;
        return false;
    }
}

/*
 * DFS
 */
class Solution2 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[][] adjacency = new int[numCourses][numCourses];
        int[] flags = new int[numCourses];
        for (int[] cp : prerequisites)
            adjacency[cp[1]][cp[0]] = 1;
        for (int i = 0; i < numCourses; i++) {
            if (!dfs(adjacency, flags, i)) return false;
        }
        return true;
    }

    private boolean dfs(int[][] adjacency, int[] flags, int i) {
        if (flags[i] == 1) return false;
        if (flags[i] == -1) return true;
        flags[i] = 1;
        for (int j = 0; j < adjacency.length; j++) {
            if (adjacency[i][j] == 1 && !dfs(adjacency, flags, j)) return false;
        }
        flags[i] = -1;
        return true;
    }
}

/*
 * BFS
 */
class Solution3 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegrees = new int[numCourses];
        for (int[] cp : prerequisites)
            indegrees[cp[0]]++;

        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegrees[i] == 0) queue.addLast(i);
        }

        while (!queue.isEmpty()) {
            Integer pre = queue.removeFirst();
            numCourses--;
            for (int[] req : prerequisites) {
                if (req[1] != pre) continue;
                if (--indegrees[req[0]] == 0) queue.add(req[0]);
            }
        }

        return numCourses == 0;
    }
}
