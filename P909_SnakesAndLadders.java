package Solutions;

import java.util.LinkedList;
import java.util.Queue;

public class P909_SnakesAndLadders {
    public int snakesAndLadders(int[][] board) {
        // minimum steps use BFS
        int n = board.length;
        boolean[] visited = new boolean[n * n + 1];
        Queue<Integer> bfs = new LinkedList<>();
        bfs.offer(1);
        int step = 0;
        while (!bfs.isEmpty()) {
            for (int sz = bfs.size(); sz > 0; sz--) {
                int curr = bfs.poll();
                if (curr == n * n) return step;
                if (visited[curr]) continue;
                visited[curr] = true;
                for (int i = 1; i <= 6 && curr + i <= n * n; i++) {
                    int next = curr + i;
                    int nextNum = getNum(board, next);
                    if (nextNum > 0) next = nextNum;
                    if (!visited[next]) bfs.add(next);
                }
            }
            step++;
        }
        return -1;
    }

    private int getNum(int[][] board, int num) {
        int n = board.length;
        int row = (num - 1) / n;
        int x = n - 1 - row;
        int mod = (num - 1) % n;
        int y = (row % 2) == 0 ? mod : n - 1 - mod;

        return board[x][y];
    }
}
