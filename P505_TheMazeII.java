package Solutions;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class P505_TheMazeII {
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        // dist[x][y]=min dist from start
        int[][] dist = new int[maze.length][maze[0].length];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dist[start[0]][start[1]] = 0;
        Queue<int[]> q = new LinkedList<>();
        q.add(start);
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            for (int[] dir : dirs) {
                int x = curr[0], y = curr[1];
                int count = 0;
                while (x + dir[0] >= 0 && x + dir[0] < maze.length &&
                        y + dir[1] >= 0 && y + dir[1] < maze[0].length &&
                        maze[x + dir[0]][y + dir[1]] == 0) {
                    x += dir[0];
                    y += dir[1];
                    count++;
                }
                if (dist[curr[0]][curr[1]] + count < dist[x][y]) {
                    dist[x][y] = dist[curr[0]][curr[1]] + count;
                    q.add(new int[]{x, y});
                }
            }
        }
        return dist[destination[0]][destination[1]] == Integer.MAX_VALUE ?
                -1 : dist[destination[0]][destination[1]];
    }
}
