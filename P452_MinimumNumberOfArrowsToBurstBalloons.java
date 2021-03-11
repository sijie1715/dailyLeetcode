package Solutions;

import java.util.Arrays;
import java.util.Comparator;

public class P452_MinimumNumberOfArrowsToBurstBalloons {
    /*
    1. Sort intervals by end (then start)
    2. If interval start <= end then continue (can burst with same arrow)
    3. If not count++, update end
    */
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) return 0;
        // Arrays.sort(points, (a, b) -> a[1] == b[1] ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1]));
        Arrays.sort(points, Comparator.comparingInt(o -> o[1]));
        int count = 1, end = points[0][1];
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] <= end) continue;
            count++;
            end = points[i][1];
        }
        return count;
    }
}
