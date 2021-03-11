package Solutions;

import java.util.Arrays;

public class P435_NonOverlappingIntervals {
    /*
        1. Same as getting as many non-overlapping intervals as possible
        2. Sort by ends. Always take the earlier ending interval to leave room for later
        3. Track 'end' after sorting. If i interval starts before 'end' skip. Update 'end' otherwise.
    */
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) return 0;
        Arrays.sort(intervals, (a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);
        int count = 1, end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < end) continue;
            count++;
            end = intervals[i][1];
        }
        return intervals.length - count;
    }
}
