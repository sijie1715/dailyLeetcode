package Solutions;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class P1368_MinimumCostToMakeAtLeastOneValidPathInAGrid {
    int INF = Integer.MAX_VALUE;
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int minCost(int[][] grid) {
        // DFS to visit all reachable nodes at current cost
        // BFS new cost at every level; skip visited nodes in dfs
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) Arrays.fill(dp[i], INF);
        Queue<int[]> bfs = new LinkedList<>();
        int cost = 0;
        dfs(grid, 0, 0, cost, dp, bfs);
        while (!bfs.isEmpty()) {
            cost++;
            for (int sz = bfs.size(); sz > 0; sz--) {
                int[] cur = bfs.poll();
                int r = cur[0], c = cur[1];
                for (int i = 0; i < 4; i++) {
                    dfs(grid, r + dirs[i][0], c + dirs[i][1], cost, dp, bfs);
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    private void dfs(int[][] grid, int r, int c, int cost, int[][] dp, Queue<int[]> bfs) {
        int m = grid.length, n = grid[0].length;
        // if out of bounds or visited, return
        if (r < 0 || r >= m || c < 0 || c >= n || dp[r][c] != INF) return;
        dp[r][c] = cost;
        bfs.offer(new int[]{r, c});
        int nextDir = grid[r][c] - 1;
        dfs(grid, r + dirs[nextDir][0], c + dirs[nextDir][1], cost, dp, bfs);
    }
}
