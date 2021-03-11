package Solutions;

import java.util.Arrays;

public class P1288_RemoveCoveredIntervals {
    public int removeCoveredIntervals(int[][] intervals) {
        // if starting at same place, take longer one first
        Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        int count = 1, end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][1] <= end) continue;
            count++;
            end = intervals[i][1];
        }
        return count;
    }
}
