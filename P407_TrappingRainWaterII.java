package Solutions;

import java.util.PriorityQueue;

public class P407_TrappingRainWaterII {
    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length, n = heightMap[0].length;
        boolean[][] visited = new boolean[m][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (a, b) -> heightMap[a[0]][a[1]] - heightMap[b[0]][b[1]]);

        // add boundaries to pq and mark visited
        for (int i = 0; i < m; i++) {
            pq.add(new int[]{i, 0});
            pq.add(new int[]{i, n - 1});
            visited[i][0] = true;
            visited[i][n - 1] = true;
        }
        for (int i = 1; i < n - 1; i++) {
            pq.add(new int[]{0, i});
            pq.add(new int[]{m - 1, i});
            visited[0][i] = true;
            visited[m - 1][i] = true;
        }

        int max = 0, res = 0;
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            max = Math.max(max, heightMap[curr[0]][curr[1]]);
            for (int[] d : dirs) {
                int row = curr[0] + d[0];
                int col = curr[1] + d[1];
                if (row > 0 && row < m - 1 && col > 0 && col < n - 1 && !visited[row][col]) {
                    visited[row][col] = true;
                    res += Math.max(0, max - heightMap[row][col]);
                    pq.add(new int[]{row, col});
                }
            }
        }
        return res;
    }
}
