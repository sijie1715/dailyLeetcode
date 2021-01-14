package Solutions;

import java.util.HashSet;
import java.util.PriorityQueue;

public class P778_SwimInRisingWater {
    public int swimInWater(int[][] grid) {
        // always walk in the smallest value direction
        // keep track of the max value walked
        int N = grid.length;
        HashSet<Integer> seen = new HashSet();
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(
                (a, b) -> grid[a / N][a % N] - grid[b / N][b % N]);
        pq.add(0);
        seen.add(0);
        int ans = 0;

        int[] dr = new int[]{1, -1, 0, 0};
        int[] dc = new int[]{0, 0, 1, -1};

        while (!pq.isEmpty()) {
            int cur = pq.poll();
            int r = cur / N, c = cur % N;
            ans = Math.max(ans, grid[r][c]);
            if (r == N - 1 && c == N - 1) return ans;

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i], nc = c + dc[i];
                int nk = nr * N + nc;
                if (nr >= 0 && nr < N && nc >= 0 && nc < N && !seen.contains(nk)) {
                    pq.add(nk);
                    seen.add(nk);
                }
            }
        }
        return -1;
    }
}
