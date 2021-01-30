package Solutions;

import java.util.*;

public class P1311_GetWatchedVideosByYourFriends {
    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {

        boolean[] visited = new boolean[friends.length];
        Queue<Integer> q = new LinkedList<>();
        visited[id] = true;
        q.offer(id);
        int lv = 0;

        // get the right level of friends in queue
        while (!q.isEmpty()) {
            if (lv == level) break;
            for (int sz = q.size(); sz > 0; sz--) {
                int f = q.poll();
                // ***** cannot check&update visited after poll,
                // ***** this will lead to duplicates being left in queue
                // if (visited[f]) continue;
                // visited[f] = true;
                for (int nf : friends[f]) {
                    if (!visited[nf]) {
                        q.offer(nf);
                        visited[nf] = true;
                    }
                }
            }
            lv++;
        }
        // count the freq of wv by queued friends
        Map<String, Integer> counts = new HashMap<>();
        List<String> res = new ArrayList<>();
        while (!q.isEmpty()) {
            int f = q.poll();
            for (String wv : watchedVideos.get(f)) {
                counts.put(wv, counts.getOrDefault(wv, 0) + 1);
                if (counts.get(wv) == 1) res.add(wv);
            }
        }
        Collections.sort(res, (a, b) -> {
            if (counts.get(a) == counts.get(b)) {
                return a.compareTo(b);
            }
            return counts.get(a) - counts.get(b);
        });
        return res;
    }
}
