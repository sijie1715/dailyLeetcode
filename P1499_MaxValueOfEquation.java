package Solutions;

import java.util.ArrayDeque;
import java.util.Deque;

public class P1499_MaxValueOfEquation {
    public int findMaxValueOfEquation(int[][] points, int k) {
        // deque for storing point index
        Deque<Integer> d = new ArrayDeque<>();
        int res = Integer.MIN_VALUE;
        d.add(0);
        for (int i = 1; i < points.length; i++) {
            while (!d.isEmpty() && points[i][0] - points[d.peek()][0] > k) {
                d.poll();
            }
            if (!d.isEmpty()) res = Math.max(res, cal(points, i, d.peek()));
            // yi + yj + |xi - xj| => (yj+xj)+(yi-xi)
            // polllast if (yi-xi) is greater
            while (!d.isEmpty()
                    && points[i][1] - points[i][0] > points[d.peekLast()][1] - points[d.peekLast()][0]) {
                d.pollLast();
            }
            d.add(i);
        }
        return res;
    }

    private int cal(int[][] points, int i, int j) {
        return points[i][1] + points[j][1] + Math.abs(points[i][0] - points[j][0]);
    }
}
