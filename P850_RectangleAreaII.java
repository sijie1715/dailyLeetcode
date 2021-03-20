package Solutions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

public class P850_RectangleAreaII {
    public int rectangleArea(int[][] rectangles) {
        int M = 1000000000 + 7;
        List<int[]> points = new ArrayList<>();
        // record critical points as:
        // (-1)--(+1)
        //  |      |
        // (+1)--(-1)
        // line sweep on x (in +y direction); calculate y coverage at every x (like meeting room)
        for (int[] rec : rectangles) {
            points.add(new int[]{rec[0], rec[1], 1});
            points.add(new int[]{rec[0], rec[3], -1});
            points.add(new int[]{rec[2], rec[1], -1});
            points.add(new int[]{rec[2], rec[3], 1});
        }
        Collections.sort(points, (a, b) -> a[0] - b[0]);
        // <y, val>
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int preX = -1, preY = -1, result = 0;
        for (int i = 0; i < points.size(); i++) {
            int[] p = points.get(i);
            map.put(p[1], map.getOrDefault(p[1], 0) + p[2]);
            // if this is the last point OR
            // if next point is different x (calculate y)
            if (i == points.size() - 1 || points.get(i + 1)[0] > p[0]) {
                if (preX > -1) {
                    result += ((long) preY * (p[0] - preX)) % M;
                    result %= M;
                }
                preY = calcY(map);
                preX = p[0];
            }
        }
        return result;
    }

    private int calcY(TreeMap<Integer, Integer> map) {
        int count = 0, preY = -1, res = 0;
        for (int y : map.keySet()) {
            int val = map.get(y);
            // count is ongoing meetings
            if (preY > -1 && count > 0) {
                res += y - preY;
            }
            preY = y;
            count += val;
        }
        return res;
    }
}
