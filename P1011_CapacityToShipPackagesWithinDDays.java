package Solutions;

public class P1011_CapacityToShipPackagesWithinDDays {
    public int shipWithinDays(int[] weights, int D) {
        int l = 1, r = 500 * 50000;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (valid(m, weights, D)) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }

    public boolean valid(int cap, int[] weights, int D) {
        int count = 1, sum = 0;
        for (int w : weights) {
            if (w > cap) return false;
            sum += w;
            if (sum > cap) {
                sum = w;
                count++;
                if (count > D) return false;
            }
        }
        return true;
    }
}
