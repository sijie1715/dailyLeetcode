package Solutions;

public class P1482_MinimumNumberOfDaysToMakeMBouquets {
    public int minDays(int[] bloomDay, int m, int k) {
        // if length<m*k then return -1
        // l=1,r=1e9 and check numOfBouqs
        if (bloomDay.length < m * k) return -1;
        int l = 1, r = 0;
        for (int b : bloomDay) r = Math.max(r, b);

        while (l < r) {
            int mid = l + (r - l) / 2, flow = 0, bouq = 0;
            // count number of bouqs
            for (int b : bloomDay) {
                if (b > mid) {
                    flow = 0;
                } else if (++flow == k) { // here flow already updated
                    bouq++;
                    flow = 0;
                }
            }
            if (bouq >= m) r = mid;
            else l = mid + 1;
        }
        return l;
    }
}
