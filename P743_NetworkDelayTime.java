package Solutions;

public class P743_NetworkDelayTime {
    public int networkDelayTime(int[][] times, int N, int K) {
        // map<u,<v,w>>
        // pq<w,v>
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for (int[] time : times) {
            map.putIfAbsent(time[0], new HashMap<>());
            map.get(time[0]).put(time[1], time[2]);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> a[0] - b[0]);
        pq.add(new int[]{0, K});
        int res = 0;
        boolean[] visited = new boolean[N + 1];
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curNode = cur[1];
            int curDist = cur[0];
            if (visited[curNode]) continue;
            visited[curNode] = true;
            // only update number of visited and dist after checking
            N--;
            res = curDist;
            if (map.containsKey(curNode)) {
                for (int next : map.get(curNode).keySet()) {
                    pq.add(new int[]{curDist + map.get(curNode).get(next), next});
                }
            }
        }
        return N == 0 ? res : -1;
    }
}
