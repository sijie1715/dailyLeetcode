package Solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P1272_RemoveInterval {
    public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
        List<List<Integer>> res = new ArrayList<>();
        for (int[] interval : intervals) {
            if (interval[1] <= toBeRemoved[0] || interval[0] >= toBeRemoved[1]) {
                res.add(Arrays.asList(interval[0], interval[1]));
            } else {
                // interval[1] > toBeRemoved[0] && interval[0] < toBeRemoved[1]
                // add before toBeRemoved?
                if (interval[0] < toBeRemoved[0]) {
                    res.add(Arrays.asList(interval[0], toBeRemoved[0]));
                }
                // add after toBeRemoved?
                if (interval[1] > toBeRemoved[1]) {
                    res.add(Arrays.asList(toBeRemoved[1], interval[1]));
                }
            }
        }
        return res;
    }
}
