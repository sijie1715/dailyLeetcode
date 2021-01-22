package Solutions;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class P675_CutOffTreesForGolfEvent {
    public int cutOffTree(List<List<Integer>> forest) {
        // pq to record trees in the right order
        // helper bfs function to go from this to next tree and record steps
        // boolean[] seen should be in bfs helper; helper only counts steps from this to next
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        pq.add(new int[]{0, 0, -1}); // {row, col, height}
        for (int i = 0; i < forest.size(); i++) {
            for (int j = 0; j < forest.get(0).size(); j++) {
                if (forest.get(i).get(j) > 1) {
                    pq.offer(new int[]{i, j, forest.get(i).get(j)});
                }
            }
        }
        int res = 0;
        int[] prev = pq.poll();
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int steps = minStep(forest, prev, curr);
            if (steps == -1) return -1;
            prev = curr;
            res += steps;
        }
        return res;
    }

    private int minStep(List<List<Integer>> forest, int[] sc, int[] tr) {
        int m = forest.size(), n = forest.get(0).size();
        int[] dr = {1, -1, 0, 0}, dc = {0, 0, 1, -1};
        Queue<int[]> q = new LinkedList<>();
        boolean[][] seen = new boolean[m][n];
        q.offer(new int[]{sc[0], sc[1]});
        seen[sc[0]][sc[1]] = true;
        int step = 0;
        while (!q.isEmpty()) {
            for (int size = q.size(); size > 0; size--) {
                int[] curr = q.poll();
                int r = curr[0], c = curr[1];
                // seen[r][c] = true;
                if (r == tr[0] && c == tr[1]) return step;
                for (int i = 0; i < 4; i++) {
                    int nr = r + dr[i], nc = c + dc[i];
                    if (nr >= 0 && nr < m && nc >= 0 && nc < n
                            && !seen[nr][nc] && forest.get(nr).get(nc) != 0) {
                        q.offer(new int[]{nr, nc});
                        seen[nr][nc] = true;
                    }
                }
            }
            step++;
        }
        return -1;
    }
}
