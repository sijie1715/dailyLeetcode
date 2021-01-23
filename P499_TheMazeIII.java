package Solutions;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class P499_TheMazeIII {
    class Position {
        int row, col, dist;
        boolean isHole;
        String path;

        public Position(int row, int col, int dist, boolean isHole, String path) {
            this.row = row;
            this.col = col;
            this.dist = dist;
            this.isHole = isHole;
            this.path = path;
        }
    }

    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        Queue<Position> pq = new PriorityQueue<>((a, b) -> {
            if (a.dist == b.dist) {
                return a.path.compareTo(b.path);
            }
            return a.dist - b.dist;
        });
        pq.add(new Position(ball[0], ball[1], 0, false, ""));
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        while (!pq.isEmpty()) {
            Position curr = pq.poll();
            // System.out.println(curr.path);
            visited[curr.row][curr.col] = true;
            if (curr.isHole) return curr.path;
            for (Position next : nextPositions(maze, curr, hole)) {
                if (!visited[next.row][next.col]) {
                    pq.add(next);
                    // visited[next.row][next.col] = true;
                }
            }
        }
        return "impossible";
    }

    private List<Position> nextPositions(int[][] maze, Position curr, int[] hole) {
        int[] dr = new int[]{1, -1, 0, 0};
        int[] dc = new int[]{0, 0, 1, -1};
        String[] dirStrs = new String[]{"d", "u", "r", "l"};
        List<Position> list = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            int nr = curr.row, nc = curr.col, dist = curr.dist;
            boolean isHole = false;
            while (nr + dr[i] >= 0 && nr + dr[i] < maze.length
                    && nc + dc[i] >= 0 && nc + dc[i] < maze[0].length
                    && maze[nr + dr[i]][nc + dc[i]] != 1) {
                nr += dr[i];
                nc += dc[i];
                dist++;
                if (nr == hole[0] && nc == hole[1]) {
                    isHole = true;
                    break;
                }
            }
            String nPath = curr.path + dirStrs[i];
            list.add(new Position(nr, nc, dist, isHole, nPath));
        }
        return list;
    }
}
