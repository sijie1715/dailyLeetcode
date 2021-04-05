package Solutions;

public class P1231_DivideChocolate {
    public int maximizeSweetness(int[] sweetness, int K) {
        // find sweetness m=(l+r)/2
        // each chunk sweetness >= m
        // if count<=K+1 r=m-1 (when equals, maybe can still shrink)
        int l = 1, r = (int) 1e9 / (K + 1);
        // int count = 1;
        while (l < r) {
            // when (l+r)/2=l, might stuck
            int m = (l + r + 1) / 2;
            int flow = 0, count = 1;
            for (int s : sweetness) {
                flow += s;
                if (flow >= m) {
                    flow = 0;
                    count++;
                }
            }
            if (count <= K + 1) r = m - 1;
            else l = m;
        }
        return l;
    }
}
