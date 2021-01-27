package Solutions;

import java.util.LinkedList;
import java.util.Queue;

public class P847_ShortestPathVisitingAllNodes {
    public int shortestPathLength(int[][] graph) {
        // use bitmask to store visitedNodes
        // need to check currNode,visitedNodes for visited; skip path that gets to same node with same keys
        int n = graph.length;
        Queue<int[]> bfs = new LinkedList<>();
        boolean[][] visited = new boolean[n][1 << n];
        for (int i = 0; i < n; i++) {
            bfs.offer(new int[]{i, 1 << i});
            // visited[i][1 << i] = true;
        }
        int target = (1 << n) - 1;
        // System.out.println(target);
        for (int step = 0; !bfs.isEmpty(); step++) {
            for (int sz = bfs.size(); sz > 0; sz--) {
                int[] curr = bfs.poll();
                int node = curr[0], keys = curr[1];
                // System.out.println(keys);
                if (keys == target) return step;
                if (visited[node][keys]) continue;
                visited[node][keys] = true;
                for (int next : graph[node]) {
                    int nKeys = keys | (1 << next);
                    if (visited[next][nKeys]) continue;
                    bfs.offer(new int[]{next, nKeys});
                }
            }
        }
        return -1;
    }
}
