package Solutions;

public class P875_KokoEatingBananas {
    public int minEatingSpeed(int[] piles, int h) {
        int l = 1, r = 1000000000;
        while (l < r) {
            int total = 0, m = l + (r - l) / 2;
            for (int p : piles) {
                // ceil(p/m)
                total += (p + m - 1) / m;
            }
            // k too small
            if (total > h) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return l;
    }
}
