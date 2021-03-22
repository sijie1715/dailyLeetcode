package Solutions;

import java.util.*;

public class P218_TheSkylineProblem {
    /*
        1. Get all significant points (start and end points)
        2. Sort all points (start before end to make sure the highest is always in)
        3. Go through all points and see if max height changes; if so, add to res
        *** need TreeMap to track the max heights and its quantity
    */
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> res = new ArrayList<>();
        List<int[]> sPoints = new ArrayList<>();
        for (int[] b : buildings) {
            // height<0 means starting
            sPoints.add(new int[]{b[0], -b[2]});
            sPoints.add(new int[]{b[1], b[2]});
        }
        /*
            sort by x
            then start to end
            if both start: from tall to short
            if both end: from short to tall
        */
        Collections.sort(sPoints, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });
        // TreeMap<height, quantity> to track max heights
        TreeMap<Integer, Integer> heights = new TreeMap<>();
        heights.put(0, 1);
        int prevHeight = 0;
        for (int[] sPoint : sPoints) {
            // if start point, increment count
            if (sPoint[1] < 0) {
                heights.put(-sPoint[1], heights.getOrDefault(-sPoint[1], 0) + 1);
            } else {
                int count = heights.get(sPoint[1]);
                heights.put(sPoint[1], count - 1);
                if (heights.get(sPoint[1]) == 0) {
                    heights.remove(sPoint[1]);
                }
            }
            int curHeight = heights.lastKey();
            if (curHeight != prevHeight) {
                res.add(Arrays.asList(sPoint[0], curHeight));
                prevHeight = curHeight;
            }
        }
        return res;
    }
}
