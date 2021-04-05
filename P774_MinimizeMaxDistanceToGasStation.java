package Solutions;

public class P774_MinimizeMaxDistanceToGasStation {
    public double minmaxGasDist(int[] stations, int k) {
        // try mid(distance)=(l+r)/2
        // if count>k then mid too small -> l=mid
        // fill in between every 2 points
        int n = stations.length;
        double l = 0, r = stations[n - 1] - stations[0];
        while (l + 1e-6 < r) {
            double m = l + (r - l) / 2;
            int count = 0;
            for (int i = 0; i < n - 1; i++) {
                count += Math.ceil((stations[i + 1] - stations[i]) / m) - 1;
            }
            if (count > k) l = m;
            else r = m;
        }
        return l;
    }
}
