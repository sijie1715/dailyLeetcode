package Solutions;

import java.util.*;

public class P787_CheapestFlightsWithinKStops {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        // BFS
        // graph<from,<to,cost>>
        // pq<city,stops,sumcost> sort by cost; always go by min cost route
        List<Map<Integer, Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new HashMap());
        for (int[] f : flights) {
            graph.get(f[0]).put(f[1], f[2]);
        }
        // pq<city,stops,sumcost>
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        // 2 cities=1 stop
        // skip when stops>K+1
        pq.add(new int[]{src, 0, 0});
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            if (curr[1] > K + 1) continue;
            int city = curr[0];
            if (city == dst) return curr[2];
            for (int nextCity : graph.get(city).keySet()) {
                int cost = graph.get(city).get(nextCity);
                pq.add(new int[]{nextCity, curr[1] + 1, curr[2] + cost});
            }
        }
        return -1;
    }
}
