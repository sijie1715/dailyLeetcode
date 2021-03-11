package Solutions;

import java.util.ArrayList;
import java.util.List;

public class P986_IntervalListIntersections {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> res = new ArrayList<>();
        int ia = 0, ib = 0;
        while (ia < A.length && ib < B.length) {
            int a0 = A[ia][0], a1 = A[ia][1], b0 = B[ib][0], b1 = B[ib][1];
            if (a1 >= b0 && b1 >= a0) {
                res.add(new int[]{Math.max(a0, b0), Math.min(a1, b1)});
            }
            if (a1 <= b1) ia++;
            else ib++;
        }
        return res.toArray(new int[res.size()][]);
    }
}
