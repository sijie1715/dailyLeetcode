package Solutions;

import java.util.LinkedList;

public class P57_InsertInterval {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        LinkedList<int[]> res = new LinkedList<>();
        int idx = 0;
        // add all intervals before newInterval
        while (idx < intervals.length && intervals[idx][0] < newInterval[0]) {
            res.add(intervals[idx]);
            idx++;
        }

        // add newInterval to res
        // 1. no overlap
        int[] temp = new int[2];
        if (res.isEmpty() || res.getLast()[1] < newInterval[0]) {
            res.add(newInterval);
        }
        // 2. yes overlap
        else {
            temp = res.removeLast();
            temp[1] = Math.max(temp[1], newInterval[1]);
            res.add(temp);
        }

        // add the rest to res; merge if needed
        while (idx < intervals.length) {
            temp = intervals[idx];
            if (temp[0] > res.getLast()[1]) {
                res.add(temp);
            } else {
                int oldEnd = temp[1];
                temp = res.removeLast();
                temp[1] = Math.max(oldEnd, temp[1]);
                res.add(temp);
            }
            idx++;
        }

        return res.toArray(new int[res.size()][]);
    }
}
