package Solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P417_PacificAtlanticWaterFlow {
    int[] dr = {1, -1, 0, 0}, dc = {0, 0, 1, -1};
    int m, n;

    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        // dfs reachable (go up) from pacific and atlantic
        // add overlap to res
        List<List<Integer>> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return res;
        m = matrix.length;
        n = matrix[0].length;
        boolean[][] reachP = new boolean[m][n];
        boolean[][] reachA = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            dfs(matrix, reachP, i, 0);
            dfs(matrix, reachA, i, n - 1);
        }
        for (int j = 0; j < n; j++) {
            dfs(matrix, reachP, 0, j);
            dfs(matrix, reachA, m - 1, j);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (reachP[i][j] && reachA[i][j]) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }
        return res;
    }

    private void dfs(int[][] matrix, boolean[][] reach, int r, int c) {
        if (reach[r][c]) return;
        reach[r][c] = true;
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i], nc = c + dc[i];
            if (nr >= 0 && nr < m && nc >= 0 && nc < n
                    && matrix[nr][nc] >= matrix[r][c]) {
                dfs(matrix, reach, nr, nc);
            }
        }
    }
}
