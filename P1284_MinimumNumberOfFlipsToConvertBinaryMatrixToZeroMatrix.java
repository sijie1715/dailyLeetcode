package Solutions;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class P1284_MinimumNumberOfFlipsToConvertBinaryMatrixToZeroMatrix {
    public int minFlips(int[][] mat) {
        // BFS
        // use int and bit manipulation to represent matrix
        int start = 0, m = mat.length, n = mat[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                start |= mat[i][j] << (i * n + j);
            }
        }
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        q.add(start);
        visited.add(start);

        int[] dr = {0, 1, -1, 0, 0};
        int[] dc = {0, 0, 0, 1, -1};
        int step = 0;
        while (!q.isEmpty()) {
            for (int size = q.size(); size > 0; size--) {
                int curr = q.poll();
                if (curr == 0) return step;
                visited.add(curr);
                // traverse all bits
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        int next = curr;
                        // flip the bit and neighbors
                        for (int k = 0; k < 5; k++) {
                            int r = i + dr[k], c = j + dc[k];
                            if (r >= 0 && r < m && c >= 0 && c < n) {
                                next ^= 1 << (r * n + c);
                            }
                        }
                        if (!visited.contains(next)) q.add(next);
                    }
                }
            }
            step++;
        }
        return -1;
    }
}
