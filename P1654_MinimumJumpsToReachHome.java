package Solutions;

import java.util.HashSet;
import java.util.PriorityQueue;

public class P1654_MinimumJumpsToReachHome {
    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        // pq order by step
        // bfs to make sure the smallest step gets tried first
        PriorityQueue<int[]> pq = new PriorityQueue<>((a1, b1) -> a1[0] - b1[0]);
        pq.offer(new int[]{0, 0, 0});  // step, idx, dir (0 back, 1 forward)
        HashSet<Integer> forbid = new HashSet<>();
        // "step,1" add direction to avoid duplicate route
        HashSet<String> visited = new HashSet<>();
        // no need to reach beyond this range
        int maxLimit = x + 2 * b;
        for (int num : forbidden) {
            forbid.add(num);
            maxLimit = Math.max(maxLimit, num + 2 * b);
        }

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int step = curr[0], idx = curr[1], dir = curr[2];
            if (idx == x) return step;

            // jump forward
            if (idx + a <= maxLimit && !forbid.contains(idx + a)
                    && !visited.contains(idx + a + "," + 1)) {
                pq.add(new int[]{step + 1, idx + a, 1});
                visited.add(idx + a + "," + 1);
            }
            // jump backward
            if (idx - b >= 0 && !forbid.contains(idx - b)
                    && !visited.contains(idx - b + "," + 0) && dir != 0) {
                pq.add(new int[]{step + 1, idx - b, 0});
                visited.add(idx - b + "," + 0);
            }
        }
        return -1;
    }
}
