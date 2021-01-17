package Solutions;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class P490_TheMaze {
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        boolean[][] visited = new boolean[maze.length][];
        for (int i = 0; i < maze.length; i++) visited[i] = new boolean[maze[0].length];
        Queue<int[]> q = new LinkedList<>();

        visited[start[0]][start[1]] = true;
        q.add(start);

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            if (Arrays.equals(curr, destination)) return true;
            visited[curr[0]][curr[1]] = true;
            for (int[] dir : dirs) {
                int[] next = nextStop(maze, curr, dir);
                if (visited[next[0]][next[1]]) continue;
                q.add(next);
            }
        }
        return false;
    }

    private int[] nextStop(int[][] maze, int[] curr, int[] dir) {
        int x = curr[0], y = curr[1];
        while (x + dir[0] >= 0 && x + dir[0] < maze.length
                && y + dir[1] >= 0 && y + dir[1] < maze[0].length
                && maze[x + dir[0]][y + dir[1]] == 0) {
            x += dir[0];
            y += dir[1];
        }
        return new int[]{x, y};
    }
}
