package Solutions;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class P864_ShortestPathToGetAllKeys {
    class Position {
        int r, c, keys;

        public Position(int r, int c, int keys) {
            this.r = r;
            this.c = c;
            this.keys = keys;
        }
    }

    public int shortestPathAllKeys(String[] grid) {
        int m = grid.length, n = grid[0].length();
        int sr = -1, sc = -1;
        int numKeys = 0;
        // find starting point and numKeys
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = grid[i].charAt(j);
                if (c == '@') {
                    sr = i;
                    sc = j;
                }
                if (c >= 'a' && c <= 'f') {
                    numKeys++;
                }
            }
        }
        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};
        Position start = new Position(sr, sc, 0);
        Queue<Position> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        q.offer(start);
        visited.add(sr + "," + sc + "," + 0);
        int step = 0;
        while (!q.isEmpty()) {
            for (int size = q.size(); size > 0; size--) {
                Position curr = q.poll();
                // 1 << 3 - 1 = 1000 - 1 = 0111
                if (curr.keys == (1 << numKeys) - 1) return step;
                for (int i = 0; i < 4; i++) {
                    int nr = curr.r + dr[i], nc = curr.c + dc[i];
                    int keys = curr.keys;
                    if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                        char c = grid[nr].charAt(nc);
                        // continue if wall/no key
                        if (c == '#') continue;
                        if (c >= 'A' && c <= 'F' && ((keys >> (c - 'A')) & 1) == 0) continue;
                        // if key change keys
                        if (c >= 'a' && c <= 'f') keys |= 1 << (c - 'a');
                        // check if visited
                        if (!visited.contains(nr + "," + nc + "," + keys)) {
                            q.offer(new Position(nr, nc, keys));
                            visited.add(nr + "," + nc + "," + keys);
                        }
                    }
                }
            }
            step++;
        }
        return -1;
    }
}
