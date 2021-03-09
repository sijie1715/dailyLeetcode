package Solutions;

import java.util.Arrays;
import java.util.LinkedList;

public class P56_MergeIntervals {
    public int[][] merge(int[][] intervals) {
        LinkedList<int[]> res = new LinkedList<>();
        int[] temp = new int[2];
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        for (int i = 0; i < intervals.length; i++) {
            temp = intervals[i];
            if (res.isEmpty() || temp[0] > res.getLast()[1]) {
                res.add(temp);
            } else {
                int oldEnd = temp[1];
                temp = res.removeLast();
                temp[1] = Math.max(temp[1], oldEnd);
                res.add(temp);
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}
